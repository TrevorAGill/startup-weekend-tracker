package models;

public class Attendee {
    private String name;
    private String company;
    private String email;
    private int age;
    private int id;
    private int eventId;

    public Attendee(String name, String company, String email, int age, int eventId){
        this.name = name;
        this.company = company;
        this.email = email;
        this.age = age;
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        if (age != attendee.age) return false;
        if (id != attendee.id) return false;
        if (eventId != attendee.eventId) return false;
        if (!name.equals(attendee.name)) return false;
        if (company != null ? !company.equals(attendee.company) : attendee.company != null) return false;
        return email != null ? email.equals(attendee.email) : attendee.email == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + id;
        result = 31 * result + eventId;
        return result;
    }

    //Getters + Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
