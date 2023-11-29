import React, { useState, useEffect } from "react";
import './SignUp.css';
import logo from './assets/logo_cropped.png';


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

function checkPasswords() {
	return document.getElementById("password").value == document.getElementById("confirm").value;
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
				<button className="SubmitBtn">Submit</button> :
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