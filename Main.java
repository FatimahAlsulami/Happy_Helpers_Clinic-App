package group_iar_5;

import static group_iar_5.Offers.checkOffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// HAPPY HELPERS CLINIC SYSTEM 
public class Main {

    static int patientIndex = 0;
    static int appointmentIndex = 0;
    static int offerIndex = 0;

    static ArrayList<Appointment> dentisList = new ArrayList<>();
    static ArrayList<Appointment> dermatologyList = new ArrayList<>();
    static ArrayList<Offers> offersList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        // to read info from the file 
        File inputFile = new File("newInput.txt");
        Scanner StringInput = new Scanner(System.in);
        Scanner integerInput = new Scanner(System.in);
        Scanner fileInput = new Scanner(inputFile);

        int total_number_of_doctor = fileInput.nextInt();
        int total_number_of_Nurse = fileInput.nextInt();
        int total_number_of_appointments = fileInput.nextInt();
        int total_number_of_Offers = fileInput.nextInt();

        // arrays to store clinic's patients, doctors, nurses, available appointments
        Doctor[] doctors_array = new Doctor[total_number_of_doctor];
        Nurse[] nurses_array = new Nurse[total_number_of_Nurse];
        ArrayList<Patient> patients_list = new ArrayList<>();
        Appointment[] PaitentAppointment = new Appointment[2];

