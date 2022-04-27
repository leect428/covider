package usc.covider.cs310;

import java.util.List;

public class Survey {
    public long date;
    public boolean hasCovid;
    public boolean hasSymptom;
    public boolean hasContact;
    public String userID;
    public List<String> buildings;

    public Survey(boolean hc, boolean hs, boolean hc2, long d, String u, List<String> b){
        hasCovid = hc;
        hasSymptom = hs;
        hasContact = hc2;
        date = d;
        userID = u;
        buildings = b;

    }
    public Survey(){

    }
}
