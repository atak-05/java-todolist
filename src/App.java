import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
        String title = JOptionPane.showInputDialog(null, "Görev başlığını yazınız!");
        if (title != null && !title.equals("")){
            if(todoList.indexOf(title)>=0){
                    todoList.remove(title);
                    JOptionPane.showMessageDialog(null,"Silme Başarılı");
                    start();           
            }else{
                JOptionPane.showMessageDialog(null,"Böyle bir başlık yok!!");
                     start();   
            }
        }else{
            JOptionPane.showMessageDialog(null,"Lütfen bir başlık yazınız!!!");
            start();

        }
    }
    

    //* ****************************** addTodo ************************************** */


    private static void addTodo() throws IOException {
        String title = JOptionPane.showInputDialog(null, "Görev başlığını yazınız!");
        if (title != null && !title.equals("")){
            if(todoList.indexOf(title)>=0){
                     JOptionPane.showMessageDialog(null,"Sistemde aynı başlık iki kez kullanılamaz!!");
                     start();           
            }else{
                todoList.add(title);
                JOptionPane.showMessageDialog(null,"Kayıt Eklendi!!");
                     start();   
            }
        }else{
            JOptionPane.showMessageDialog(null,"Lütfen bir başlık yazınız!!!");
            start();

        }
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
