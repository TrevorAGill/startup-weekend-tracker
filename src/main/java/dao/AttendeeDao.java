package dao;

import models.Attendee;

import java.util.List;

public interface AttendeeDao {

    //create
    void add (Attendee attendee);

    //read
    List<Attendee> getAll();

    List<Attendee> getAllByEvent(int eventId);

    //find by id
    Attendee findById(int id);

    //update
    void update();

    //delete
    void deleteById(int id);
}
