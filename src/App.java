import java.util.ArrayList;

import javax.swing.JOptionPane;;

public class App {
    static ArrayList<Object> todoList = new ArrayList<Object>();
    public static void main(String[] args) throws Exception {
        
        start();
    }
    private static void start() {
        String str = "Lütfen Seçim Yapınız";
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

    //* ***************************** delTodo **************************************** */

    private static void delTodo() {
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


    private static void addTodo() {
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
    private static void list() {
        String str = "Todo List";
        str+= "\n*****************\n ";
        for (int i = 0; i < todoList.size(); i++) {
            str+=  i+1 +". "+ todoList.get(i)+"\n";
        }
        str+="Toplam "+todoList.size()+" kayıt bulunmaktadır";
        message(str);

    }
    //* **************************** message ***************************************** */

    private static void message(String str) {
        JOptionPane.showMessageDialog(null, str);
        start();
    }
    //* ****************************** exit ***************************************** */

    private static void exit() {
        int confirm = JOptionPane.showConfirmDialog(null, "Çıkmak istediğinizden emin misiniz?");
        if (confirm == 0){
            System.exit(0);

        }else{
            start();
        }
    }
}
