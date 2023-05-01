package com.knit.dailywagesworkers;

public class homeRvMainModel {

    String name;
    String profession;
    String address;
    String surl;

    homeRvMainModel(){

    }

    public homeRvMainModel(String name, String profession, String address,String surl) {
        this.name = name;
        this.profession = profession;
        this.address=address;
        this.surl = surl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }



}
