package group_iar_5;

public abstract class Staff extends User {

    private double salary;
    private String specialization;

    
    public Staff()
    {
        
    }
    public Staff(double salary, String specialization, String name, String Id, String Phone, char Gender, int age, String Nationality,String address) {
        super(name, Id, Phone, Gender, age, Nationality, address);
        this.salary = salary;
        this.specialization = specialization;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
