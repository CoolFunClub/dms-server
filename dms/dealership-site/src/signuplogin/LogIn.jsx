import React from "react";
import { useNavigate } from "react-router-dom";
import './Login.css';
import { useLoginData } from "./LoginContext";
import logo from '../assets/logo_cropped.png';


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
	const { acct, updateAcct } = useLoginData();
	const navigate = useNavigate();

	function login() {
		const id = document.getElementById("dlNum").value;
		const username = document.getElementById("username").value;

		const accountDto = {
			userName: username,
			pw: document.getElementById("password").value,
		};

		fetch(`https://www.afkauto.com/api/login/customer/${id}`, {
			body: JSON.stringify(accountDto),
			method: "POST",
				headers: {
			"Content-Type": "application/json",
			},
		}).then((response) => {
			console.log("*** response after attempting login");
			console.log(response);

			if (response.status === 200) {
				updateAcct({ type: "customer", user: username, id: id });
				navigate("/");
			}
		});
	}

	return (
		<div className="MainForm">
			<label htmlFor="dlNum">Driver's license #</label>
			<input id="dlNum" />
			<label htmlFor="username">Username</label>
			<input id="username" />
			<label htmlFor="password">Password</label>
			<input type="password" id="password" />
			<button className="SubmitBtn" onClick={()=>{login()}}>Login</button>
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
