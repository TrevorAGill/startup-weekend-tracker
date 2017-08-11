package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.Event.createNewEvent;
import static org.junit.Assert.*;

public class EventTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Event_InstantiateNewEventObject_true() throws Exception {
        Event newEvent = createNewEvent();
        assertTrue(newEvent instanceof Event);
    }
}