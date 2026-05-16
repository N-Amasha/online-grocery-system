"use client";
import { useEffect, useState } from "react";

const API_LINK = "http://localhost:8080/admin/products/quantities/pure"
const BAR_1_COLOR = "#3AE024"
const REFERENCE_LINE_COLOR = "#D9D9D9"

import {
  ResponsiveContainer,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ReferenceLine,
  Label,
} from "recharts";

/**
 * Props
 * -----
 * data: [
 *   {
 *     id: "id001",
 *     raw: 10,
 *     withOrders: 15
 *   }
 * ]
 *
 * threshold: number
 * xAxisLabel?: string
 * yAxisLabel?: string
 */

export default function ThresholdBarChart({
  threshold = 0,
  xAxisLabel = "IDs",
  yAxisLabel = "Values",
  xDataKey = "name",
  yDataKey1 = "quantity"
}) {

  const [chartData, setChartData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {    const fetchChartData = async () => {
      try {
        // setLoading(true);

        const response = await fetch(
          API_LINK
        );

        if (!response.ok) {
          throw new Error("Failed to fetch chart data");
        }

        const data = await response.json();

        // expected shape:
        // [
        //   { id: "id001", raw: 10, withOrders: 15 },
        //   ...
        // ]

        setChartData(data);
      } catch (err) {
        console.error(err);
        setError(err.message);
      } finally {
        // setLoading(false);
      }
    };

    fetchChartData();

  }, [])

  return (
    <div style={{ width: "80%", height: "80%" }}>
      <ResponsiveContainer width="100%" height="100%">
        <BarChart
          data={chartData}
          margin={{
            top: 20,
            right: 30,
            left: 20,
            bottom: 50,
          }}
          barGap={6}
        >
          {/* Grid */}
          <CartesianGrid
            strokeDasharray="3 3"
            vertical={false}
          />

          {/* X Axis */}
          <XAxis dataKey={xDataKey}>
            <Label
              value={xAxisLabel}
              position="insideBottom"
              offset={-10}
            />
          </XAxis>

          {/* Y Axis */}
          <YAxis>
            <Label
              value={yAxisLabel}
              angle={-90}
              position="insideLeft"
              style={{ textAnchor: "middle" }}
            />
          </YAxis>

          {/* Tooltip */}
          <Tooltip />

          {/* Threshold dashed line */}
          <ReferenceLine
            y={threshold}
            stroke="#D9D9D9"
            strokeWidth={2}
            strokeDasharray="8 8"
            label={{
              value: `Threshold (${threshold})`,
              position: "insideTopRight",
              fill: "#D9D9D9",
              fontSize: 12,
            }}
          />

          {/* First Bar */}
          <Bar
            dataKey={yDataKey1}
            name="Raw"
            fill={BAR_1_COLOR}
            radius={[4, 4, 0, 0]}
          />

          {/* Second Bar */}
          {/* <Bar
            dataKey="withOrders"
            name="With Orders"
            fill="#26A69A"
            radius={[4, 4, 0, 0]}
          /> */}
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
}