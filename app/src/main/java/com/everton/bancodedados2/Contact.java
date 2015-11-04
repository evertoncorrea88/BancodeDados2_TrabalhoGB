package com.everton.bancodedados2;

/**
 * Created by Everton on 11/2/15.
 */
public class Contact {
    private long id;
    private String name;
    private int number;
    private String email;

    public Contact() {

    }

    public Contact(String name, int number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
