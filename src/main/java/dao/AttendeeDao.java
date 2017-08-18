package dao;

import models.Attendee;

import java.util.List;

public interface AttendeeDao {

    //create
    void addAttendee (Attendee attendee);

    //read
    List<Attendee> getAllAttendees();

    List<Attendee> getAllAttendeesByEvent(int eventId);

    //find by id
    Attendee findAttendeeById(int id);

    //update
    void updateAttendee(String name, String company, String email, int age, int eventId, int id);

    //delete
    void deleteAttendeeById(int id);
}
