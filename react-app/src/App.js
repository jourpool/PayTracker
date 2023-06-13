import React, { Component } from "react";
import { Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import PaymentList from "./components/payment-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-dark bg-dark">
          <div className="container">
            <span className="navbar-brand" style={{width: '100%', color: '#fff', textAlign:'center'}}>PayTrack</span>
          </div>
        </nav>

        <div className="container mt-3">
          <Routes>
            <Route path="/" element={<PaymentList/>} />
          </Routes>
        </div>
      </div>
    );
  }
}

export default App;
