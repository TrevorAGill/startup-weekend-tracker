import dao.Sql2oAttendeDao;
import dao.Sql2oEventDao;
import models.Attendee;
import models.Event;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/events2.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEventDao eventDao = new Sql2oEventDao(sql2o);
        Sql2oAttendeDao attendeeDao = new Sql2oAttendeDao(sql2o);

        //go to homepage--refactored
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//            ArrayList<Event> items= Event.getAllEvents();
//            model.put("items", items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //go to event listing--refactored
        get("/events", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Event> allEvents = eventDao.getAllEvents();
            model.put("events", allEvents);
            return new ModelAndView(model, "event-home.hbs");
        }, new HandlebarsTemplateEngine());

        //show new event form--refactored
        get("/event/new", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new event to event listing page--refactored
        post("/event/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("eventName");
            String description = req.queryParams("eventDescription");
//            ArrayList<Attendee> attendees = new ArrayList<Attendee>();
            Event newEvent = new Event(name, description);
            eventDao.addEvent(newEvent);
//            int id = newEvent.getId();
//            model.put("id",id);
//            model.put("newEvent",newEvent);
            res.redirect("/events");
            return null;
        });

        //go to form to edit existing event--refactored
        get("/event/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Event foundEvent = eventDao.findEventById(Integer.parseInt(req.params("id")));
            model.put("editEvent", foundEvent);
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post edited event--refactored
        post("/event/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("eventName");
            String newDescription = req.queryParams("eventDescription");
            int eventId = Integer.parseInt(req.params("id"));
            eventDao.updateEventById(newName, newDescription,eventId);
            res.redirect("/event/" + eventId);
            return null;
        });

        //show new attendee form--refactored
        get("/event/:id/attendee/new", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int eventId = Integer.parseInt(req.params("id"));
            model.put("id", eventId);
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new attendee to event attendee list--
        post("/event/:id/attendee/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("attendeeName");
            String company = req.queryParams("attendeeCompany");
            String email = req.queryParams("attendeeEmail");
            int age = Integer.parseInt(req.queryParams("attendeeAge"));
            int eventId = Integer.parseInt(req.params("id"));
            model.put("id",eventId);
            Attendee newAttendee = new Attendee(name, company, email, age, eventId);
            attendeeDao.addAttendee(newAttendee);
            model.put("newAttendee", newAttendee);
            res.redirect("/event/" + eventId);
            return null;
        });

        //load event detail page with all attendees--refactored
        get("/event/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int eventId = Integer.parseInt(req.params("id"));
            Event foundEvent = eventDao.findEventById(Integer.parseInt(req.params("id")));
            List<Attendee> attendees = attendeeDao.getAllAttendeesByEvent(eventId);
            model.put("foundEvent", foundEvent);
            model.put("attendees", attendees);
            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
