import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class App {
    static ArrayList<Object> todoList = new ArrayList<Object>();
    public static void main(String[] args) throws Exception {
        File file = new File("lib/todo.txt");
        if (!file.exists()) {   
            file.createNewFile();
         }
        start();
    }
    private static void start() throws IOException {
        String str = "Lütfen Seçim Yapınız";
        //devam edilecektir...
        str+="\n****************\n";
        str+="\n [1] Listele ";
        str+="\n [2] Ekle ";
        str+="\n [3] Sil ";
        str+="\n [0 veya Cancel] Çıkış ";

        String choice = JOptionPane.showInputDialog(null, str);

        if(choice == null){
            choice= "0";
        }else if (choice.equals("")||choice.matches("[^0-9]+")){
            start();
        }
        switch (choice) {
            case "0":
                exit();
                break;
            case "1":
                list();
                break;
            case "2":
                addTodo();
                break;
            case "3":
                delTodo();
                break;
            default:
                break;
        }

    }
    //*    */
    //* ***************************** delTodo **************************************** */

    private static void delTodo() throws IOException {

        String title = JOptionPane.showInputDialog(null, "Silinecek görev!");
        File file = new File("lib/todo.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp = "";
            String line = br.readLine();
            while (line != null) {
                Object[] dizi = line.split(",");
                String data = dizi[1].toString();
                if(!title.equals(data)){
                    temp += line + System.lineSeparator();
                }
                line = br.readLine();

                
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(temp);
            br.close();
            bw.close();
            start();
        }

    //* ****************************** addTodo ************************************** */

    private static void addTodo() throws IOException {
        Todo data = new Todo();
        JTextField title = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Title:"));
        panel.add(title);
        int confirm = JOptionPane.showConfirmDialog(null, panel, "Lütfen başlık biligisini yazınız!", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.OK_OPTION) {
            int dataId = getRecordCount() + 1;
            String dataTitle = title.getText();
            File file = new File("lib/todo.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String temp = "";
            String line = br.readLine();
            while (line != null) {
                temp += line + System.lineSeparator();
                line = br.readLine();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(temp);
            bw.write(dataId + "," + dataTitle + "," + false);
            br.close();
            bw.close();
        } else {
            addTodo();
            
        }
        start();
    }
    
    //* ****************************** list *************************************** */
    private static void list() throws IOException {
        File file = new File("lib/todo.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        Object[] cols = {"id","title","isCompleted"};
        Object[][] rows = new Object[getRecordCount()][3];
        int index = 0;
        while (line!=null){
            rows[index] = line.split(",");
            line=br.readLine();
            index++;
        }
        JTable table = new JTable(rows, cols);
        JOptionPane.showMessageDialog(null,new JScrollPane(table));
        
        start();

    }
    //* **************************** message ***************************************** */

   
    private static int getRecordCount() throws IOException {
        File file = new File("lib/todo.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int count = 0;
        while(br.readLine() != null){
            count++;
        }
        br.close();
        return count;
}
    private static void message(String str) throws IOException {
        JOptionPane.showMessageDialog(null, str);
        start();
    }
    //* ****************************** exit ***************************************** */

    private static void exit() throws IOException {
        int confirm = JOptionPane.showConfirmDialog(null, "Çıkmak istediğinizden emin misiniz?");
        if (confirm == 0){
            System.exit(0);

        }else{
            start();
        } 
    }

}
