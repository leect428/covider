package usc.covider.cs310;

public class Survey {
    public String date;
    public boolean hasCovid;
    public boolean hasSymptom;
    public boolean hasContact;
    public String userID;

    public Survey(boolean hc, boolean hs, boolean hc2, String d, String u){
        hasCovid = hc;
        hasSymptom = hs;
        hasContact = hc2;
        date = d;
        userID = u;

    }
    public Survey(){

    }
}
