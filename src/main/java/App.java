import models.Attendee;
import models.Event;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //go to homepage--done
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Event> items= Event.getAllEvents();
            model.put("items", items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //go to event listing--done
        get("/events", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Event> events = Event.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "event-home.hbs");
        }, new HandlebarsTemplateEngine());

        //show new event form--done
        get("/event/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new event to event listing page--done
        post("/event/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("eventName");
            String description = req.queryParams("eventDescription");
            Event newEvent = new Event(name, description);
            int id = newEvent.getId();
            model.put("id",id);
            model.put("newEvent",newEvent);
            res.redirect("/events");
            return null;
        });

        //show new attendee form--done
        get("/attendee/new", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new attendee to event attendee list
        post("/attendee/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("attendeeName");
            String company = req.queryParams("attendeeCompany");
            String email = req.queryParams("attendeeEmail");
            int age = Integer.parseInt(req.queryParams("attendeeAge"));
            Attendee newAttendee = new Attendee(name, company, email, age);
            model.put("newAttendee",newAttendee);
            res.redirect("/events");
            return null;
        });

        //load event detail page with all attendees
        get("/event/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            Event foundEvent = Event.findById(Integer.parseInt(req.params("id")));
            ArrayList<Attendee> attendees = foundEvent.getAllAttendees();
            model.put("foundEvent", foundEvent);
            model.put("attendees", attendees);

            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());




    }
}
