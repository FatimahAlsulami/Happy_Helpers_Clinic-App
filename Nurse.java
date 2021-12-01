package group_iar_5;

public class Nurse extends Staff {

    public Nurse() {
        super();
    }

    public Nurse(String name, String Id, String Phone, char Gender, String age, String Nationality, String address, double salary, String specialization) {
        super(salary, specialization, name, Id, Phone, Gender, age, Nationality, address);
    }

    public Nurse find_Nurse(String ID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Nurse && users.get(i).getId() == ID) {
                return (Nurse) users.get(i);
            }
        }
        return null;
    }

}
