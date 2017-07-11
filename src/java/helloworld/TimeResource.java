/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import helloworld.Event;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author pylguern
 */
@Path("Time")
public class TimeResource {

    @Context
    private UriInfo context;       

    /**
     * Creates a new instance of TimeResource
     */
    public TimeResource() {
    }
    
    /**
     * Returns the current server time
     * @return an instance of java.lang.String
     */
    @GET
    @Path("CurrentTime")
    @Produces("application/json")
    public String getCurrentTime() {
        String day = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        String hour = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        JsonArray jsonReturn = Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
            .add("day", day)
            .add("hour", hour))
            .build();
        return jsonReturn.toString();
    }
    
    /**
     * Retrieves representation of an instance of webService.WebServiceCurrentTime
     * @return an instance of java.lang.String
     */
    @GET
    @Path("TimeUntil/{until}")
    @Produces("application/json")
    public String getTimeUntil(@PathParam("until") String until) {
        
        // Custom date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String currentDateString = dateFormat.format(Calendar.getInstance().getTime());
        String untilDateString = until;

        Date currentDate = null;
        Date untilDate = null;
        
        try {
            currentDate = dateFormat.parse(currentDateString);
            untilDate = dateFormat.parse(untilDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }    

        // Get msec from each, and subtract.
        long remainingDiff = untilDate.getTime() - currentDate.getTime();
        
        long millisInSecond = 1000;
        long millisInMinute = millisInSecond * 60;
        long millisInHour = millisInMinute * 60;
        long millisInDay = millisInHour * 24;
        long millisInMonth = millisInDay * 30;
        long millisInYear = millisInMonth * 365;
        
        long untilYears = (long) remainingDiff / millisInYear;
        remainingDiff = remainingDiff - (untilYears * millisInYear);
        
        long untilMonths = (long) remainingDiff / millisInMonth;
        remainingDiff = remainingDiff - (untilMonths * millisInMonth);
        
        long untilDays = (long) remainingDiff / millisInDay;
        remainingDiff = remainingDiff - (untilDays * millisInDay);
        
        long untilHours = (long) remainingDiff / millisInHour;
        remainingDiff = remainingDiff - (untilHours * millisInHour);
        
        long untilMinutes = (long) remainingDiff / millisInMinute;
        remainingDiff = remainingDiff - (untilMinutes * millisInMinute);
        
        long untilSeconds = (long) remainingDiff / millisInSecond;
        remainingDiff = remainingDiff - (untilSeconds * millisInSecond);

        JsonArray jsonReturn = Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
            .add("years", untilYears)
            .add("months", untilMonths)
            .add("days", untilDays)
            .add("hours", untilHours)
            .add("minutes", untilMinutes)
            .add("seconds", untilSeconds))                
            .build();
        return jsonReturn.toString();
    }
    
    /**
     * Add an event
     * @param eventName Event name
     * @param eventDate Event date
     * @return Agreement string
     */
    @GET
    @Path("AddEvent/{eName}/{eDate}")
    @Produces("text/plain")
    public String addEvent(@PathParam("eName") String eventName, @PathParam("eDate") String eventDate){
        Event e = new Event(eventName, eventDate);
        e.storeToFile();
        return "Event created ! " + e.countEvents() + " events stored.";
    }
    
    /**
     * Returns the current server time
     * @return an instance of java.lang.String
     */
    @GET
    @Path("ListEvents")
    @Produces("application/json")
    public String listEvent() {       
        Event mockEvent = new Event("","");
        List<Event> listE = mockEvent.getListEvent();
        
        String returnString = "{\n\t\"eventList\": [";
        
        for (Event e : listE) {
            returnString = returnString + "\n\t\t{\"name\": \"" + e.eventName + "\", \"date\": \"" + e.eventDate.toString() + "\"},";
        }
        returnString = returnString.substring(0, returnString.length() - 1);
        
        returnString = returnString + "\n]\n}";        
            
        return returnString;
    }

    /**
     * Retrieves representation of an instance of helloworld.TimeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TimeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
