package group_iar_5;

import java.io.*;
import java.util.Scanner;

//Main Class
public class HappyHelpersClinic {

    public static void main(String[] args) throws FileNotFoundException {

        // to read info from the file 
        File inputFile = new File("newInput.txt");
        // to write into file 
        File PrintFile = new File("output.txt");
        PrintWriter output = new PrintWriter(PrintFile);
        Scanner input = new Scanner(inputFile);

        // variables to read the size of each array from input file
        int total_number_of_patients = input.nextInt();
        int total_number_of_doctor = input.nextInt();
        int total_number_of_Nurse = input.nextInt();
        int total_number_of_appointments = input.nextInt();

        // arrays to store clinic's patients, doctors, nurses, available appointments
        Patient[] patients_array = new Patient[total_number_of_patients];
        Doctor[] doctors_array = new Doctor[total_number_of_doctor];
        Nurse[] nurses_array = new Nurse[total_number_of_Nurse];
        Appointment[] appointments_array = new Appointment[total_number_of_appointments];

        //cheak if a file exist or not? 
        if (!inputFile.exists()) {
            System.out.println("The file does not exist");
            System.exit(0);
        }
        output.println();
        output.println("---- Welcome to Happy Helpers Clinic ----\n\n");
        System.out.println("---- Welcome to Happy Helpers Clinic ----\n\n");

        // to read the command from input file 
        String command;
        // loop to fill the arrays by reading data from input file
        while (input.hasNext()) {
            command = input.next();
            /**
             * for each command, call the appropriate method to read patient's
             * information from the file and store them
             */
            if (command.equalsIgnoreCase("Add_patient")) {
                Add_Patient(patients_array, input, output);
            } else if (command.equalsIgnoreCase("Add_Doctor")) {
                Add_Doctor(doctors_array, input, output);
            } else if (command.equalsIgnoreCase("Add_Nurse")) {
                Add_nurse(nurses_array, input, output);
            } else if (command.equalsIgnoreCase("Add_Appointment")) {
                Add_Appointment(appointments_array, doctors_array, input, output);
            } else if (command.equalsIgnoreCase("Book_Appointment")) {
                BookAppointment(appointments_array, patients_array, input, output);
            } else if (command.equalsIgnoreCase("Cancel_Appointment")) {
                CancelAppointment(patients_array, appointments_array, input, output);
            } else {
                Quit(output);
            }
        }
    }

    /**
     * Method that read patient's information from the file, create the object
     * and store it in the array, then print his information
     */
    public static void Add_Patient(Patient[] patients_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Patient\n");
        System.out.println("\n--->>  Add Patient\n");
        
        for (int i = 0; i < patients_array.length; i++) {
            patients_array[i] = new Patient(input.next().replaceAll("_", " "), input.nextInt(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next());
            output.println("The patient " + patients_array[i].getName() + ", with ID " + patients_array[i].getId() + " is added successfully.");
            System.out.println("The patient " + patients_array[i].getName() + ", with ID " + patients_array[i].getId() + " is added successfully.");
        }
    }

    /**
     * Method that read doctor's information from the file, create the object
     * and store it in the array, then print his information
     */
    public static void Add_Doctor(Doctor[] doctors_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Doctor\n");
        System.out.println("\n--->>  Add Doctor\n");
        
        for (int i = 0; i < doctors_array.length; i++) {
            doctors_array[i] = new Doctor(input.next().replaceAll("_", " "), input.nextInt(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next(), input.nextInt(), input.next());
            output.println("The doctor " + doctors_array[i].getName() + ", with ID " + doctors_array[i].getId() + " is added successfully.");
            System.out.println("The doctor " + doctors_array[i].getName() + ", with ID " + doctors_array[i].getId() + " is added successfully.");
        }
    }

    /**
     * Method that read nurse's information from the file, create the object and
     * store it in the array, then print his information
     */
    public static void Add_nurse(Nurse[] nurses_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Nurse\n");
        System.out.println("\n--->>  Add Nurse\n");
        
        for (int i = 0; i < nurses_array.length; i++) {
            nurses_array[i] = new Nurse(input.next().replaceAll("_", " "), input.nextInt(), input.next(), input.next().charAt(0), input.nextInt(), input.next(), input.next(), input.nextDouble(), input.next());
            output.println("The nurse " + nurses_array[i].getName() + ", with ID " + nurses_array[i].getId() + " is added successfully.");
            System.out.println("The nurse " + nurses_array[i].getName() + ", with ID " + nurses_array[i].getId() + " is added successfully.");
        }
    }

    /**
     * Method that read available appointment's information from the file,
     * create the object and store it in the array, then print his information
     */
    public static void Add_Appointment(Appointment[] appointments_array, Doctor[] doctors_array, Scanner input, PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\n--->>  Add Appointment\n");
        System.out.println("\n--->>  Add Appointment\n");
        
        // fill the array
        for (int i = 0; i < appointments_array.length; i++) {
            appointments_array[i] = new Appointment(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt(), input.next());
            int doctor_ID = appointments_array[i].getDoctor_ID();
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
        int patientID = input.nextInt(), doctorID = input.nextInt();
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
    static int check_appointments_list(Appointment[] appointment_array, int dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < appointment_array.length; i++) {
            if (appointment_array[i].getDoctor_ID() == dr_id
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
    public static int find_patient(Patient[] patients_array, int id) {
        for (int i = 0; i < patients_array.length; i++) {
            if (patients_array[i].getId() == id) {
                //if the patient regestered
                return i;
            }
        }
        //if not found
        return -1;
    }

    // method search for doctor by his ID and return its object if found, otherwise return null
    public static Doctor find_doctor(Doctor[] doctors_array, int id) {
        for (int i = 0; i < doctors_array.length; i++) {
            if (doctors_array[i].getId() == id) {
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
        int patientID = input.nextInt(), DoctorID = input.nextInt();
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

    // method to print the final statement and close the output file
    public static void Quit(PrintWriter output) {
        output.println("----------------------");
        System.out.println("----------------------");
        output.println("\nThank you for using Our clinic System, Good Bye!");
        System.out.println("\nThank you for using Our clinic System, Good Bye!");
        output.flush();
        output.close();
    }
}
