package dao;
import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEventDao implements EventDao {

    private final Sql2o sql2o;

    public Sql2oEventDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addEvent(Event event) {
        String sql = "INSERT INTO events (name, description) VALUES (:name, :description)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", event.getName())
                    .addParameter("description", event.getDescription())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("DESCRIPTION", "description")
                    .executeUpdate()
                    .getKey();
            event.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM events")
                    .executeAndFetch(Event.class);
        }
    }

    @Override
    public Event findEventById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM events WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Event.class);
        }
    }

    @Override
    public void updateEventById(String name, String description, int attendeeCount, int id) {
        try (Connection con = sql2o.open()) {
            con.createQuery("UPDATE events SET (name, description, attendeeCount) = (:name, :description, :attendeeCount) WHERE id = :id")
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .addParameter("attendeeCount", attendeeCount)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteEventById(int id){
        try (Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM events WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
