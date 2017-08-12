package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Event {
    int id;
    private String name;
    private String description;
    private ArrayList<Attendee> allAttendees;
    private static ArrayList<Event> allEvents = new ArrayList<Event>();
    private static int eventListSize;
    private int attendeeCount;

    //Constructor
    public Event(String name,String description){
        this.name = name;
        this.description = description;
        this.allAttendees = new ArrayList<Attendee>();
        eventListSize++;
        this.id = eventListSize;
        allEvents.add(this);
        this.attendeeCount = allAttendees.size();
    }

    public static Event createNewEvent(){
        Event newEvent = new Event("Test Event","Test Description");
        return newEvent;
    }

    public static ArrayList<Event> clearAllEvents(ArrayList<Event> allEvents){
        allEvents.clear();
        return allEvents;
    }

    public static Event findById(int id){
        for(Event event : allEvents){
            if(id == event.id){
                return event;
            }
        }
        return null;
    }

    public void updateEvent(String newName, String newDescription){
        this.name = newName;
        this.description = newDescription;
    }

    //Getters + Setters
    public int getId() {
        return id;
    }

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

    public ArrayList<Attendee> getAllAttendees() {
        return allAttendees;
    }

    public void setAllAttendees(Attendee addToEvent) {
        allAttendees.add(addToEvent);
    }

    public static ArrayList<Event> getAllEvents() {
        return allEvents;
    }

    public static void setAllEvents(ArrayList<Event> allEvents) {
        Event.allEvents = allEvents;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount() {
        this.attendeeCount++;
    }
}
