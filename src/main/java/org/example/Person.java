package org.example;

// Abstract class representing a person
public abstract class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    // Abstract method to get the type of person (Guest or Anyone)
    public abstract String getPersonType();
}
