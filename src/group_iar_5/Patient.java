package group_iar_5;

import java.util.ArrayList;

public class Patient extends User {

//
//    private static int file_number = 100000;
//    private int patient_file_number;
    private int number_of_appointments = 0;
    static Appointment[] appointments_array = new Appointment[2];
    private String Diagnosis;
    static int appointmentID = 1;
    private int appointment_ID;
    private String address = null;

    private int PatientId = 0;

    public Patient() {
        super();
    }

    public Patient(String name, String Id, String Phone, char Gender, int age, String Nationality, String address) {
        super(name, Id, Phone, Gender, age, Nationality, address);
//        patient_file_number = file_number++;
//        this.Diagnosis = Diagnosis;
//        this.PatientId=Id;
//        users.add(this);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    public String getDiagnosis() {
        return this.Diagnosis;
    }

    public static Appointment [] getAppointments_array() {
        return appointments_array;
    }
    

//    public int getPatient_file_number() {
//        return patient_file_number;
//    }
//
//    public void setPatient_file_number(int patient_file_number) {
//        this.patient_file_number = patient_file_number;
//    }

    public int getNumber_of_appointments() {
        return number_of_appointments;
    }

    public boolean AddAppointment(Appointment appointment) {
        boolean flag = false;
        if (number_of_appointments >= 2) {
            flag = false;
        } else {
            appointments_array[number_of_appointments] = new Appointment(appointment.getDoctor_ID(), 
                    appointment.getYear(), appointment.getMonth(), appointment.getDay(), appointment.getAppointmentTime());
            number_of_appointments++;
            flag = true;
        }
        return flag;
    }


    
    public void delete_appointment(int index) {
        appointments_array[index].setAvailable(true);
        this.appointments_array[index] = null;
        number_of_appointments--;

        if (index == 0) {
            appointments_array[0] = appointments_array[1];
            appointments_array[1] = null;
        }

    }
}
