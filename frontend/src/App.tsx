import React from 'react';
import './App.css';
import Header from 'pages/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from 'pages/sign/Login';
import Main from 'pages/main/Main';

function App() {
  return (
    <Router>
      <div className="App font-pretendard">
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
    
  );
}

export default App;
