import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./SignUp.css";
import { Header, FormItem } from "./SignUp";


function checkPasswords() {
	return document.getElementById("password").value === document.getElementById("confirm").value;
}

async function submitSignUp() {
	// make customer entity
	const dlNum = document.getElementById("dlnum").value;
	const customer = {
		firstName: document.getElementById("fn").value,
		lastName: document.getElementById("ln").value,
		dateBirth: document.getElementById("dob").value,
		gender: document.getElementById("gender").value,
		phone: parseInt(document.getElementById("phonenum").value),
		email: document.getElementById("email").value,
		address: document.getElementById("address").value,
		driverLicenseID: dlNum,
	};

	await fetch("https://www.afkauto.com/api/cfc/addcustomers", {
		body: JSON.stringify(customer),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** Customer entity made");
	// make customer account
	const userNamePw = {
		userName: document.getElementById("username").value,
		pw: document.getElementById("password").value,
	};

	const data = await fetch(`https://www.afkauto.com/api/account/customer/${dlNum}`, {
		body: JSON.stringify(userNamePw),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	const msgJson = await data.json();
	console.log("*** Customer account made");
	console.log(msgJson);
}

function MainForm() {
	const [doesMatch, setDoesMatch] = useState(true);
	const navigate = useNavigate();

	return (
		<div className="MainForm">
			<div className="FormRow">
				<FormItem id="fn" label="First name" />
				<FormItem id="ln" label="Last name" />
				<FormItem id="dob" label="Date of birth" />				
			</div>

			<div className="FormRow">
				<FormItem id="gender" label="Gender" />
				<FormItem id="phonenum" label="Phone number" />
				<FormItem id="dlnum" label="Driver's license number" />
			</div>

			<FormItem id="address" label="Home address" />

			<div className="FormRow">
				<FormItem id="email" label="Email address" />
				<FormItem id="username" label="Username" />
			</div>

			<div className="FormRow">
				<FormItem 
						id="password" label="Password" type="password"
						onChange={()=>{setDoesMatch(checkPasswords())}}
					/>
				<FormItem
					id="confirm" label="Confirm password" type="password"
					onChange={()=>{setDoesMatch(checkPasswords())}}
				/>
			</div>

			{doesMatch ? 
				<button className="SubmitBtn" onClick={
					async ()=>{
						await submitSignUp();
						navigate("/");
					}
				}
				>
					Submit
				</button> :
				<p className="PwdMsg">Passwords must match!</p>
			}
		</div>
	);
}

function CustomerSignUpPage() {
  return (
		<div className="SignUpPage">
			<Header type="Customer" />
			<MainForm />
		</div>
  );
}

export default CustomerSignUpPage
