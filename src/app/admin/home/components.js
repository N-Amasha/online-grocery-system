"use client"
import { useEffect, useState, useRef } from "react";

// do NOT use react chart js ts bricked my project

// import build_notification_sentences from "./build_notification";

// import {
//   Chart as ChartJS,
//   CategoryScale,
//   LinearScale,
//   BarElement,
//   Tooltip,
//   Legend,
// } from "chart.js";
// import { Bar } from "react-chartjs-2";

// ChartJS.register(CategoryScale, LinearScale, BarElement, Tooltip, Legend);

export function NotificationSlide ({text}) {
    return (
        <div className="event-card" role="listitem">
            <h3>{text}</h3>
        </div>
        // <div className="event-p-3 text-warning-emphasis bg-warning-subtle border border-warning-subtle rounded-3" role="listitem">
        //     <h3>{text}</h3>
        // </div>
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
    }, [])

    return (
        notifications.map((message, index) => (
            <NotificationSlide key={index} text={message}/>
        ))
    )
}


export function PieChart({ title, raw_data }) {
  const chartRef = useRef(null);
  // const dataArray = raw_data

  useEffect(() => {
    const drawChart = () => {
      if (!window.google || !chartRef.current) return;

      // const data = window.google.visualization.arrayToDataTable(raw_data)

      const data = window.google.visualization.arrayToDataTable(raw_data);

      const options = {
        // title: "My Daily Activities",
        title: title,
        // pieHole: 0.4,   // remove this line for a normal pie chart
        // is3D: true,   // uncomment for 3D pie
        width: "100%",
        height: 400,
        backgroundColor: "transparent",

        // Title text color
        titleTextStyle: {
            color: "#d9d9d9",
            fontSize: 18,
        },

        // Legend text color
        legend: {
            textStyle: {
            color: "#d9d9d9",
            },
        },

        // Tooltip text (optional)
        tooltip: {
            textStyle: {
            color: "#0d0d0d",
            },
        }
      };

      const chart = new window.google.visualization.PieChart(chartRef.current);
      chart.draw(data, options);
    };

    const loadGoogleCharts = () => {
      return new Promise((resolve) => {
        if (window.google && window.google.charts) {
          resolve();
          return;
        }

        const existingScript = document.getElementById("google-charts-loader");
        if (existingScript) {
          existingScript.addEventListener("load", resolve, { once: true });
          return;
        }

        const script = document.createElement("script");
        script.id = "google-charts-loader";
        script.src = "https://www.gstatic.com/charts/loader.js";
        script.async = true;
        script.onload = resolve;
        document.body.appendChild(script);
      });
    };

    loadGoogleCharts().then(() => {
      window.google.charts.load("current", { packages: ["corechart"] });
      window.google.charts.setOnLoadCallback(drawChart);
    });

    const handleResize = () => {
      if (window.google && chartRef.current) {
        drawChart();
      }
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, [title, raw_data]);

  return <div ref={chartRef} style={{ width: "100%", height: "100%" }} />;
}

// Plugin for threshold line
// const thresholdLinePlugin = {
//   id: "thresholdLine",
//   afterDraw: (chart, args, options) => {
//     const {
//       ctx,
//       chartArea: { left, right },
//       scales: { y },
//     } = chart;

//     if (!y) return;

//     const yPos = y.getPixelForValue(options.value);

//     ctx.save();
//     ctx.beginPath();
//     ctx.setLineDash([8, 6]); // dashed line
//     ctx.strokeStyle = options.color || "rgba(0,0,255,0.7)";
//     ctx.lineWidth = options.width || 2;

//     ctx.moveTo(left, yPos);
//     ctx.lineTo(right, yPos);
//     ctx.stroke();
//     ctx.restore();
//   },
// };

// ChartJS.register(thresholdLinePlugin);

// export function CustomBarChart() {
//   const data = {
//     labels: ["One", "Two", "Three", "Four", "Five", "Six", "Seven"],
//     datasets: [
//       {
//         label: "Dataset 1",
//         data: [null, 90, 60, null, 65, null, 80],
//         backgroundColor: "rgba(54, 162, 235, 0.7)",
//         barThickness: 40,
//       },
//       {
//         label: "Dataset 2",
//         data: [null, null, 50, null, 60, null, 80],
//         backgroundColor: "rgba(153, 102, 255, 0.7)",
//         barThickness: 40,
//       },
//     ],
//   };

//   const options = {
//     responsive: true,
//     maintainAspectRatio: false,
//     plugins: {
//       legend: {
//         display: false,
//       },
//       thresholdLine: {
//         value: 40, // threshold value
//         color: "blue",
//         width: 2,
//       },
//     },
//     scales: {
//       x: {
//         stacked: false,
//         grid: {
//           display: false,
//         },
//       },
//       y: {
//         beginAtZero: true,
//         max: 100,
//         grid: {
//           drawBorder: false,
//         },
//       },
//     },
//   };

//   return (
//     <Bar data={data} options={options} />
//   );
// }

