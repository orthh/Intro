import React from 'react';
import logo from './logo.svg';
import './App.css';
import BasicEditor from 'components/editor/BasicEditor';
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
