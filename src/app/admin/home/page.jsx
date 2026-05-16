"use client";

import { useState } from "react";
import "./styles.css";
import MainBox from "./mainBox";

export default function Home() {
  const [showThresholdModal, setShowThresholdModal] = useState(false);
  const [formData, setFormData] = useState({
    expirationThreshold: "",
    criticallyLowThreshold: "",
  });

  const openThresholdModal = () => setShowThresholdModal(true);
  const closeThresholdModal = () => setShowThresholdModal(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleApply = (e) => {
    e.preventDefault();

    const expirationThreshold = Number(formData.expirationThreshold);
    const criticallyLowThreshold = Number(formData.criticallyLowThreshold);

    if (Number.isNaN(expirationThreshold) || Number.isNaN(criticallyLowThreshold)) {
      alert("Please enter valid numbers for both thresholds.");
      return;
    }

    console.log("Applied Thresholds:", {
      expirationThreshold,
      criticallyLowThreshold,
    });

    closeThresholdModal();
  };

  return (
    <div className="app-shell">
      <aside className="vertical-nav" aria-label="Vertical navigation">
        <button className="nav-item is-selected" type="button" aria-current="page">
          Home
        </button>

        <button
          className="nav-item"
          type="button"
          onClick={openThresholdModal}
        >
          Thresholds
        </button>

        <div className="nav-separator">Orders</div>
        <button className="nav-item" type="button">Product Orders</button>
        <button className="nav-item" type="button">Customer Orders</button>

        <div className="nav-separator">Databases</div>
        <button className="nav-item" type="button">Product</button>
        <button className="nav-item" type="button">Customer</button>

        <div className="profile-card" tabIndex="0" aria-label="Admin profile">
          <div className="profile-avatar" aria-hidden="true">
            <svg viewBox="0 0 96 96" role="img" aria-label="Profile picture placeholder">
              <circle cx="48" cy="48" r="48"></circle>
              <circle className="avatar-face" cx="48" cy="37" r="15"></circle>
              <path className="avatar-body" d="M22 82c5.8-14.8 19.2-24 26-24s20.2 9.2 26 24" />
            </svg>
          </div>
          <div className="profile-text">
            <div className="profile-name">J.K Amare</div>
            <div className="profile-role">admin</div>
          </div>
        </div>
      </aside>

      <main className={`main-pane ${showThresholdModal ? "blurred" : ""}`}>
        <MainBox />
      </main>

      {showThresholdModal && (
        <div className="modal-backdrop" onClick={closeThresholdModal}>
          <div
            className="threshold-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="threshold-title"
            onClick={(e) => e.stopPropagation()}
          >
            <div className="modal-header">
              <h2 id="threshold-title">Threshold Settings</h2>
              <button
                type="button"
                className="close-btn"
                onClick={closeThresholdModal}
                aria-label="Close dialog"
              >
                ×
              </button>
            </div>

            <p className="modal-subtitle">
              Update the stock alert thresholds below.
            </p>

            <form onSubmit={handleApply} className="threshold-form">
              <label className="field-group">
                <span>Expiration threshold (days)</span>
                <input
                  type="number"
                  name="expirationThreshold"
                  value={formData.expirationThreshold}
                  onChange={handleChange}
                  placeholder="e.g. 7"
                  min="0"
                />
              </label>

              <label className="field-group">
                <span>Critically low threshold</span>
                <input
                  type="number"
                  name="criticallyLowThreshold"
                  value={formData.criticallyLowThreshold}
                  onChange={handleChange}
                  placeholder="e.g. 10"
                  min="0"
                />
              </label>

              <div className="modal-actions">
                <button
                  type="button"
                  className="cancel-btn"
                  onClick={closeThresholdModal}
                >
                  Cancel
                </button>
                <button type="submit" className="apply-btn">
                  Apply Changes
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

// import "./styles.css"
// import MainBox from "./mainBox"

// export default function home() {
//     return (
//         <div className="app-shell">
//           <aside className="vertical-nav" aria-label="Vertical navigation">
//             <button className="nav-item is-selected" type="button" aria-current="page">Home</button>
//             <button className="nav-item" type="button">Thresholds</button>

//             <div className="nav-separator">Orders</div>
//             <button className="nav-item" type="button">Product Orders</button>
//             <button className="nav-item" type="button">Customer Orders</button>

//             <div className="nav-separator">Databases</div>
//             <button className="nav-item" type="button">Product</button>
//             <button className="nav-item" type="button">Customer</button>

//             <div className="profile-card" tabIndex="0" aria-label="Admin profile">
//               <div className="profile-avatar" aria-hidden="true">
//                 <svg viewBox="0 0 96 96" role="img" aria-label="Profile picture placeholder">
//                   <circle cx="48" cy="48" r="48"></circle>
//                   <circle className="avatar-face" cx="48" cy="37" r="15"></circle>
//                   <path className="avatar-body" d="M22 82c5.8-14.8 19.2-24 26-24s20.2 9.2 26 24" />
//                 </svg>
//               </div>
//               <div className="profile-text">
//                 <div className="profile-name">J.K Amare</div>
//                 <div className="profile-role">admin</div>
//               </div>
//             </div>
//           </aside>

//           <main className="main-pane">
//               <MainBox/>
//           </main>
//         </div>

//     );
// }

