package com.example.universitybazaarsystem;

public class ClubModel {
    private String clubName,contact,des;
public ClubModel(){

}
    public ClubModel(String clubName, String contact, String des) {
        this.clubName = clubName;
        this.contact = contact;
        this.des = des;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
