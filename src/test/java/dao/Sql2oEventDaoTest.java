package dao;

import models.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEventDaoTest {

    private Sql2oEventDao eventDao;
    private Connection conn; //must be sql2o class conn
    
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        eventDao = new Sql2oEventDao(sql2o);

        //keep connection open through entire test so it does not get erased.
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void Event_instantiatesCorrectly() {
        Event newEvent = newEvent();
        assertTrue(newEvent instanceof Event);
    }

    @Test
    public void getNameFromNewEvent_PCT () throws Exception {
        Event newEvent = newEvent();
        assertEquals("Utilizing DAO",newEvent.getName());
    }

//    @Test
//    public void getNameFromNewEvent_UtilizingDAO () throws Exception {
//        Event newEvent = newEvent();
//        assertEquals(3,newEvent.getDifficulty());
//    }
//
//    @Test
//    public void getRatingFromNewEvent_PCT () throws Exception {
//        Event newEvent = newEvent();
//        assertEquals(5,newEvent.getRating());
//    }
//
//    @Test
//    public void getId_and_addEventingEventSetsId () throws Exception {
//        Event newEvent = newEvent();
//        int localEventId = newEvent.getId();
//        EventDao.addEvent(newEvent);
//        assertNotEquals(localEventId,newEvent.getId());
//    }
//
//    @Test
//    public void findEventById() throws Exception {
//        Event newEvent = newEvent();
//        EventDao.addEvent(newEvent);
//        int thisId = newEvent.getId();
//        Event foundEvent = EventDao.findEventById(thisId);
//        assertEquals(thisId, foundEvent.getId());
//    }
//
//    @Test
//    public void returnListOfAllEvents() throws Exception {
//        Event newEvent = newEvent();
//        Event newEvent2 = new Event("Lewis and Clarke", 4, 3);
//        EventDao.addEvent(newEvent);
//        EventDao.addEvent(newEvent2);
//        assertEquals(2, EventDao.getAllEvents().size());
//    }
//
//    @Test
//    public void updateASingleEvent() throws Exception {
//        Event newEvent = newEvent();
//        String oldName = newEvent.getName();
//        EventDao.addEvent(newEvent);
//        Event foundEvent = EventDao.findEventById(newEvent.getId());
//        EventDao.update("Test",4,4, foundEvent.getId());
//        Event updatedEvent = EventDao.findEventById(foundEvent.getId());
//        assertNotEquals(oldName, updatedEvent.getName());
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//        Event newEvent = newEvent();
//        EventDao.addEvent(newEvent);
//        int idToDelete = newEvent.getId();
//        EventDao.deleteById(idToDelete);
//        assertEquals(0, EventDao.getAllEvents().size());
//    }


    //helper
    public Event newEvent() {
        return new Event("Utilizing DAO", "How to use DAO");
    }

}