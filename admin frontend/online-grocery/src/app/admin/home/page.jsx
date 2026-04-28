import "./styles.css"
import { Notifications } from "./components.js"

export default function home() {
    return (
        <div className="app-shell">
          <aside className="vertical-nav" aria-label="Vertical navigation">
            <button className="nav-item is-selected" type="button" aria-current="page">Home</button>

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

          <main className="main-pane">
            <nav className="horizontal-nav" aria-label="Horizontal navigation">
              <button className="pill is-selected" type="button" aria-current="page">Notification</button>
              <button className="pill" type="button">Overview</button>
            </nav>

            
            <section className="events-area" aria-labelledby="events-title">
              <h1 id="events-title">EVENTS</h1>

              <Notifications/>

            </section>

          </main>
        </div>

    );
}

