package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static models.Event.createNewEvent;
import static org.junit.Assert.*;

public class AttendeeTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Event.clearAllEvents(Event.getAllEvents());
        Event.clearAllAttendees(Event.getAllAttendees());
    }

    @Test
    public void Attendee_InstantiateAttendee_true() throws Exception {
        Event newEvent = createNewEvent();
        Attendee Trevor = new Attendee("Trevor", "WEI", "trevor.a.gill@gmail.com", 30,newEvent);
        assertTrue(Trevor instanceof Attendee);
    }

    @Test
    public void Attendee_AddAttendeeToAnEvent_1() throws Exception {
        Event newEvent = createNewEvent();
        Event newEvent2 = new Event("Test Event 2","Test Description2");
        Attendee Trevor = new Attendee("Trevor", "WEI", "trevor.a.gill@gmail.com", 30,newEvent2);
        assertEquals(1, newEvent2.getAllAttendees().size());
    }

}