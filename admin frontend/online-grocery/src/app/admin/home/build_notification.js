function build_notification_sentences(raw_notification_response) {
    var item = "default item"
    var time = 0
    let clean_notifications = []

    raw_notification_response["notifications"].forEach((raw_notification) => {
        switch (raw_notification["type"]) {
            case "run-out":
                item = raw_notification["item"]
                clean_notifications.push([`${item} stock is running out`, "22:00"])
                break;
            case "expiration":
                item = raw_notification["item"]
                time = raw_notification["time"]
                clean_notifications.push([`${item} stock will expire within ${time} days`, "22:00"])
                break;
            default:
                console.error("Invalid type/ not accounted for")
        }
    });

    return clean_notifications
}

// if (require.main === module) {
//     const test_dict = {"notifications": [
//         {"type": "run-out", "item": "Tomato"},
//         {"type": "expiration", "item": "Celary", "time": 14}
//     ]}

//     const built_notifications = build_notification_sentences(test_dict)

//     console.log(built_notifications)

//     built_notifications.forEach((sentence) => {
//         console.log(sentence)
//     })
// }
