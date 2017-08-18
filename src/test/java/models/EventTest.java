//package models;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static models.Event.createNewEvent;
//import static models.Event.getAllEvents;
//import static org.junit.Assert.*;
//
//public class EventTest {
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Event.clearAllEvents(Event.getAllEvents());
//    }
//
//    @Test
//    public void Event_InstantiateNewEventObject_true() throws Exception {
//        Event newEvent = createNewEvent();
//        assertTrue(newEvent instanceof Event);
//    }
//
//    @Test
//    public void Event_GetEventName_TestEvent() throws Exception {
//        Event newEvent = createNewEvent();
//        assertEquals("Test Event", newEvent.getName());
//    }
//
//    @Test
//    public void Event_EnsureAllEventsArrayHoldsAllEvents_2() throws Exception {
//        Event newEvent = createNewEvent();
////        ArrayList<Attendee> allAttendees = new ArrayList<Attendee>();
//        Event newEvent2 = new Event("Test Event 2","Test Description2");
//        assertEquals(2, Event.getAllEvents().size());
//    }
//
//    @Test
//    public void Event_RetrieveSpecificEventById_() throws Exception {
//        Event newEvent = createNewEvent();
////        ArrayList<Attendee> allAttendees = new ArrayList<Attendee>();
////        ArrayList<Attendee> allAttendees2 = new ArrayList<Attendee>();
//        Event newEvent2 = new Event("Test Event 2","Test Description2");
//        Event newEvent3 = new Event("Test Event 3","Test Description3");
//        assertEquals(newEvent2, Event.findById(2));
//    }
//
//    @Test
//    public void Event_UpdateEventDetails_NotATestEvent() throws Exception {
//        Event newEvent = createNewEvent();
//        newEvent.updateEvent("Not A Test Event", "Are we really talking about tests? TESTS?" );
//        assertEquals("Not A Test Event", newEvent.getName());
//    }
//
//    @Test
//    public void Event_UpdateEventAttendanceCount_2() throws Exception {
//        Event newEvent = createNewEvent();
//        Attendee newAttendee = new Attendee("Test","trevor","t", 30, newEvent);
//        Attendee newAttendee2 = new Attendee("Test","trevor","t", 33, newEvent);
//        int attendeeCount = newEvent.getAttendeeCount();
//        assertEquals(2, attendeeCount);
//    }
//
//}