package dao;

import models.Attendee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oAttendeDaoTest {
    private Sql2oAttendeDao attendeeDao;
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        attendeeDao = new Sql2oAttendeDao(sql2o);
        conn = sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void Attendee_instantiatesCorrectly() {
        Attendee newAttendee = newAttendee();
        assertTrue(newAttendee instanceof Attendee);
    }

    @Test
    public void getNameFromNewAttendee_Trevor() throws Exception {
        Attendee newAttendee = newAttendee();
        assertEquals("Trevor",newAttendee.getName());
    }

    @Test
    public void findAttendeeById() throws Exception {
        Attendee newAttendee = newAttendee();
        attendeeDao.addAttendee(newAttendee);
        int thisId = newAttendee.getId();
        Attendee foundAttendee = attendeeDao.findAttendeeById(thisId);
        assertEquals(thisId, foundAttendee.getId());
    }

    @Test
    public void returnListOfAllAttendees() throws Exception {
        Attendee newAttendee = newAttendee();
        Attendee newAttendee2 = new Attendee("Stuart","Ambrose", "none", 34, 1);
        attendeeDao.addAttendee(newAttendee);
        attendeeDao.addAttendee(newAttendee2);
        assertEquals(2, attendeeDao.getAllAttendees().size());
    }

    @Test
    public void getAllAttendeesByTour_returnsAllAttendeesForTour() throws Exception {
        Attendee newAttendee = newAttendee();
        Attendee newAttendee2 = new Attendee("Stuart","Ambrose", "none", 34, 1);
        Attendee newAttendee3 = new Attendee("Sally","Gill Nursery", "none", 59, 2);
        attendeeDao.addAttendee(newAttendee);
        attendeeDao.addAttendee(newAttendee2);
        attendeeDao.addAttendee(newAttendee3);
        List tourCamps = attendeeDao.getAllAttendeesByEvent(1);
        assertEquals(2, tourCamps.size());
    }

    @Test
    public void updateASingleAttendee() throws Exception {
        Attendee newAttendee = newAttendee();
        String oldName = newAttendee.getName();
        attendeeDao.addAttendee(newAttendee);
        Attendee foundAttendee = attendeeDao.findAttendeeById(newAttendee.getId());
        attendeeDao.updateAttendee("Stuart","Ambrose", "none", 34, 1, 1);
        Attendee updatedAttendee = attendeeDao.findAttendeeById(foundAttendee.getId());
        assertNotEquals(oldName, updatedAttendee.getName());
    }

    @Test
    public void deleteById() throws Exception {
        Attendee newAttendee = newAttendee();
        attendeeDao.addAttendee(newAttendee);
        int idToDelete = newAttendee.getId();
        attendeeDao.deleteAttendeeById(idToDelete);
        assertEquals(0, attendeeDao.getAllAttendees().size());
    }

    //helper
    public Attendee newAttendee() {
        return new Attendee("Trevor", "WEI", "trevor.a.gill@gmail.com", 30, 1);
    }
}