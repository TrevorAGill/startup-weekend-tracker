package dao;

import models.Event;

import java.util.List;

public interface EventDao {

    void addEvent (Event event);

    List<Event> getAllEvents();

    Event findEventById(int id);

    void updateEventById(String name, String description, int attendeeCount, int id);

    void deleteEventById(int id);

    void incrementAttendeeCount(int id, int attendeeCount);
}
