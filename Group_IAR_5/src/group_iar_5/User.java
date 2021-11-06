package group_iar_5;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private String name;
    private int Id; //XXXXXXXXXXXXXXXXXXXXXX check only 10 digits 
    private String Phone; //XXXXXXXXXXXXXXXXXXXXXXXXX check only 10 digits 
    private char Gender;
    private int age;
    private String Nationality;
    private String password;
    private String address=null;
    private ArrayList<String> passwords = new ArrayList<String>();
    public static ArrayList<User> users = new ArrayList<User>();

    public User(){
        
    }
    public User(String name, int Id, String Phone, char Gender, int age, String Nationality  , String address) {
        this.name = name;
        this.Id = Id;
        this.Phone = Phone;
        this.Gender = Gender;
        this.age = age;
        this.Nationality = Nationality;
        this.address=address;
        this.password = password_generator();
        passwords.add(password);
      //  users.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public void setGender(char Gender) {
        this.Gender = Gender;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return Nationality;
    }

    public char getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }
      public String getAddress() {
        return address;
    }
      public void setaddress(String address){
          this.address=address;
      }

    public String password_generator() {
        password = "User@" + (new Random().nextInt(9000000) + 1000000);
        for (int i = 0; i < passwords.size(); i++) {
            //to insure unique password for each user
            if (passwords.get(i) != null && passwords.get(i) == password) {
                password = "User@" + (new Random().nextInt(9000000) + 1000000);
            }
        }
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean validation(int ID, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == ID && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean login(int ID, String password) {
        boolean exist_account = validation(ID, password);
        if (exist_account == true) {
            System.out.println("Yor are logged in successfully");
            return true;
        }
        System.out.println("You are not regester, please sign up!");
        return false;
    }
   
    public static void main(String[] args) {
//        User user1 = new Patient("Ahmed", 05, 40, 'i', 650, "jyh");
//        User doctor = new Doctor(877, "kk", "jb", 877, 0, 'n', 87, "jh");
//        User user3 = new Patient("Ali", 057, 480, 'i', 650, "jyh");
//        User user4 = new Patient("Khaled", 05, 40, 'i', 650, "jyh");
        
//        boolean add = ((Patient) user1).AddAppointment(2021, 11, 9, "3Am", ((Patient) user1).getPatient_file_number(), doctor.getId());
//        System.out.println(add);
//        add = 
       
//        ((Patient) user1).AddAppointment(2021, 11, 9, "3am", ((Patient) user1).getPatient_file_number(), doctor.getId());
//         ((Patient) user3).AddAppointment(2021, 11, 9, "3Am", ((Patient) user3).getPatient_file_number(), doctor.getId());
//        ((Patient) user1).AddAppointment(2021, 11, 9, "3am", ((Patient) user1).getPatient_file_number(), doctor.getId());
//        ((Patient) user3).AddAppointment(2021, 11, 9, "9pm", ((Patient) user3).getPatient_file_number(), doctor.getId());
        // int i =((Patient)user1).SearchAppointmentAt(user2.getId(), 2021, 11, 9, "3am");
        //  System.out.println(i);
    }
}
