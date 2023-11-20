import React from 'react';
import './App.css';
import Header from 'pages/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from 'pages/sign/Login';
import Main from 'pages/main/Main';
import Signup from 'pages/sign/Signup';
import AddProduct from 'pages/product/AddProduct';
import Pay from 'pages/payment/Pay';
import MyPage from 'pages/mypage/MyPage';

function App() {

  

  return (
    <Router>
      <div className="App font-pretendard">
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path="/addproduct" element={<AddProduct />} />
          <Route path="/pay" element={<Pay />} />
          <Route path="/mypage" element={<MyPage />} />

        </Routes>
      </div>
    </Router>
    
  );
}

export default App;
