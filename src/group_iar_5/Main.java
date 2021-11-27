package group_iar_5;

import static group_iar_5.Offers.checkOffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int pIndex = 0;
    static int aindex = 0;
    static ArrayList<Appointment> appointments_denlist = new ArrayList<>();
    static ArrayList<Appointment> appointments_derlist = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        // to read info from the file 
        // to read info from the file 
        File inputFile = new File("newInput.txt");
        Scanner StringInput = new Scanner(System.in);
        Scanner integerInput = new Scanner(System.in);
        Scanner input3 = new Scanner(inputFile);

        int total_number_of_doctor = input3.nextInt();
        int total_number_of_Nurse = input3.nextInt();
        int total_number_of_appointments = input3.nextInt();
        int total_number_of_Offers = input3.nextInt();
        // arrays to store clinic's patients, doctors, nurses, available appointments
//        Patient[] patients_array = new Patient[100];
        Doctor[] doctors_array = new Doctor[total_number_of_doctor];
        Nurse[] nurses_array = new Nurse[total_number_of_Nurse];
        Appointment[] appointments_array = new Appointment[total_number_of_appointments];
        Offers[] offers = new Offers[total_number_of_Offers];
        ArrayList<Patient> patients_list = new ArrayList<>();

        Appointment[] PaitentAppointment = new Appointment[2];

        System.out.println("---- Welcome to Happy Helpers Clinic ----\n\n");
        String command;
        while (input3.hasNext()) {
            command = input3.next();
            if (command.equalsIgnoreCase("SignUp_Doctor")) {
                SignUp_Doctor(doctors_array, input3);
            } else if (command.equals("Add_Appointment")) {
                Show_Appointment(total_number_of_appointments, appointments_denlist, appointments_derlist, doctors_array, input3);
            }

        }
        String cont = "Y";
        int choice;
        int choice2;
        do {
            DisplayMenuServices();
            choice = integerInput.nextInt();
            switch (choice) {
                case 1:
                    patients_list.add(signUp_Patient(patients_list, StringInput, integerInput));
                    System.out.println("Your password is: " + patients_list.get(0).getPassword());

                    break;
                case 2:
                    System.out.println("book appointment");
                    do {
                        displyAppointmentMenu();
                        choice2 = integerInput.nextInt();
                        switch (choice2) {
                            case 1:
                                System.out.println("Book for dentist");
                                AppointmentDEPdentis(appointments_denlist, doctors_array);
                                System.out.println("Choose appointment _________________");
                                int select = integerInput.nextInt();
                                Book_denappointment(select, appointments_denlist, cont, patients_list, doctors_array, PaitentAppointment);
                                break;
                            case 2:
                                System.out.println("Book for dermatologist");
                                AppointmentDetermnology(appointments_derlist, doctors_array);
                                System.out.println("Choose appointment _________________");
                                select = integerInput.nextInt();
                                Book_derappointment(select, appointments_derlist, cont, patients_list, doctors_array, PaitentAppointment);
                                break;

                            default:
                                System.out.print("ERROR, You'r choice wrong!!, try again: ");
                        }
                    } while (choice2 > 2 || choice2 < 1);

                    break;
                case 3:
                    System.out.println("cancel");
                    DisplayAppointment(PaitentAppointment, patients_list);
                    System.out.print("Choose appointemt to cancel it: ");
                    int select = integerInput.nextInt();
                    Cancel_denappointment(select, PaitentAppointment, patients_list, doctors_array, appointments_denlist, appointments_derlist, integerInput);
                    break;
                case 4:
                    System.out.println("display offer");
                    OffersMenu(integerInput);
                    break;
                case 5:
                    System.out.println("display recomendation");
                    break;
                case 6:
                    System.out.print("Enter your ID: ");
                    int loginID = integerInput.nextInt();
                    System.out.print("Enter your password: ");
                    String loginPassword = StringInput.next();
                    login(loginID, loginPassword, patients_list);
                    break;

                case 7:
                    System.out.println("Disply Appointment");
                    DisplayAppointment(PaitentAppointment, patients_list);
                    break;
                default:
                    System.out.print("ERROR, You'r choice wrong!!, try again : ");
            }
            System.out.print("Do you want another service? enter Y for yes, or N for no: ");
            cont = StringInput.next().toUpperCase();
        } while (cont.equals("Y"));
        pIndex++;
    }

    public static void SignUp_Doctor(Doctor[] doctors_array, Scanner input) {
        for (int i = 0; i < doctors_array.length; i++) {
            doctors_array[i] = new Doctor(input.next().replaceAll("_", " "), input.next(), input.next(),
                    input.next().charAt(0), input.nextInt(), input.next(), input.next(),
                    input.nextDouble(), input.next());
        }
    }

    public static Patient signUp_Patient(ArrayList<Patient> patients_array, Scanner input, Scanner input2) {
        System.out.println("Please fill the following information:");
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("ID: ");
        String id = input.next();
        while ((id.matches("[0-9]+") && id.length() == 10) == false) {
            System.out.print("Please try again, only 10 DIGITS is allowed! ");
            id = input.next();
        }
        System.out.print("Phone (Starting with 05): ");
        String phone = input.next();
        while ((phone.matches("[0-9]+") && phone.length() == 10 && phone.startsWith("05")) == false) {
            System.out.print("Please try again, only 10 DIGITS started with 05 is allowed! ");
            phone = input.next();
        }
        System.out.print("Gender (F for female and M for male): ");
        String gender = input.next() + "";
        while (((gender.equalsIgnoreCase("f") && gender.length() == 1) || (gender.equalsIgnoreCase("m") && gender.length() == 1)) == false) {
            System.out.print("Please try again, only f or m is allowed! ");
            gender = input.next().charAt(0) + "";
        }
        System.out.print("Age: ");
        int age = input2.nextInt();
        System.out.print("Nationality: ");
        String nationality = input2.next();
        System.out.print("Address: ");
        String address = input.next();

        return new Patient(name, id, phone, gender.charAt(0), age, nationality, address);
    }

    public static void DisplayMenuServices() {

        System.out.println("Choose from our services!");
        System.out.println("------------------------------------");
        System.out.println("1. SignUp \n"
                + "2. Book Appointment\n"
                + "3. Cancel Appointment\n"
                + "4. Display Offers\n"
                + "5. Disply Recomendations\n"
                + "6. Log in\n"
                + "7.Display Appointment");

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

    public static void displyAppointmentMenu() {
        System.out.println("Choose department");
        System.out.println("1. appointment dentis ");
        System.out.println("2. appointment determnology ");
    }

    public static void Show_Appointment(int total, ArrayList<Appointment> appointments_denlist, ArrayList<Appointment> appointments_derlist, Doctor[] doctors_array, Scanner input) {
        // fill the array
        for (int i = 0; i < total; i++) {
            Appointment a = new Appointment(input.next(), input.nextInt(), input.nextInt(), input.nextInt(), input.next());
            Doctor d = find_doctor(doctors_array, a.getDoctor_ID());
            if (d.getSpecialization().equalsIgnoreCase("dentist")) {
                appointments_denlist.add(a);
            } else if (d.getSpecialization().equalsIgnoreCase("dermatologist‏")) {
                appointments_derlist.add(a);
            }
        }
    }

    public static void AppointmentDEPdentis(ArrayList<Appointment> appointments_denlist, Doctor[] doctors_array) {
        int count = 0;
        System.out.println("The avalible appointment of dentist department:");
        System.out.println(appointments_denlist.size());
        for (int i = 0; i < appointments_denlist.size(); i++) {
            Doctor doctor = find_doctor(doctors_array, appointments_denlist.get(i).getDoctor_ID());
            if (appointments_denlist.get(i).isAvailable() == true) {
                System.out.println((count + 1) + ". Appointment with Dr. " + doctor.getName() + appointments_denlist.get(i).toString());
                count++;
            }

        }
    }

    public static void AppointmentDetermnology(ArrayList<Appointment> appointments_derlist, Doctor[] doctors_array) {
        int count = 0;
        System.out.println("The avalible appointment of determnology department:");
        for (int i = 0; i < appointments_derlist.size(); i++) {
            Doctor doctor = find_doctor(doctors_array, appointments_derlist.get(i).getDoctor_ID());
            String department = doctor.getSpecialization();
            if (department.equalsIgnoreCase("dermatologist‏") && appointments_derlist.get(i).isAvailable() == true) {
                System.out.println((count + 1) + ". Appointment with Dr. " + doctor.getName() + appointments_derlist.get(i).toString());
                count++;
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

    public static void Book_denappointment(int appointment_no, ArrayList<Appointment> appointments_denlist, String p_ID, ArrayList<Patient> patients_array, Doctor[] doctors_array, Appointment[] PaitentAppointment) {
        // to reserve new appointment, check that the patient have less than two reserved appointment in his record
        boolean ability_to_reserve = patients_array.get(pIndex).AddAppointment(appointments_denlist.get(appointment_no - 1));
        int i = check_denappointments_list(appointments_denlist, appointments_denlist.get(appointment_no - 1).getDoctor_ID(), appointments_denlist.get(appointment_no - 1).getYear(), appointments_denlist.get(appointment_no - 1).getMonth(), appointments_denlist.get(appointment_no - 1).getDay(), appointments_denlist.get(appointment_no - 1).getAppointmentTime());
        if (ability_to_reserve == false) {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + " your booking is failed, you already have two reserved appointments");
        } else if (ability_to_reserve == true && appointments_denlist.get(i).isAvailable() == true) {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + ", the appointment is available, please pay the bill to confirm it.");
            appointments_denlist.get(i).setAvailable(false);
            PaitentAppointment[aindex++] = appointments_denlist.get(i);
        } else {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + ", the appointment is not available, please choose another.");

        }
    }

    public static void Book_derappointment(int appointment_no, ArrayList<Appointment> appointments_derlist, String p_ID, ArrayList<Patient> patients_array, Doctor[] doctors_array, Appointment[] PaitentAppointment) {
        // to reserve new appointment, check that the patient have less than two reserved appointment in his record
        boolean ability_to_reserve = patients_array.get(pIndex).AddAppointment(appointments_derlist.get(appointment_no - 1));
        int i = check_denappointments_list(appointments_derlist, appointments_derlist.get(appointment_no - 1).getDoctor_ID(), appointments_derlist.get(appointment_no - 1).getYear(), appointments_derlist.get(appointment_no - 1).getMonth(), appointments_derlist.get(appointment_no - 1).getDay(), appointments_derlist.get(appointment_no - 1).getAppointmentTime());
        if (ability_to_reserve == false) {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + " your booking is failed, you already have two reserved appointments");
        } else if (ability_to_reserve == true && appointments_derlist.get(i).isAvailable() == true) {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + ", the appointment is available, please pay the bill to confirm it.");
            appointments_derlist.get(i).setAvailable(false);
            PaitentAppointment[aindex++] = appointments_derlist.get(i);
        } else {
            System.out.println("Dear " + patients_array.get(pIndex).getName() + ", the appointment is not available, please choose another.");

        }
    }

    public static void Cancel_denappointment(int appointment_no, Appointment[] patient_appointments, ArrayList<Patient> patient_array, Doctor[] doctors_array, ArrayList<Appointment> appointments_denlist, ArrayList<Appointment> appointments_derlist, Scanner integerInput) {
        int pai = SearchdenAppointment(patient_appointments[appointment_no - 1], patient_appointments);
        Appointment a = patient_appointments[appointment_no - 1];
        Doctor d = find_doctor(doctors_array, a.getDoctor_ID());
        if (d.getSpecialization().equalsIgnoreCase("dermatologist‏")) {
            int check = check_derappointments_list(appointments_derlist, a.getDoctor_ID(), a.getYear(), a.getMonth(), a.getDay(), a.getAppointmentTime());
            patient_array.get(0).delete_appointment(pai);
            aindex--;
            // change the canceled appointment's status to available, so any patient can reserve it now
            appointments_denlist.get(check).setAvailable(true);
        } else if (d.getSpecialization().equalsIgnoreCase("dentist")) {
            int check = check_denappointments_list(appointments_denlist, a.getDoctor_ID(), a.getYear(), a.getMonth(), a.getDay(), a.getAppointmentTime());
            patient_array.get(0).delete_appointment(pai);
            aindex--;
            // change the canceled appointment's status to available, so any patient can reserve it now
            appointments_denlist.get(check).setAvailable(true);
        }
        System.out.println("Dear " + patient_array.get(0).getName() + ", your appointment is canceled successfully.");
    }

    public static int SearchdenAppointment(Appointment appointment, Appointment[] patient_appointments) {
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


    public static int SearchderAppointment(Appointment appointment, Appointment[] patient_appointments) {
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
// method search for certain appointments if exist in the list or not, if exist return its index, otherwise return -1

    static int check_derappointments_list(ArrayList<Appointment> denappointmets, String dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < denappointmets.size(); i++) {
            if (Integer.parseInt(dr_id) == Integer.parseInt(denappointmets.get(i).getDoctor_ID())
                    && denappointmets.get(i).getDay() == day && denappointmets.get(i).getMonth() == month
                    && denappointmets.get(i).getYear() == Year
                    && denappointmets.get(i).getAppointmentTime().equalsIgnoreCase(time)) {
                // if the appointment found
                return i;
            }
        }
        // otherwise return -1
        return -1;
    }

    static int check_denappointments_list(ArrayList<Appointment> derappointmets, String dr_id, int Year, int month, int day, String time) {
        for (int i = 0; i < derappointmets.size(); i++) {
            if (Integer.parseInt(dr_id) == Integer.parseInt(derappointmets.get(i).getDoctor_ID())
                    && derappointmets.get(i).getDay() == day && derappointmets.get(i).getMonth() == month
                    && derappointmets.get(i).getYear() == Year
                    && derappointmets.get(i).getAppointmentTime().equalsIgnoreCase(time)) {
                // if the appointment found
                return i;
            }
        }
        // otherwise return -1
        return -1;
    }

    public static int find_appointment_index(int choice, Appointment[] appointments_array, Doctor[] doctors_array) {
        int count = 1;
        for (int i = 0; i < appointments_array.length; i++) {
            Doctor doctor = find_doctor(doctors_array, appointments_array[i].getDoctor_ID());
            String department = doctor.getSpecialization();
            if (department.equalsIgnoreCase("dentist") && appointments_array[i].isAvailable() && count == choice) {
                return i;
            }
            count++;
        }
        return -1;
    }

    private static boolean validation(int ID, String password, ArrayList<Patient> p) {
        for (int i = 0; i < p.size(); i++) {
            if (Integer.parseInt(p.get(i).getId()) == ID && p.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean login(int ID, String password, ArrayList<Patient> p) {
        boolean exist_account = validation(ID, password, p);
        if (exist_account == true) {
            System.out.println("Yor are logged in successfully");
            return true;
        }
        System.out.println("You are not regester, please sign up!");
        return false;
    }

    public static void DisplayAppointment(Appointment[] PaitentAppointment, ArrayList<Patient> p) {
        int count = 0;
        PaitentAppointment = p.get(0).getAppointments_array();
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
}
