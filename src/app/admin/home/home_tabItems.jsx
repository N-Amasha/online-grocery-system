"use client"
import { useEffect, useState} from "react";
import { PieChart } from "./components.js";


export function OverviewTab(){
    let default_data = [
        ["Status", "Amount"], 
        ["packaging", 1],
        ["in-transit", 1], 
        ["received", 1],
        ["cancelled", 1]
    ] 
    // const [count, setCount] = useState(0)
    const [customer_overview, setCustomer_overview] = useState(default_data);
    const [products_overview, setProducts_overview] = useState(default_data);
    const [customerTotal, setCustomerTotal] = useState(4);
    const [productTotal, setProductTotal] = useState(4);

    useEffect(() => {
        const getTotal = (in_list) => {
            return in_list[1][1] + in_list[1][2] + in_list[1][3] + in_list[1][4]
        }

        const fetchOverview = async (endpoint) => {
            try{
                let response = await fetch("http://localhost:8080/admin/" + endpoint)
                let respose = await response.json()

                let out = [
                    ["Status", "Amount"], 
                    ["packaging", respose["packing"]],
                    ["in-transit", respose["inTransit"]], 
                    ["received", respose["received"]],
                    ["cancelled", respose["cancelled"]]
                ];

                if(endpoint === "customer_overview") {
                    setCustomer_overview(out)
                    setCustomerTotal(getTotal(out))
                    console.log("customer changed")
                }
                else if(endpoint === "product_overview"){
                    setProducts_overview(out)
                    setProductTotal(getTotal(out))
                    console.log("products changed")
                }
                else{

                }

                console.log("ran to completion")
                // setCount(count + 1)
                // console.log(count)
            }
            catch(error){
                console.log("exception occured")
                console.error(error)
            }
            finally {
                
            }

        }

        fetchOverview("customer_overview")
        fetchOverview("product_overview")

        // console.log(customer_overview)
        // console.log(products_overview)

    }, [])

    return (
        <section className="overview-area">
            <div className="overview-box">
                <PieChart title={"Customer Orders"} raw_data={customer_overview}/>
                <h3>Total customer orders: {customerTotal}</h3>
            </div>

            <div className="overview-box">
                <PieChart title={"Product Orders"} raw_data={products_overview}/>
                <h3>Total product orders: {productTotal}</h3>
            </div>
        </section>
    );
}
