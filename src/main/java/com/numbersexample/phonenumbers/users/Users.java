package com.numbersexample.phonenumbers.users;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name="customer")
@Entity
@NoArgsConstructor
public class Users {
    private String name;
    private String phone;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Transient
    private String country;
    @Transient
    private String state;

    public Users(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
   }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}

