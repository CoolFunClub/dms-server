import React, { useState } from "react";
import './SignUp.css';
import logo from './assets/logo_cropped.png';


function checkPasswords() {
	return document.getElementById("password").value === document.getElementById("confirm").value;
}

async function submitSignUp() {
	const dlNum = document.getElementById("dlnum").value;
	const userNamePw = {
		userName: document.getElementById("username").value,
		pw: document.getElementById("password").value
	};

	const data = await fetch(`https://www.afkauto.com/api/account/customer/${dlNum}`, {
		body: JSON.stringify(userNamePw),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	const msgJson = await data.json;
	console.log(msgJson);
}

function Header() {
  return (
    <div className="Header">
      <a href="/">
        <img className="ClickableLogo" src={logo} alt="Go back to the home page" />
      </a>
      Sign up
    </div>
  );
}

function MainForm() {
	const [doesMatch, setDoesMatch] = useState(true);

	return (
		<div className="MainForm">
			<label for="dlnum">Driver's license number</label>
			<input id="dlnum" />

			<label for="username">Username</label>
			<input id="username" />

			<label for="password">Password</label>
			<input type="password" id="password" onChange={()=>{setDoesMatch(checkPasswords())}} />

			<label for="confirm">Confirm password</label>
			<input type="password" id="confirm" onChange={()=>{setDoesMatch(checkPasswords())}} />

			{doesMatch ? 
				<button className="SubmitBtn" onClick={()=>{submitSignUp()}}>Submit</button> :
				<p className="PwdMsg">Passwords must match!</p>
			}
		</div>
	);
}

function SignUpPage() {
  return (
		<div className="SignUpPage">
			<Header />
			<MainForm />
		</div>
  );
}

export default SignUpPage