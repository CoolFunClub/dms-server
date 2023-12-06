import React from "react";
import logo from "../assets/MenuLogo.png";


export function Header() {
  return (
    <div className="Header">
      <a href="/">
        <img className="ClickableLogo" src={logo} alt="Go back to the home page" />
      </a>
      Login
    </div>
  );
}
