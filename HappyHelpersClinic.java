package group_iar_5;

import static group_iar_5.Offers.checkOffer;
import java.io.*;
import java.util.Scanner;

//Main Class
public class HappyHelpersClinic {

    static int pIndex = 0;

    public static void main(String[] args) throws FileNotFoundException {

        // to read info from the file 
        File inputFile = new File("newInput.txt");
        // to write into file 
        File PrintFile = new File("output.txt");
        PrintWriter output = new PrintWriter(PrintFile);
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(inputFile);
//        Scanner input2 = new Scanner(System.in);
        // variables to read the size of each array from input file
//        int total_number_of_patients = input.nextInt();
        int total_number_of_doctor = input3.nextInt();
        int total_number_of_Nurse = input3.nextInt();
        int total_number_of_appointments = input3.nextInt();
        int total_number_of_Offers = input3.nextInt();
        // arrays to store clinic's patients, doctors, nurses, available appointments
        Patient[] patients_array = new Patient[100];
        Doctor[] doctors_array = new Doctor[total_number_of_doctor];
        Nurse[] nurses_array = new Nurse[total_number_of_Nurse];
        Appointment[] appointments_array = new Appointment[total_number_of_appointments];
        Offers[] offers = new Offers[total_number_of_Offers];
//    
        //cheak if a file exist or not? 
        if (!inputFile.exists()) {
            System.out.println("The file does not exist");
            System.exit(0);
        }
        //output.println();
        // output.println("---- Welcome to Happy Helpers Clinic ----\n\n");
        System.out.println("---- Welcome to Happy Helpers Clinic ----\n\n");
       // SignUp_Patient(patients_array, input, input, output);
        // to read the command from input file 
        String command;
        // loop to fill the arrays by reading data from input file
        while (input3.hasNext()) {
           command = input3.next();
        /**
         * for each command, call the appropriate method to read patient's
         * information from the file and store them
         */
            if (command.equalsIgnoreCase("SignUp_patient")) {
                SignUp_Patient(patients_array, input, input2, output);
            } else if (command.equalsIgnoreCase("SignUp_Doctor")) {
                SignUp_Doctor(doctors_array, input3, output);
            } else if (command.equalsIgnoreCase("SignUp_Nurse")) {
                SignUp_nurse(nurses_array, input3, output);
            } else if (command.equalsIgnoreCase("Add_Appointment")) {
                Show_Appointment(appointments_array, doctors_array, input3, output);
            } else if (command.equalsIgnoreCase("Book_Appointment")) {
                BookAppointment(appointments_array, patients_array, input3, output);
            } else if (command.equalsIgnoreCase("Cancel_Appointment")) {
                CancelAppointment(patients_array, appointments_array, input3, output);
            } else if (command.equalsIgnoreCase("Add_offers")) {
                Offers(offers, input3, output);

            } else {
                Quit(output);
            }
        }

      //  MenuServices(input2);
        DisplayMenuServices();
        OffersMenu(input2);
    }

