package com.WD38.GroceryOnline;

import java.util.ArrayList;
import java.util.HashMap;


public class NotificationBuilder {
    private final ArrayList<HashMap<String, Object>> notificationList;
    private final HashMap<String, ArrayList<HashMap<String, Object>>> notificationsOut;

    public NotificationBuilder() {
        this.notificationList = new ArrayList<>();
        this.notificationsOut = new HashMap<>();
    }
    

    public void addCriticallyLowNotification(String item, int quantity){
        HashMap<String, Object> OutNottification = new HashMap<>();
        OutNottification.put("type", "critically-low");
        OutNottification.put("item", item);
        OutNottification.put("quantity", quantity);
        this.notificationList.add(OutNottification);
    }
    
    
    public void addExpirationNotification(String item, long timeTillExpiry){
        HashMap<String, Object> OutNottification = new HashMap<>();
        OutNottification.put("type", "expiration");
        OutNottification.put("item", item);
        OutNottification.put("time", timeTillExpiry);
        this.notificationList.add(OutNottification);
    }


    public void clearNotificationList() {
        this.notificationList.clear();
    }

    public HashMap<String, ArrayList<HashMap<String, Object>>> getNotificationsMap() {
        notificationsOut.put("notifications", notificationList);
        return notificationsOut;
    }

    // public static void main(String[] args) {
    //     NotificationBuilder myNotificationBuilder = new NotificationBuilder();
    //     myNotificationBuilder.addRunOutNotification("Tomato");
    //     myNotificationBuilder.addExpirationNotification("Celary", 14);
    //     System.out.println(myNotificationBuilder.getNotificationsMap());
    // }

}
