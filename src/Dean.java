import java.util.ArrayList;

public class Dean {

    private int deanID;
    private String deanName;


    public Dean() {
    }

    public Dean(int deanID, String deanName){
        this.setDeanID(deanID);
        this.setDeanName(deanName);
    }

  

    public int getDeanID(){
        return deanID;
    }

    public String getDeanName(){
        return deanName;
    }

    public void setDeanID(int deanID){
        this.deanID=deanID;
    }

    public void setDeanName(String deanName){
        this.deanName = deanName;
    }

    public static Dean findDean(ArrayList<Dean> deans, int id){
        for(Dean dean: deans){
            if(dean.getDeanID()==id){
                return dean;
            }
        }
        return null;
    }



}
