package com.example.vaibhav.databasetest1.database;


public class DataProvider {

    private String name;
    private String pass;
    private String email;
    private String pno;
    private String grp;

    public  DataProvider(String email ,String name,String pno,String grp){
        this.name=name;
        this.pno=pno;
        this.grp=grp;
        this.email=email;
    }

    public String getName() {
        return name;
    }
    public String getPno() {
        return pno;
    }
    public String getGrp() {
        return grp;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
