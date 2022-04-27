package usc.covider.cs310;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String email;
    public String password;
    public List<String> buildings;

    public User(String e, String p){
        email = e;
        password = p;
        buildings = new ArrayList<>();
    }

    public User(){

    }

    public void setBuildings(List<String> buildings) {
        this.buildings = buildings;
    }
}
