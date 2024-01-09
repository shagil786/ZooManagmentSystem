package org.example;
import java.lang.*;

// Concrete class representing a guest
public class Guest extends Person{
    public Guest(int age) {
        super(age);
    }
    @Override
    public String getPersonType() {
        return "Guest";
    }
}
