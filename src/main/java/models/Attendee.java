package models;

public class Attendee {
    String name;
    String company;
    String email;
    int age;
    Event event;

    public Attendee(String name, String company, String email, int age, Event event){
        this.name = name;
        this.company = company;
        this.email = email;
        this.age = age;
        this.event = event;
        Event.getAllAttendees().add(this);
    }




}
