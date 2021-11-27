package group_iar_5;

public class Doctor extends Staff {
    private String [] diagnosis;
    
     public Doctor(){
        super();
    }

//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int Id) {
//        this.Id = Id;
//    }
    public Doctor(String name, String Id, String Phone, char Gender, int age, String Nationality,String address,double salary, String specialization) {
        super(salary, specialization, name, Id, Phone, Gender, age, Nationality,address);
    }
    
    public Doctor find_doctor(String ID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Doctor && users.get(i).getId()== ID) {
                return (Doctor)users.get(i);
            }
        }
        return null;
    }

//    public String getDiagnosis() {
//        return diagnosis;
//    }
//
//    public void setDiagnosis(String diagnosis) {
//        this.diagnosis = diagnosis;
//    }
    
}
