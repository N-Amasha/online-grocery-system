"use client";

import { useEffect, useState } from "react";

export default function ThresholdForm({ isOpen, onClose }) {
  const [formData, setFormData] = useState({
    expirationThreshold: "",
    criticallyLowThreshold: "",
  });

  const [loading, setLoading] = useState(false);

  // Fetch thresholds when modal opens
  useEffect(() => {
    if (isOpen) {
      fetchThresholds();
    }
  }, [isOpen]);

  const fetchThresholds = async () => {
    try {
      setLoading(true);

      const response = await fetch(
        "http://localhost:8080/admin/thresholds/get"
      );

      const data = await response.json();

      setFormData({
        expirationThreshold: data.expiry,
        criticallyLowThreshold: data.quantity,
      });
    } catch (error) {
      console.error("Failed to fetch thresholds:", error);
      alert("Could not fetch threshold values.");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleApply = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);

      const expiry = Number(formData.expirationThreshold);
      const quantity = Number(formData.criticallyLowThreshold);

      const response = await fetch(
        `http://localhost:8080/admin/thresholds/set?expiry=${expiry}&quantity=${quantity}`
      );

      const data = await response.json();

      if (data.code === 200) {
        alert("Thresholds updated successfully.");
        onClose();
      } else {
        alert("Failed to update thresholds.");
      }
    } catch (error) {
      console.error("Update failed:", error);
      alert("Error updating thresholds.");
    } finally {
      setLoading(false);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div
        className="threshold-modal"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="modal-header">
          <h2>Threshold Settings</h2>
          <button className="close-btn" onClick={onClose}>
            ×
          </button>
        </div>

        <p className="modal-subtitle">
          Update stock alert thresholds
        </p>

        <form onSubmit={handleApply} className="threshold-form">
          <label className="field-group">
            <span>Expiration threshold (days)</span>
            <input
              type="number"
              name="expirationThreshold"
              value={formData.expirationThreshold}
              onChange={handleChange}
              min="0"
              required
            />
          </label>

          <label className="field-group">
            <span>Critically low threshold</span>
            <input
              type="number"
              name="criticallyLowThreshold"
              value={formData.criticallyLowThreshold}
              onChange={handleChange}
              min="0"
              required
            />
          </label>

          <div className="modal-actions">
            <button
              type="button"
              className="cancel-btn"
              onClick={onClose}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="apply-btn"
              disabled={loading}
            >
              {loading ? "Applying..." : "Apply Changes"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}