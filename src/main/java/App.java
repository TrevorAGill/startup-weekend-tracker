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

        //go to homepage
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Event> items= Event.getAllEvents();
            model.put("items", items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //go to event listing
        get("/events", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Event> events = Event.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "event-home.hbs");
        }, new HandlebarsTemplateEngine());

        //show new event form
        get("/event/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "event-form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new event to event listing page
        post("/event/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("eventName");
            String description = req.queryParams("eventDescription");
            Event newEvent = new Event(name, description);
            model.put("newEvent",newEvent);
            res.redirect("/events");
            return null;
        });




    }
}
