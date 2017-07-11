/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pylguern
 */
public class Event {
    public String eventName;
    public Date eventDate;
    
    public Event(String eName, String eDate){
        this.eventName = eName;
        this.eventDate = null;
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
            this.eventDate = dateFormat.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }    
        
    }
}