        try {

            System.out.println("---- Welcome to Happy Helpers Clinic ----\n");
            String command;
            while (fileInput.hasNext()) {
                command = fileInput.next();
                if (command.equalsIgnoreCase("SignUp_Doctor")) {
                    SignUp_Doctor(doctors_array, fileInput);
                } else if (command.equalsIgnoreCase("SignUp_Nurse")) {
                    SignUp_Nurse(nurses_array, fileInput);
                } else if (command.equals("Add_Appointment")) {
                    Show_Appointment(total_number_of_appointments, dentisList, dermatologyList, doctors_array, fileInput);
                } else if (command.equals("Add_offers")) {
                    for (int i = 0; i < total_number_of_Offers; i++) {
                        offersList.add(new Offers(fileInput.nextInt(), fileInput.nextLine().trim()));
                    }
                }
            }

            String complete = "Y";

            int service_choice, department_choice;
            do {
                // Display services
                DisplayMenuServices();
                // Take user's choice
                System.out.print("\nEnter the number of service that you want: ");
                System.lineSeparator();
                service_choice = integerInput.nextInt();
                switch (service_choice) {
                    // If user choose sign up
                    case 1:

                        System.out.println("\n----------- Sign up page -----------");
                        // Invoke signUp_Patient method to create patient's object and add it to the arraylist
                        patients_list.add(signUp_Patient(patients_list, StringInput, integerInput));
                        System.out.println("\nDear " + patients_list.get(patientIndex).getName() + ", your password is: " + patients_list.get(0).getPassword()
                                + ". You will need it later to log in.");

                        break;
                    // If user choose log in
                    case 2:

                        System.out.println("\n----------- Log in page -----------");
                        // Take user's log in information
                        System.out.print("Enter your ID: ");
                        int loginID = integerInput.nextInt();
                        System.out.print("Enter your password: ");
                        String loginPassword = StringInput.next();
                        // Invoke login method
                        login(loginID, loginPassword, patients_list);

                        break;
                    // If user choose book appointment
                    case 3:
                        // Check that the patient is logged in
                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- BOOK APPOINTMENT ----------- ");
                            do {

                                System.out.println("\nThe available departments are:"
                                        + "\n1. Dentis department"
                                        + "\n2. Dermatology department");
                                System.out.print("\nPlease enter the department number:");
                                // Take choosen department by the user
                                department_choice = integerInput.nextInt();
                                switch (department_choice) {
                                    // In case the chosen dpartment was dentis
                                    case 1:
                                        System.out.print("\nBOOK FOR DENTIST");
                                        //Display all available appointments so user can choose from
                                        displayAppointmentsInDentisDept(dentisList, doctors_array);
                                        // Take user choice
                                        System.out.print("\nChoose Appointment Number: ");
                                        int selection = integerInput.nextInt();
                                        System.lineSeparator();
                                        // Book the choosen appointment for the user
                                        BookDentisAppointment(selection, dentisList, complete, patients_list, doctors_array, PaitentAppointment);
                                        break;
                                    // In case the chosen dpartment was dermatology
                                    case 2:
                                        System.out.println("\nBOOK FOR DERMATOLOGIST");
                                        //Display all available appointments so user can choose from
                                        displayAppointmentsInDermatologyDept(dermatologyList, doctors_array);
                                        System.out.print("\nChoose Appointment Number: ");
                                        selection = integerInput.nextInt();
                                        System.lineSeparator();
                                        // Book the choosen appointment for the user
                                        BookDermatologyAppointment(selection, dermatologyList, complete, patients_list, doctors_array, PaitentAppointment);
                                        break;

                                    default:
                                        System.out.print("\nERROR, Your choice is wrong! please try again: ");
                                }
                            } while (department_choice > 2 || department_choice < 1);

                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose cancel appointment
                    case 4:

                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- CANCEL APPOINTMENT -----------\n");
                            displayReservedAppointment(PaitentAppointment, patients_list);
                            System.out.print("\nChoose Appointment Number to cancel it: ");
                            int selection = integerInput.nextInt();
                            // Incoke the appropirate method to cancel the selected appointment
                            CancelAppointment(selection, PaitentAppointment, patients_list, doctors_array, dentisList, dermatologyList, integerInput);
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose display offers
                    case 5:
                   // Display all offers that avalible in clinic system 
                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- DISPLAY OFFERS -----------\n");
                            for (int i = 0; i < offersList.size(); i++) {
                                System.out.println(offersList.get(i).getOffersID() + ". " + offersList.get(i).getContent());
                            }

                            // Invoke the method reserve the selected offer 
                            Reserve_Offer(integerInput);
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose display recommendation (still under processing)
                    case 6:

                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- DISPLAY RECOMMENDATIONS -----------");
                            System.out.println("\nDear , this service under development it will be avalible soon :) ");
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose display appointments
                    case 7:

                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- DISPLAY APPOINTMENTS -----------\n");
                            displayReservedAppointment(PaitentAppointment, patients_list);
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose display x-ray results (still under processing)
                    case 8:

                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- DISPLAY X-RAY RESULTS -----------");
                            System.out.println("\nDear " + patients_list.get(patientIndex).getName() + ", this service under development it will be avalible soon :) ");
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;
                    // If user choose display test results (still under processing)
                    case 9:

                        if (!patients_list.isEmpty()) {
                            System.out.println("\n----------- DISPLAY TEST RESULTS -----------");
                            System.out.println("\nDear " + patients_list.get(patientIndex).getName() + ", this service under development it will be avalible soon :) ");
                        } else {
                            System.out.println("\nYou must register or log in!");
                        }
                        break;

                    default:
                        System.out.print("\nERROR, Your choice is wrong!, please try again: ");
                        System.lineSeparator();
                }

                System.out.print("\nDo you want another service? enter Y for yes, or N for no: ");
                System.lineSeparator();
                System.lineSeparator();
                complete = StringInput.next().toUpperCase();

            } while (complete.equals("Y"));
            patientIndex++;

        } catch (Exception ex) {
            System.out.println("PLEASE CHECK YOUR INPUT AND TRY AGAIN! ");
        }

    }
    // Method that add doctors information from the file

    public static void SignUp_Doctor(Doctor[] doctors_array, Scanner input) {
        for (int i = 0; i < doctors_array.length; i++) {
            doctors_array[i] = new Doctor(input.next().replaceAll("_", " "), input.next(), input.next(),
                    input.next().charAt(0), input.next(), input.next(), input.next(),
                    input.nextDouble(), input.next());
        }
    }

    // Method that search for a doctor and return his index
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

    // Method that add nurses information from the file
    public static void SignUp_Nurse(Nurse[] nursess_array, Scanner input) {
        for (int i = 0; i < nursess_array.length; i++) {
            nursess_array[i] = new Nurse(input.next().replaceAll("_", " "), input.next(), input.next(),
                    input.next().charAt(0), input.next(), input.next(), input.next(),
                    input.nextDouble(), input.next());
        }
    }

    // Method that search for a nurse and return his index
    public static Nurse find_nurse(Nurse[] nursess_array, String id) {
        for (int i = 0; i < nursess_array.length; i++) {
            if (nursess_array[i].getId().equals(id)) {
                //if the doctor regestered
                return nursess_array[i];
            }
        }
        //if not found
        return null;
    }

    // this method check if string contain only characters 
    public static boolean validtaeString(String str) {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }
        return true;
    }

    // Method to sign up new patient
    public static Patient signUp_Patient(ArrayList<Patient> patients_array, Scanner input, Scanner input2) {
        System.out.println("\nPlease fill the following information:");
        System.out.print("Name: ");
        String name = input.next();
        boolean check_name = validtaeString(name);
        while (check_name == false) {
            System.out.print("Please try again, only String is allowed! ");
            name = input.next();
            check_name = validtaeString(name);

        }

        System.out.print("ID: ");
        String id = input.next();
        // To insure that the patient entered a real ID
        while ((id.matches("[0-9]+") && (id.length() == 10)) == false) {
            System.out.print("Please try again, only 10 DIGITS is allowed! ");
            id = input.next();
        }
        System.out.print("Phone (Starting with 05): ");
        String phone = input.next();
        //To insure that the patient entered a real phone number
        while ((phone.matches("[0-9]+") && phone.length() == 10 && phone.startsWith("05")) == false) {
            System.out.print("Please try again, only 10 DIGITS started with 05 is allowed! ");
            phone = input.next();
        }
        System.out.print("Gender (F for female and M for male): ");
        String gender = input.next();

        while (((gender.equalsIgnoreCase("f") && gender.length() == 1)
                || (gender.equalsIgnoreCase("m") && gender.length() == 1)) == false) {
            System.out.print("Please try again, only f or m is allowed! ");
            gender = input.next();

        }
        System.out.print("Age: ");
        String age = input.next();
        boolean check_Age = age.matches("[0-9]+");
        while (check_Age == false) {
            System.out.print("Please try again, only Number allowed! ");
            age = input.next();
            check_Age = age.matches("[0-9]+");
        }

        System.out.print("Nationality: ");
        String nationality = input2.next();

        boolean check_nationality = validtaeString(nationality);
        while (check_nationality == false) {
            System.out.print("Please try again, only String is allowed! ");
            nationality = input.next();
            check_nationality = validtaeString(nationality);
        }

        System.out.print("Address: ");
        String address = input.next();

        boolean check_address = validtaeString(address);
        while (check_address == false) {
            System.out.print("Please try again, only String is allowed! ");
            address = input.next();
            check_address = validtaeString(address);
        }

        // create new object with patient's information and return it
        return new Patient(name, id, phone, gender.charAt(0), age, nationality, address);
    }

    // Method that display the offered services
    public static void DisplayMenuServices() {
        System.out.println("------------------------------------");
        System.out.println("Choose from our services!");
        System.out.println("------------------------------------");
        System.out.println("1. Sign up \n"
                + "2. Log in\n"
                + "3. Book Appointment\n"
                + "4. Cancel Appointment\n"
                + "5. Display Offers\n"
                + "6. Display Recomendations\n"
                + "7. Display Reserved Appointments\n"
                + "8. Disply X-RAY Results\n"
                + "9. Disply test Results");
        System.out.println("------------------------------------");

    }

    // Method that reserve the selected offer
    public static void Reserve_Offer(Scanner input) {
        System.out.print("\n Choose the number of offer you want: ");
        int choice = 0;
        boolean offerAvailability = checkOffer(choice);
        while (offerAvailability == false) {
            
            choice = input.nextInt();
            offerAvailability = checkOffer(choice);

            if (offerAvailability) {
                for (int i = 0; i < offersList.size(); i++) {
                    if ((i + 1) == choice) {
                        System.out.println("You have " + offersList.get(i).getContent().substring(0, 15)
                        +", it will be deducted from the payment amount.");
                    }
                }
            } else {
                System.out.print("ERROR, Your choice is wrong! please try again: ");
            }
        }

    }

    // Method that add available appointments to display them later
    public static void Show_Appointment(int total, ArrayList<Appointment> appointments_denlist, ArrayList<Appointment> appointments_derlist, Doctor[] doctors_array, Scanner input) {
        // fill the array
        for (int i = 0; i < total; i++) {
            Appointment appointment = new Appointment(input.next(), input.nextInt(), input.nextInt(), input.nextInt(), input.next());
            Doctor doctor = find_doctor(doctors_array, appointment.getDoctor_ID());
            if (doctor.getSpecialization().equalsIgnoreCase("dentist")) {
                appointments_denlist.add(appointment);
            } else if (doctor.getSpecialization().equalsIgnoreCase("dermatologist‏")) {
                appointments_derlist.add(appointment);
            }
        }
    }

    // Method that displays patient's appointment
    public static void displayReservedAppointment(Appointment[] PaitentAppointment, ArrayList<Patient> patientsList) {
        int count = 0;
        PaitentAppointment = patientsList.get(0).getAppointments_array();
        for (int i = 0; i < PaitentAppointment.length; i++) {
            if (PaitentAppointment[i] != null) {
                System.out.println((count + 1) + ". Appointment " + PaitentAppointment[i].toString());
            } else if (PaitentAppointment[0] == null && PaitentAppointment[1] == null) {
                System.out.println("You don't have any reserved appointment!");
                break;
            }

            count++;
        }
    }

    // Method that display available appointments in dentis department
    public static void displayAppointmentsInDentisDept(ArrayList<Appointment> appointments_denlist, Doctor[] doctors_array) {
        int count = 0;
        System.out.println("The avalible appointment of dentist department:");

        for (int i = 0; i < appointments_denlist.size(); i++) {
            Doctor doctor = find_doctor(doctors_array, appointments_denlist.get(i).getDoctor_ID());
            if (appointments_denlist.get(i).isAvailable() == true) {
                System.out.println((++count) +". Appointment with Dr. " + doctor.getName() + appointments_denlist.get(i).toString());
            }

        }
    }

    // Method that display available appointments in dermatology department
    public static void displayAppointmentsInDermatologyDept(ArrayList<Appointment> appointments_derlist, Doctor[] doctors_array) {
        int count = 0;
        System.out.println("The avalible appointment of determnology department:");

        for (int i = 0; i < appointments_derlist.size(); i++) {
            Doctor doctor = find_doctor(doctors_array, appointments_derlist.get(i).getDoctor_ID());
            String department = doctor.getSpecialization();
            if (department.equalsIgnoreCase("dermatologist‏") && appointments_derlist.get(i).isAvailable() == true) {
                System.out.println((++count) + ". Appointment with Dr. " + doctor.getName() + appointments_derlist.get(i).toString());
            }

        }
    }

    // Method that used to book appointment in dentis department
    public static void BookDentisAppointment(int appointment_no, ArrayList<Appointment> appointments_denlist, String p_ID, ArrayList<Patient> patients_array, Doctor[] doctors_array, Appointment[] PaitentAppointment) {
        // to reserve new appointment, check that the patient have less than two reserved appointment in his record
        boolean ability_to_reserve = patients_array.get(patientIndex).AddAppointment(appointments_denlist.get(appointment_no - 1));
        int index = checkDentisList(appointments_denlist, appointments_denlist.get(appointment_no - 1).getDoctor_ID(), appointments_denlist.get(appointment_no - 1).getYear(), appointments_denlist.get(appointment_no - 1).getMonth(), appointments_denlist.get(appointment_no - 1).getDay(), appointments_denlist.get(appointment_no - 1).getAppointmentTime());
        if (ability_to_reserve == false) {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + " your booking is failed, you already have two reserved appointments");
        } else if (ability_to_reserve == true && appointments_denlist.get(index).isAvailable() == true) {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + ", the appointment is available, please pay the bill to confirm it.");
            appointments_denlist.get(index).setAvailable(false);
            PaitentAppointment[appointmentIndex++] = appointments_denlist.get(index);
        } else {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + ", the appointment is not available, please choose another.");
        }
    }

    // Method that used to book appointment in dermatology department
    public static void BookDermatologyAppointment(int appointment_no, ArrayList<Appointment> appointments_derlist, String p_ID, ArrayList<Patient> patients_array, Doctor[] doctors_array, Appointment[] PaitentAppointment) {
        // to reserve new appointment, check that the patient have less than two reserved appointment in his record
        boolean ability_to_reserve = patients_array.get(patientIndex).AddAppointment(appointments_derlist.get(appointment_no - 1));
        int index = checkDentisList(appointments_derlist, appointments_derlist.get(appointment_no - 1).getDoctor_ID(), appointments_derlist.get(appointment_no - 1).getYear(), appointments_derlist.get(appointment_no - 1).getMonth(), appointments_derlist.get(appointment_no - 1).getDay(), appointments_derlist.get(appointment_no - 1).getAppointmentTime());
        if (ability_to_reserve == false) {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + " your booking is failed, you already have two reserved appointments");
        } else if (ability_to_reserve == true && appointments_derlist.get(index).isAvailable() == true) {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + ", the appointment is available, please pay the bill to confirm it.");
            appointments_derlist.get(index).setAvailable(false);
            PaitentAppointment[appointmentIndex++] = appointments_derlist.get(index);
        } else {
            System.out.println("Dear " + patients_array.get(patientIndex).getName() + ", the appointment is not available, please choose another.");

        }
    }

    // Method that canceled the passed appointment
    public static void CancelAppointment(int appointment_no, Appointment[] patient_appointments, ArrayList<Patient> patient_array, Doctor[] doctors_array, ArrayList<Appointment> dentisList, ArrayList<Appointment> dermatologyList, Scanner integerInput) {
        // Get reserved appointment index
        int index = SearchReservedAppointment(patient_appointments[appointment_no - 1], patient_appointments);
        // Store the appointment information to copmare it
        Appointment appointment = patient_appointments[appointment_no - 1];
        // Get doctor object
        Doctor doctor = find_doctor(doctors_array, appointment.getDoctor_ID());
        if (doctor.getSpecialization().equalsIgnoreCase("dermatologist‏")) {
            int dermatologyIndex = checkDermatologyList(dermatologyList, appointment.getDoctor_ID(), appointment.getYear(), appointment.getMonth(), appointment.getDay(), appointment.getAppointmentTime());
            patient_array.get(0).delete_appointment(index);
            appointmentIndex--;
            // change the canceled appointment's status to available, so any patient can reserve it now
            dentisList.get(dermatologyIndex).setAvailable(true);
        } else if (doctor.getSpecialization().equalsIgnoreCase("dentist")) {
            int dentisIndex = checkDentisList(dentisList, appointment.getDoctor_ID(), appointment.getYear(), appointment.getMonth(), appointment.getDay(), appointment.getAppointmentTime());
            patient_array.get(0).delete_appointment(index);
            appointmentIndex--;
            // change the canceled appointment's status to available, so any patient can reserve it now
            dentisList.get(dentisIndex).setAvailable(true);
        }
        System.out.println("Dear " + patient_array.get(0).getName() + ", your appointment is canceled successfully.");
    }

    // Method that search for certain appointment in patient's appointments list and return its index
    public static int SearchReservedAppointment(Appointment appointment, Appointment[] patient_appointments) {
        int x = -1;
        for (int i = 0; i < patient_appointments.length; i++) {
            if (patient_appointments[i] != null
                    && patient_appointments[i].getDay() == appointment.getDay()
                    && patient_appointments[i].getMonth() == appointment.getMonth()
                    && patient_appointments[i].getYear() == appointment.getYear()
                    && patient_appointments[i].getAppointmentTime().equalsIgnoreCase(appointment.getAppointmentTime())) {

                x = i;

            }
        }
        return x;
    }

    // method that search for certain appointments if exist in the list or not, if exist return its index, otherwise return -1
    public static int checkDermatologyList(ArrayList<Appointment> dermatologyList, String dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < dermatologyList.size(); i++) {
            if (Integer.parseInt(dr_id) == Integer.parseInt(dermatologyList.get(i).getDoctor_ID())
                    && dermatologyList.get(i).getDay() == day && dermatologyList.get(i).getMonth() == month
                    && dermatologyList.get(i).getYear() == Year
                    && dermatologyList.get(i).getAppointmentTime().equalsIgnoreCase(time)) {
                // if the appointment found
                return i;
            }
        }
        // otherwise return -1
        return -1;
    }

    // method that search for certain appointments if exist in the list or not, if exist return its index, otherwise return -1
    public static int checkDentisList(ArrayList<Appointment> dentisList, String dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < dentisList.size(); i++) {
            if (Integer.parseInt(dr_id) == Integer.parseInt(dentisList.get(i).getDoctor_ID())
                    && dentisList.get(i).getDay() == day && dentisList.get(i).getMonth() == month
                    && dentisList.get(i).getYear() == Year
                    && dentisList.get(i).getAppointmentTime().equalsIgnoreCase(time)) {
                // if the appointment found
                return i;
            }
        }
        // otherwise return -1
        return -1;
    }

    // Method that check log in innformation's validation
    private static boolean validation(int ID, String password, ArrayList<Patient> patientsList) {
        for (int i = 0; i < patientsList.size(); i++) {
            if (Integer.parseInt(patientsList.get(i).getId()) == ID && patientsList.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method that used to process log in service
    public static boolean login(int ID, String password, ArrayList<Patient> patientsList) {
        boolean exist_account = validation(ID, password, patientsList);
        if (exist_account == true) {
            System.out.println("Yor are logged in successfully");
            return true;
        }
        System.out.println("You are not regester, please sign up!");
        return false;
    }

}
