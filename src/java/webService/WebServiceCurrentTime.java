/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author asamier
 */
@Path("CurrentTime")
public class WebServiceCurrentTime {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebServiceCurrentTime
     */
    public WebServiceCurrentTime() {
    }

    /**
     * Retrieves representation of an instance of webService.WebServiceCurrentTime
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        String jour = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        String heure = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
	Map<String, String> timeMap = new HashMap<String, String>();
	timeMap.put("jour",jour);
	timeMap.put("heure",heure);
	JSONObject jsonReturn = new JSONObject(timeMap);
        return jsonReturn.toString();
        //throw new UnsupportedOperationException();
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
