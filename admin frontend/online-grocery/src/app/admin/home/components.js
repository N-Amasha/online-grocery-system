"use client"
import { useEffect, useState } from "react";
// import build_notification_sentences from "./build_notification";


export function NotificationSlide ({text}) {
    return (
        <div className="event-card" role="listitem">
            <h3>{text}</h3>
        </div>
    )
}

export function Notifications () {
    const [notifications, setNotifications] = useState([]);

    useEffect(() => {
        const fetchNotifications = async () => {
            try{
                let respose = await fetch("http://localhost:8080/admin/notifications")
                const notification_api_response = await respose.json()
                // let notification_text = build_notification_sentences(notification_api_response)

                let clean_notifications = []
                notification_api_response["notifications"].forEach((raw_notification) => {
                    let item, time, quantity = ""
                    switch (raw_notification["type"]) {
                        case "critically-low":
                            item = raw_notification["item"]
                            quantity = raw_notification["quantity"]
                            clean_notifications.push(`${item} stock critically low (quantity: ${quantity})`)
                            break;
                        case "expiration":
                            item = raw_notification["item"]
                            time = raw_notification["time"]
                            clean_notifications.push(`${item} stock will expire within ${time} days`)
                            break;
                        default:
                            console.error("Invalid type/ not accounted for")
                    }
                });                

                setNotifications(clean_notifications)
                // testting
                console.log("notifications updated")
            }
            catch (error) {
                console.log("exception occured")
                console.error(error)
            }
            finally {

            } 
        }
        
        fetchNotifications(); // initial call

        const interval = setInterval(() => {
            fetchNotifications();
        }, 10 * 1000);   // time

        return () => clearInterval(interval);
    })

    return (
        notifications.map((message, index) => (
            <NotificationSlide key={index} text={message}/>
        ))
    )
}
