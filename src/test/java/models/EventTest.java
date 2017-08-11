package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.Event.createNewEvent;
import static models.Event.getAllEvents;
import static org.junit.Assert.*;

public class EventTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Event.clearAllEvents(Event.getAllEvents());
    }

    @Test
    public void Event_InstantiateNewEventObject_true() throws Exception {
        Event newEvent = createNewEvent();
        assertTrue(newEvent instanceof Event);
    }

    @Test
    public void Event_GetEventName_TestEvent() throws Exception {
        Event newEvent = createNewEvent();
        assertEquals("Test Event", newEvent.getName());
    }

    @Test
    public void Event_EnsureAllEventsArrayHoldsAllEvents_2() throws Exception {
        Event newEvent = createNewEvent();
        Event newEvent2 = new Event("Test Event 2","Test Description2");
        assertEquals(2, Event.getAllEvents().size());
    }

    @Test
    public void Event_RetrieveSpecificEventById_() throws Exception {
        Event newEvent = createNewEvent();
        Event newEvent2 = new Event("Test Event 2","Test Description2");
        Event newEvent3 = new Event("Test Event 3","Test Description3");
        assertEquals(newEvent2, Event.findEventById(2));
    }
}