import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./SignUp.css";
import { Header, FormItem } from "./SignUp";


function checkPasswords() {
	return document.getElementById("password").value === document.getElementById("confirm").value;
}

async function submitSignUp() {
	const employeeType = document.getElementById("employeetype").value;
	const entityUrl = employeeType === "manager" ? "manager" : "cfc/rep";
	// make employee entity
	const id = parseInt(document.getElementById("id").value);
	const employee = {
		firstName: document.getElementById("fn").value,
		lastName: document.getElementById("ln").value,
		dateBirth: document.getElementById("dob").value,
		gender: document.getElementById("gender").value,
		phone: parseInt(document.getElementById("phonenum").value),
		email: document.getElementById("email").value,
		address: document.getElementById("address").value,
		ssn: id,
	};

	await fetch(`https://www.afkauto.com/api/${entityUrl}`, {
		body: JSON.stringify(employee),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log(`*** ${employeeType} entity made`);
	// make employee account
	const userNamePw = {
		userName: document.getElementById("username").value,
		pw: document.getElementById("password").value,
	};

	const data = await fetch(`https://www.afkauto.com/api/account/${employeeType}/${id}`, {
		body: JSON.stringify(userNamePw),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	const msgJson = await data.json();
	console.log(`*** ${employeeType} account made`);
	console.log(msgJson);
}

function MainForm() {
	const [doesMatch, setDoesMatch] = useState(true);
	const navigate = useNavigate();

	return (
		<div className="MainForm">
			<div className="FormRow">
				<label htmlFor="employeetype">Employee Type</label>
				<select id="employeetype">
					<option value="manager">Manager</option>
					<option value="salesRep">Salesperson</option>
				</select>
			</div>
			<div className="FormRow">
				<FormItem id="fn" label="First name" />
				<FormItem id="ln" label="Last name" />
				<FormItem id="dob" label="Date of birth" />				
			</div>

			<div className="FormRow">
				<FormItem id="gender" label="Gender" />
				<FormItem id="phonenum" label="Phone number" />
				<FormItem id="id" label="ID" />
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

function EmployeeSignUpPage() {
  return (
		<div className="SignUpPage">
			<Header type="Manager" />
			<MainForm />
		</div>
  );
}

export default EmployeeSignUpPage
