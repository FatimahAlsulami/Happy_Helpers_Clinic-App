/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group_iar_5;

/**
 *
 * @author Fawad
 */
public class Nurse extends Staff {
    
   public Nurse( String name, int Id, String Phone, char Gender, int age, String Nationality,String address,double salary, String specialization) {
        super(salary, specialization, name, Id, Phone, Gender, age, Nationality,address);
    }

    
    
}
