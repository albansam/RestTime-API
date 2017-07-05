/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author asamier
 */
@Path("TimeAPI")
public class WebServiceCurrentTime {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebServiceCurrentTime
     */
    public WebServiceCurrentTime() {
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
     * PUT method for updating or creating an instance of WebServiceCurrentTime
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
