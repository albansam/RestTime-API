/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pylguern
 */
public class Event {
    public String eventName;
    public Date eventDate;
    public static List<Event> eventList = new ArrayList<Event>();
    
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
    
    public void storeToFile(){
        eventList.add(this);
    }
    
    public int countEvents(){
        return eventList.size();
    }
    
    public List<Event> getListEvent(){
        return eventList;
    }
}
