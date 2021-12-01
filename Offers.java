
package group_iar_5;


public class Offers {
    
    int OffersID;
    String Content; 
    
    public Offers(){
        
    }
    public Offers(int id,String Content){
        this.Content=Content;
        this.OffersID=id;
    }

    public int getOffersID() {
        return OffersID;
    }

    public void setOffersID(int OffersID) {
        this.OffersID = OffersID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    
    public static boolean checkOffer(int choice){
        if (choice == 1 || choice == 2 || choice == 3)
            return true;
        else
            return false;
    }
    
}
