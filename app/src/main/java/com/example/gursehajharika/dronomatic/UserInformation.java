package com.example.gursehajharika.dronomatic;


class Userinformation  {

    private String name;
    private String email;
    private String password;
    private String productID;
    private String userID;

    public Userinformation(){



    }

    public Userinformation(String email, String name, String password, String productID,String userID) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.productID = productID;
        this.userID = userID;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUserID(){

        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
