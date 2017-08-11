package models;

public class Attendee {
    private String name;
    private String company;
    private String email;
    private int age;



    private int id;
    private static int attendeeSize;
    Event event;

    public Attendee(String name, String company, String email, int age, Event event){
        this.setName(name);
        this.setCompany(company);
        this.setEmail(email);
        this.setAge(age);
        attendeeSize++;
        this.id = attendeeSize;
        this.event = event;
        event.setAllAttendees(this);
    }


    //Getters + Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
