package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private String name;
    private String description;
    private int attendeeCount;

    //Constructor
    public Event(String name,String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (attendeeCount != event.attendeeCount) return false;
        if (!name.equals(event.name)) return false;
        return description != null ? description.equals(event.description) : event.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + attendeeCount;
        return result;
    }

    //Getters + Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount() {
        this.attendeeCount++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
