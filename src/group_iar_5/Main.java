package group_iar_5;

import static group_iar_5.Offers.checkOffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // to read info from the file 
        // to read info from the file 
        File inputFile = new File("newInput.txt");
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(inputFile);

        int total_number_of_Patients = input3.nextInt();
        int total_number_of_doctor = input3.nextInt();
        int total_number_of_Nurse = input3.nextInt();
        int total_number_of_appointments = input3.nextInt();
        int total_number_of_Offers = input3.nextInt();
        // arrays to store clinic's patients, doctors, nurses, available appointments
        Patient[] patients_array = new Patient[total_number_of_Patients];
        Doctor[] doctors_array = new Doctor[total_number_of_doctor];
        Nurse[] nurses_array = new Nurse[total_number_of_Nurse];
        Appointment[] appointments_array = new Appointment[total_number_of_appointments];
        Offers[] offers = new Offers[total_number_of_Offers];

        System.out.println("---- Welcome to Happy Helpers Clinic ----\n\n");

        int choice;
        int choice2;
        do {
            DisplayMenuServices();
            choice = input2.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("book appointment");

                    do {
                        displyAppointmentMenu();
                        choice2 = input2.nextInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("book for dentist");
                                AppointmentDEPdentis(appointments_array, doctors_array);
                                break;
                            case 2:
                                System.out.println("book for dermatologist‏");
                                break;

                            default:
                                System.out.print("ERROR, You'r choice wrong!!, try again : ");
                        }
                    } while (choice2 > 2 || choice2 < 1);

                    break;
                case 2:
                    System.out.println("cancel");
                    break;
                case 3:
                    System.out.println("display offer");
                    OffersMenu(input2);
                    break;
                case 4:
                    System.out.println("display recomendation");
                    break;
                default:
                    System.out.print("ERROR, You'r choice wrong!!, try again : ");
            }
        } while (choice > 4 || choice < 1);

    }
     public static void Add_Patient(Patient[] patients_array, Scanner input) {
       
        System.out.println("----------------------");
 
        System.out.println("\n--->>  Add Patient\n");
        
        for (int i = 0; i < patients_array.length; i++) {
            patients_array[i] = new Patient(input.next().replaceAll("_", " "), input.nextInt(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next());
           
            System.out.println("The patient " + patients_array[i].getName() + ", with ID " + patients_array[i].getId() + " is added successfully.");
        }
    }

    public static void DisplayMenuServices() {

        // choice 1 or 2 1 >> اسنان 2 >> جلديه 
        System.out.println("Choose from our services!");
        System.out.println("------------------------------------");
        System.out.println("1. Book Appointment\n"
                + "2. Cancell Appointment\n"
                + "3. Display Offers\n"
                + "4. Disply Recomendations");

    }

    public static void OffersMenu(Scanner input) {

        System.out.println("1. discount by 20% if your from kau\n"
                + "2. discount by 50% if your have gold card\n"
                + "3. discount by 30% if your have bronze card");
        System.out.print("choose offer:");
        int choice = 0;
        boolean check = checkOffer(choice);
        while (check == false) {
            choice = input.nextInt();
            check = checkOffer(choice);
            switch (choice) {
                case 1:
                    System.out.println("you have discount by 20%");
                    break;
                case 2:
                    System.out.println("you have discount by 50%");
                    break;
                case 3:
                    System.out.println("you have discount by 30%");
                    break;
                default:
                    System.out.print("ERROR, You'r choice wrong!!, try again : ");
            }
        }

    }

    static void BookAppointment(Appointment[] appointments_array, Doctor[] doctors_array, Scanner input, Scanner in) {

    }

    public static void displyAppointmentMenu() {
        System.out.println("choose department");
        System.out.println("1. appointment dentis ");
        System.out.println("2. appointment determnology ");
    }

    public static void AppointmentDEPdentis(Appointment[] appointments_array, Doctor[] doctors_array, Scanner in) {
        System.out.println("the avalible appointment of dentis departments:");

        for (int i = 0; i < appointments_array.length; i++) {
            appointments_array[i] = new Appointment(in.next(), in.nextInt(), in.nextInt(), in.nextInt(), in.next());
            String doctor_ID = appointments_array[i].getDoctor_ID();
            Doctor doctor_object = find_doctor(doctors_array, doctor_ID);
            if (doctor_object != null) {
                System.out.println("The appointment with doctor " + doctor_object.getName() + ", at " + appointments_array[i].getDay() + "/"
                        + appointments_array[i].getMonth() + "/" + appointments_array[i].getYear() + " " + appointments_array[i].getAppointmentTime().toUpperCase()
                        + " is available now.");

            }
        }
    }

    public static Doctor find_doctor(Doctor[] doctors_array, String id) {
        for (int i = 0; i < doctors_array.length; i++) {
            if (doctors_array[i].getId().equals(id)) {
                //if the doctor regestered
                return doctors_array[i];
            }
        }
        //if not found
        return null;
    }

}
