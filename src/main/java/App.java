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
        String connectionString = "jdbc:h2:~/events3.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEventDao eventDao = new Sql2oEventDao(sql2o);
        Sql2oAttendeDao attendeeDao = new Sql2oAttendeDao(sql2o);

        //go to homepage
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //go to event listing
        get("/events", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Event> allEvents = eventDao.getAllEvents();
            model.put("events", allEvents);
            return new ModelAndView(model, "event-home.hbs");
        }, new HandlebarsTemplateEngine());

        //show new event form
        get("/event/new", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new event to event listing page
        post("/event/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("eventName");
            String description = req.queryParams("eventDescription");
            Event newEvent = new Event(name, description);
            eventDao.addEvent(newEvent);
            res.redirect("/events");
            return null;
        });

        //go to form to edit existing event
        get("/event/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Event foundEvent = eventDao.findEventById(Integer.parseInt(req.params("id")));
            model.put("editEvent", foundEvent);
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post edited event
        post("/event/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("eventName");
            String newDescription = req.queryParams("eventDescription");
            int eventId = Integer.parseInt(req.params("id"));
            Event event = eventDao.findEventById(eventId);
            int attendeeCount = event.getAttendeeCount();
            eventDao.updateEventById(newName, newDescription, attendeeCount, eventId);
            res.redirect("/event/" + eventId);
            return null;
        });

        //delete event
        get("/event/:id/delete", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int eventIdToDelete = Integer.parseInt(req.params("id"));
            eventDao.deleteEventById(eventIdToDelete);
            res.redirect("/events");
            return null;
        });

        //load event detail page with all attendees
        get("/event/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int eventId = Integer.parseInt(req.params("id"));
            Event foundEvent = eventDao.findEventById(Integer.parseInt(req.params("id")));
            List<Attendee> attendees = attendeeDao.getAllAttendeesByEvent(eventId);
            model.put("foundEvent", foundEvent);
            model.put("attendees", attendees);
            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //show new attendee form
        get("/event/:id/attendee/new", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int eventId = Integer.parseInt(req.params("id"));
            model.put("eventId", eventId);
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new attendee to event attendee list
        post("/event/:id/attendee/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("attendeeName");
            String company = req.queryParams("attendeeCompany");
            String email = req.queryParams("attendeeEmail");
            int age = Integer.parseInt(req.queryParams("attendeeAge"));
            int eventId = Integer.parseInt(req.params("id"));
            Attendee newAttendee = new Attendee(name, company, email, age, eventId);
            attendeeDao.addAttendee(newAttendee);
            model.put("newAttendee", newAttendee);
            //update event attendance count
            Event event = eventDao.findEventById(eventId);
            int attendeeCount = attendeeDao.getAllAttendeesByEvent(eventId).size();
            event.setAttendeeCount();
            eventDao.updateEventById(event.getName(),event.getDescription(),attendeeCount,eventId);
            model.put("id",eventId);
            res.redirect("/event/" + eventId);
            return null;
        });

        //load attendee edit form
        get("/event/:eventId/attendee/:id/edit", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int attendeeId = Integer.parseInt(req.params("id"));
            Attendee attendee = attendeeDao.findAttendeeById(attendeeId);
            int eventId = Integer.parseInt(req.params("eventId"));
            model.put("eventId",eventId);
            model.put("attendee",attendee);
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post edited attendee
        post("/event/:eventId/attendee/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String attendeeName = req.queryParams("attendeeName");
            String attendeeCompany = req.queryParams("attendeeCompany");
            String attendeeEmail = req.queryParams("attendeeEmail");
            int attendeeAge = Integer.parseInt(req.queryParams("attendeeAge"));
            int eventId = Integer.parseInt(req.params("eventId"));
            int id = Integer.parseInt(req.params("id"));
            Event event = eventDao.findEventById(eventId);
            int attendeeCount = event.getAttendeeCount();
            attendeeDao.updateAttendee(attendeeName,attendeeCompany,attendeeEmail,attendeeAge,eventId,id);
            res.redirect("/event/" + eventId);
            return null;
        });

        //delete attendee
        get("/event/:foundEvent.id/attendee/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int eventId = Integer.parseInt(req.params("foundEvent.id"));
            Event event = eventDao.findEventById(eventId);
            int attendeeToDelete = Integer.parseInt(req.params("id"));
            attendeeDao.deleteAttendeeById(attendeeToDelete);
            //update attendee count
            int attendeeCount = attendeeDao.getAllAttendeesByEvent(eventId).size();
            event.setAttendeeCount();
            eventDao.updateEventById(event.getName(),event.getDescription(),attendeeCount,eventId);
            res.redirect("/event/" + eventId);
            return null;
        });
    }
}