    /**
     * Method that read patient's information from the file, create the object
     * and store it in the array, then print his information
     */
    public static void SignUp_Patient(Patient[] patients_array, Scanner input, Scanner input2, PrintWriter output) {
        System.out.println("Please fill the following information:");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("ID: ");
        String id = input2.next();
        while ((id.matches("[0-9]+") && id.length() == 10) == false) {
            System.out.print("Please try again, only 10 DIGITS is allowed! ");
            id = input2.next();
        }
        System.out.print("Phone (Starting with 05): ");
        String phone = input2.next();
        while ((phone.matches("[0-9]+") && phone.length() == 10 && phone.startsWith("05")) == false) {
            System.out.print("Please try again, only 10 DIGITS started with 05 is allowed! ");
            phone = input2.next();
        }
        System.out.print("Gender (F for female and M for male): ");
        String gender = input.next() + "";
        while (((gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m")) && gender.length() == 1) == false) {
            System.out.print("Please try again, only f or m is allowed! ");
            gender = input.next().charAt(0) + "";
        }
        System.out.print("Age: ");
        int age = input2.nextInt();
        System.out.print("Nationality: ");
        String nationality = input.next();
        System.out.print("Address: ");
        String address = input.next();
        System.out.print("Password: ");
        input.next();
        String password = input.nextLine();
        patients_array[pIndex] = new Patient(name, id, phone, gender.charAt(0), age, nationality, address, password);
        System.out.println("Dear " + patients_array[pIndex].getName() + ", with ID " + patients_array[pIndex].getId() + " you are regestered successfully.");
        pIndex++;
    }

    /**
     * Method that read doctor's information from the file, create the object
     * and store it in the array, then print his information
     */
    public static void SignUp_Doctor(Doctor[] doctors_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Doctor\n");
        System.out.println("\n--->>  Add Doctor\n");

        for (int i = 0; i < doctors_array.length; i++) {
            doctors_array[i] = new Doctor(input.next().replaceAll("_", " "), input.next(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next(), input.nextDouble(), input.next(), input.next());
            output.println("The doctor " + doctors_array[i].getName() + ", with ID " + doctors_array[i].getId() + " is added successfully.");
            System.out.println("The doctor " + doctors_array[i].getName() + ", with ID " + doctors_array[i].getId() + " is added successfully.");
        }
    }

    /**
     * Method that read nurse's information from the file, create the object and
     * store it in the array, then print his information
     */
    public static void SignUp_nurse(Nurse[] nurses_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Nurse\n");
        System.out.println("\n--->>  Add Nurse\n");

        for (int i = 0; i < nurses_array.length; i++) {
            nurses_array[i] = new Nurse(input.next().replaceAll("_", " "), input.next(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next(), input.nextDouble(), input.next(), input.next());
            output.println("The nurse " + nurses_array[i].getName() + ", with ID " + nurses_array[i].getId() + " is added successfully.");
            System.out.println("The nurse " + nurses_array[i].getName() + ", with ID " + nurses_array[i].getId() + " is added successfully.");
        }
    }

    /**
     * Method that read available appointment's information from the file,
     * create the object and store it in the array, then print his information
     */
    public static void Show_Appointment(Appointment[] appointments_array, Doctor[] doctors_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Appointment\n");
        System.out.println("\n--->>  Add Appointment\n");

        // fill the array
        for (int i = 0; i < appointments_array.length; i++) {
            appointments_array[i] = new Appointment(input.next(), input.nextInt(), input.nextInt(), input.nextInt(), input.next());
            String doctor_ID = appointments_array[i].getDoctor_ID();
            Doctor doctor_object = find_doctor(doctors_array, doctor_ID);
            if (doctor_object != null) {
                output.println("The appointment with doctor " + doctor_object.getName() + ", at " + appointments_array[i].getDay() + "/"
                        + appointments_array[i].getMonth() + "/" + appointments_array[i].getYear() + " " + appointments_array[i].getAppointmentTime().toUpperCase()
                        + " is available now.");
                System.out.println("The appointment with doctor " + doctor_object.getName() + ", at " + appointments_array[i].getDay() + "/"
                        + appointments_array[i].getMonth() + "/" + appointments_array[i].getYear() + " " + appointments_array[i].getAppointmentTime().toUpperCase()
                        + " is available now.");
            }
        }
    }

    /**
     * Method that read appointment's information from the file, check if
     * available and reserve it
     */
    static void BookAppointment(Appointment[] appointments_array, Patient[] patient_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Appointment\n");
        System.out.println("\n--->>  Add Appointment\n");
        //read from the file 
        String patientID = patient_array[0].getId(), doctorID = input.next();
        int year = input.nextInt(), month = input.nextInt(), day = input.nextInt();
        String time = input.next();

        // check if the patient is regestred
        int patient_index = find_patient(patient_array, patientID);
        // if not regestred
        if (patient_index == -1) {
            output.println("Dear " + patientID + ", you can not book an appointment since that you are not registered.");
            System.out.println("Dear " + patientID + ", you can not book an appointment since that you are not registered.");

            // if regestred
        } else {
            // check if the appointment is available in the clinic or not
            int appointment_index = check_appointments_list(appointments_array, doctorID, year, month, day, time);

            // if not available in the clinic, inform the patient and ask him to choose another
            if (appointment_index == -1) {
                output.println("Dear " + patient_array[patient_index].getName() + ", the ppointment is not available in the clinic, please choose another.");
                System.out.println("Dear " + patient_array[patient_index].getName() + ", the ppointment is not available in the clinic, please choose another.");

                // if the appointment is available i n clinic's list
            } else {
                // check if the appointment is already reserved, inform the patient and ask him to choose another
                if (appointments_array[appointment_index].isAvailable() == false) {
                    output.println("Dear " + patient_array[patient_index].getName() + ", sorry the ppointment is reserved, please choose another.");
                    System.out.println("Dear " + patient_array[patient_index].getName() + ", sorry the ppointment is reserved, please choose another.");

                    // if the appointment is available
                } else {
                    // to reserve new appointment, check that the patient have less than two reserved appointment in his record
                    boolean ability_to_reserve = patient_array[patient_index].AddAppointment(doctorID, year, month, day, time);

                    if (ability_to_reserve == false) {
                        output.println("Dear " + patient_array[patient_index].getName() + " your booking is failed, you already have two reserved appointments");
                        System.out.println("Dear " + patient_array[patient_index].getName() + " your booking is failed, you already have two reserved appointments");
                    } else {
                        output.println("Dear " + patient_array[patient_index].getName() + ", the appointment is available, please pay the bill to confirm it.");
                        System.out.println("Dear " + patient_array[patient_index].getName() + ", the appointment is available, please pay the bill to confirm it.");
                        appointments_array[appointment_index].setAvailable(false);

                    }
                }
            }
        }
    }

    // method search for certain appointments if exist in the list or not, if exist return its index, otherwise return -1
    static int check_appointments_list(Appointment[] appointment_array, String dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < appointment_array.length; i++) {
            if (dr_id.equals(appointment_array[i].getDoctor_ID())
                    && appointment_array[i].getDay() == day && appointment_array[i].getMonth() == month
                    && appointment_array[i].getYear() == Year
                    && appointment_array[i].getAppointmentTime().equalsIgnoreCase(time)) {
                // if the appointment found
                return i;
            }
        }
        // otherwise return -1
        return -1;
    }

    // method search for patient by his ID and return its index if found, otherwise return -1
    public static int find_patient(Patient[] patients_array, String id) {
        for (int i = 0; i < patients_array.length; i++) {
            if (patients_array[i].getId().equals(id)) {
                //if the patient regestered
                return i;
            }
        }
        //if not found
        return -1;
    }

    // method search for doctor by his ID and return its object if found, otherwise return null
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

    /**
     * Method that read (appointment to cancel) information from the file,
     * search for it in patient record and cancel it
     */
    static void CancelAppointment(Patient[] patient, Appointment[] appointment_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Cancel Appointment\n");
        System.out.println("\n--->>  Cancel Appointment\n");

        // read from the file 
        String patientID = input.next(), DoctorID = input.next();
        int year = input.nextInt(), month = input.nextInt(), day = input.nextInt();
        String time = input.next().toUpperCase();
        // check if the patient is regestered or not
        int patient_index = find_patient(patient, patientID);
        // if not regestered
        if (patient_index == -1) {
            output.println("Dear " + patientID + ", you are not registered");
            System.out.println("Dear " + patientID + ", you are not registered");
            // if regestered
        } else {
            // check if the patient has the appointment is his record
            int patient_appointment_index = patient[patient_index].SearchAppointment(year, month, day, time);

            // if the appointment not found 
            if (patient_appointment_index == -1) {
                output.println("Dear " + patient[patient_index].getName() + ", the appointment is not found in your record.\n");
                System.out.println("Dear " + patient[patient_index].getName() + ", the appointment is not found in your record.\n");
                // if found
            } else {
                // search for appointment index
                int appointment_index = check_appointments_list(appointment_array, DoctorID,
                        year, month, day, time);

                // invoke delete_appointment method to remove it from patient record
                patient[patient_index].delete_appointment(patient_appointment_index);

                // change the canceled appointment's status to available, so any patient can reserve it now
                appointment_array[appointment_index].setAvailable(true);
                output.println("Dear " + patient[patient_index].getName() + ", your appointment is canceled successfully.\n");
                System.out.println("Dear " + patient[patient_index].getName() + ", your appointment is canceled successfully.\n");
            }
        }

    }

    public static void Offers(Offers[] offers, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Offers\n");
        System.out.println("\n--->>  Add Offers\n");

        for (int i = 0; i < offers.length; i++) {
            offers[i] = new Offers(input.nextInt(), input.nextLine());
            output.println("The Offers " + offers[i].getContent() + ", with ID " + offers[i].getOffersID() + " is added successfully.");
            System.out.println("The Offers " + offers[i].getContent() + ", with ID " + offers[i].getOffersID() + " is added successfully.");
        }
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

    public static void Recomendatoins() {

    }

    // method to print the final statement and close the output file
    public static void Quit(PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\nThank you for using Our clinic System, Good Bye!");
        System.out.println("\nThank you for using Our clinic System, Good Bye!");
        output.flush();
        output.close();
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

    // menu of book appointment 
//        if (choice_S == 1) {
//            System.out.println("choose >>");
//            System.out.println("1. book appointment for dentist ");
//            System.out.println("2. book appointment for dermatologist‏ ");
//            choice_b = input.nextInt();
//        }
//        switch (choice_b) {
//            case 1: System.out.println("The avalible appointment : تنعرض المواعيد مع اسماء الدكاتره ");
//            case 2:System.out.println("The avalible appointment : تنعرض له برضو ");
//            
//        }
    public void checkofBookAppointment() {
        // كل التيشيكات تصير هنا تبع حجز الموعد 
    }

    public void checkofCancellAppointment() {
        // كل التيشيكات تصير هنا تبع الغاء الموعد 
    }

}
