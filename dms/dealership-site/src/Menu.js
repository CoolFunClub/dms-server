import React, { useState, useEffect } from 'react';
import './Menu.css';
import logo from "./assets/logo-placeholder.png";

function LogoBar() {
  return (
    <div className="LogoBar">
      <img src={logo} alt="Car dealership logo" />
    </div>
  );
}

function NavBarAndContent() {
  return (
    <div className="NavBarAndContent">
      <NavBar />
      <MainContent />
    </div>
  );
}

function NavBar() {
  return (
    <div className="NavBar">
      <p>
        buttons
      </p>
    </div>
  );
}

function MainContent() {
  return (
    <div className="MainContent">
      <p>Welcome!</p>
    </div>
  );
}

function App() {
  // const [msg, setMsg] = useState('');

  // useEffect(() => {
  //   const getMsg = async () => {
  //     const data = await fetch("http://localhost:8080/cars");
  //     const msgJson = await data.json();
  //     console.log(msgJson);
  //     setMsg(msgJson[0].vin);
  //   }
  //   getMsg();
  // }, []);

  return (
    <div>
      <LogoBar />
      <NavBarAndContent />
    </div>
  );
}

export default App;
