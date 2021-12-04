package group_iar_5;

import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private int Year;
    private int Month;
    private int Day;
    private String patient_ID;
    private String doctor_ID;
    private String AppointmentTime;    
    private boolean Available = true;
    
    //contractor
    Appointment() {

    }

    Appointment(String doctorID, int Year, int Month, int Day, String AppointmentTime) {
        this.doctor_ID = doctorID;
        this.Year = Year;
        this.Month = Month;
        this.Day = Day;
        this.AppointmentTime = AppointmentTime;
//        patient_ID = p_ID;
//        doctor_ID = d_ID;
//        Patient.appointmentID = AppointmentID;
    }
  
    public void setYear(int Year) {
        this.Year = Year;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public void setDay(int Day) {
        this.Day = Day;
    }

    public void setAppointmentTime(String AppointmentTime) {
        this.AppointmentTime = AppointmentTime;
    }

    public int getYear() {
        return Year;
    }

    public int getMonth() {
        return Month;
    }

    public int getDay() {
        return Day;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public String getPatient_ID() {
        return patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        this.patient_ID = patient_ID;
    }

    public String getDoctor_ID() {
        return doctor_ID;
    }

    public void setDoctor_ID(String doctor_ID) {
        this.doctor_ID = doctor_ID;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean Available) {
        this.Available = Available;
    }

}
