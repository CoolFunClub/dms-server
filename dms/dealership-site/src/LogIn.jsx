import React from "react";
import { useNavigate } from "react-router-dom";
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

async function login() {
	const id = document.getElementById("dlNum").value;
	const accountDto = {
		userName: document.getElementById("username").value,
		pw: document.getElementById("password").value,
	};

	await fetch(`https://www.afkauto.com/api/login/${id}`, {
		body: JSON.stringify(accountDto),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});
}

function MainForm() {
	const navigate = useNavigate();

	return (
		<div className="MainForm">
			<label htmlFor="dlNum">Driver's license #</label>
			<input id="dlNum" />
			<label htmlFor="username">Username</label>
			<input id="username" />
			<label htmlFor="password">Password</label>
			<input type="password" id="password" />
			<button className="SubmitBtn" onClick={async ()=>{
				await login();
				navigate("/");
			}}
			>
				Login
			</button>
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