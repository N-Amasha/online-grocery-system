"use client";

import { useState } from "react";
import { Notifications, PieChart, CustomBarChart } from "./components.js";
import * as tabItems from "./home_tabItems.jsx";
import ThresholdBarChart from "./components/ThresholdBarChart.js";


export default function MainBox() {
  const [activeTab, setActiveTab] = useState("notification");

    const renderContent = () => {
    switch (activeTab) {
      case "notification":
        return (
          <section className="events-area" aria-labelledby="events-title">
            <h1 id="events-title">EVENTS</h1>
            <Notifications />
          </section>
        );

      case "overview":
      //   const data_customer = [
      //     ["Status", "Amount"], 
      //     ["packaging", 10],
      //     ["in-transit", 10], 
      //     ["received", 20],
      //     ["cancelled", 5]
      // ];

      // const data_product = [
      //     ["Status", "Amount"], 
      //     ["packaging", 2],
      //     ["in-transit", 5], 
      //     ["received", 3],
      //     ["cancelled", 0]
      // ];
        return (
          <tabItems.OverviewTab/>
          // <section className="overview-area">
          //   <div className="overview-box"><PieChart title={"Customer Orders"} raw_data={data_customer}/></div>
          //   <div className="overview-box"><PieChart title={"Product Orders"} raw_data={data_product}/></div>
          // </section>
        );

      case "stock":
        const data = [
                      { id: "id001", raw: 10, withOrders: 15 },
                      { id: "id002", raw: 20, withOrders: 18 },
                      { id: "id003", raw: 30, withOrders: 35 },
                    ];
        return (
          <section className="events-area">
            <h1>Products Stock</h1>
                <ThresholdBarChart
                  data={data}
                  threshold={16}
                  xAxisLabel="Products"
                  yAxisLabel="Count"
                />
          </section>
        );

      default:
        return null;
    }
  };

  return (
    <main className="main-pane">
      <nav className="horizontal-nav" aria-label="Horizontal navigation">
        <button
          className={`pill ${
            activeTab === "notification" ? "is-selected" : ""
          }`}
          type="button"
          aria-current={activeTab === "notification" ? "page" : undefined}
          onClick={() => setActiveTab("notification")}
        >
          Notification
        </button>

        <button
          className={`pill ${
            activeTab === "overview" ? "is-selected" : ""
          }`}
          type="button"
          aria-current={activeTab === "overview" ? "page" : undefined}
          onClick={() => setActiveTab("overview")}
        >
          Overview
        </button>

        <button
          className={`pill ${
            activeTab === "stock" ? "is-selected" : ""
          }`}
          type="button"
          aria-current={activeTab === "stock" ? "page" : undefined}
          onClick={() => setActiveTab("stock")}
        >
          Stock
        </button>

      </nav>

      {/* Conditional Rendering */}
      {renderContent()}
    </main>
  );
}