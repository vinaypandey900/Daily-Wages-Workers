package com.knit.dailywagesworkers;

public class firebaseAgentData {

    String name;
    String username;
    String email;
    String phone;
    String address;
    String shopName;
    String license;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public firebaseAgentData(String name, String username, String email, String phone, String address, String shopName, String license) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address=address;
        this.shopName=shopName;
        this.license=license;
    }



    public firebaseAgentData() {
    }
}
