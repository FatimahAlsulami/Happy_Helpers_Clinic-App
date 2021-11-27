package group_iar_5;

import java.util.ArrayList;
import java.util.Random;

public abstract class User {

    private String name;
    private String Id; //XXXXXXXXXXXXXXXXXXXXXX check only 10 digits 
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
    public User(String name, String Id, String Phone, char Gender, int age, String Nationality , String address) {
        this.name = name;
        this.Id = Id;
        this.Phone = Phone;
        this.Gender = Gender;
        this.age = age;
        this.Nationality = Nationality;
        this.address=address;
        this.password=password_generator();
        passwords.add(password);
       // users.add(this);
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

    public String getId() {
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
   
        
}
