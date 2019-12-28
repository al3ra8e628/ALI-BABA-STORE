package com.company.alibaba.entities;

import java.io.Serializable;

/**
 * Created by al3ra8e on 4/25/2018.
 */

public class User  implements Serializable{
    private String id ;
    private String firstName  ;
    private String lastName ;
    private String email ;
    private String password ;
    private int gender ;
    private String username ;

    private String city ;
    private String country ;
    private String phoneNumber ;
    private String houseNumber ;
    private String streetNumber ;
    private String zipCode ;




    public User() {
    }

    public User(String id, String firstName, String lastName, String email, String password, String city, String country, String phoneNumber, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }


    public int getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equals("female")){
            this.gender = 1  ;
        }else if(gender.equals("male")){
            this.gender = 0 ;
        }else{
            this.gender = 2 ;
        }
    }

    public String getHouseNumber() {
        return houseNumber.replace("'" , "");
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


    public String getStreetNumber() {
        return streetNumber.replace("'" , "");

    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode.replace("'" , "");
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setGender(int gender) {
        this.gender = gender ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.replace("'" , "");

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.replace("'" , "");

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city.replace("'" , "");
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country.replace("'" , "");

    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber.replace("'" , "");
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                '}';
    }
}
