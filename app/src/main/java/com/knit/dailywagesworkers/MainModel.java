package com.knit.dailywagesworkers;

public class MainModel {

    String name,  profession,address,phone, surl;

    MainModel(){

    }

    public MainModel(String name, String profession, String surl, String phone,String address) {
        this.name = name;
        this.profession = profession;
        this.address=address;
        this.phone=phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }



}
