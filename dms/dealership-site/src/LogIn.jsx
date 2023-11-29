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
		<div className="MainForm">
			<label for="username">Username</label>
			<input id="username" />
			<label for="password">Password</label>
			<input id="password" />
			<div>
				<button className="SubmitBtn">Login</button> or sign up for an account
			</div>
		</div>
	);
}

function LoginPage() {
  return (
		<div className="LoginPage">
			<Header />
			<MainForm />
		</div>
  );
}

export default LoginPage