package dao;

import models.Attendee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAttendeDao implements AttendeeDao{

    private final Sql2o sql2o;

    public Sql2oAttendeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addAttendee(Attendee attendee) {
        String sql = "INSERT INTO attendees (name, company, email, age, eventId) VALUES (:name, :company, :email, :age, :eventId)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", attendee.getName())
                    .addParameter("company", attendee.getCompany())
                    .addParameter("email", attendee.getEmail())
                    .addParameter("age", attendee.getAge())
                    .addParameter("eventId", attendee.getEventId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("COMPANY", "company")
                    .addColumnMapping("EMAIL", "email")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("EVENTID", "eventId")
                    .executeUpdate()
                    .getKey();
            attendee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Attendee> getAllAttendees() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM attendees")
                    .executeAndFetch(Attendee.class);
        }
    }

    @Override
    public List<Attendee> getAllAttendeesByEvent(int eventId) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM attendees WHERE eventId = :eventId")
                    .addParameter("eventId", eventId)
                    .executeAndFetch(Attendee.class);
        }

    }

    @Override
    public Attendee findAttendeeById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM attendees WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Attendee.class);
        }
    }

    @Override
    public void updateAttendee(String name, String company, String email, int age, int eventId, int id) {
        try (Connection con = sql2o.open()) {
            con.createQuery("UPDATE attendees SET (name, company, email, age, eventId) = (:name, :company, :email, :age, :eventId) WHERE id = :id")
                    .addParameter("name", name)
                    .addParameter("company", company)
                    .addParameter("email", email)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .addParameter("eventId", eventId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void deleteAttendeeById(int id) {
        try (Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM attendees WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
