import React from "react";
import './Login.css';
import logo from './assets/logo_cropped.png';

function Header() {
  return (
    <div className="Header">
      <a href="/">
        <img className="ClickableLogo" src={logo} alt="Go back to the home page" />
      </a>
      Login
    </div>
  );
}

function MainForm() {
  return (
    <Header />
  );
}

export default MainForm