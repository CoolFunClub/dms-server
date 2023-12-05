import React from "react";
import { useNavigate } from "react-router-dom";
import './Login.css';
import { useLoginData } from "./LoginContext";
import { Header } from "./Login";


function MainForm() {
	const { updateAcct } = useLoginData();
	const navigate = useNavigate();

	function login() {
		const employeeType = document.getElementById("employeetype").value;
		const id = document.getElementById("id").value;
		const username = document.getElementById("username").value;

		const accountDto = {
			userName: username,
			pw: document.getElementById("password").value,
		};

		fetch(`https://www.afkauto.com/api/login/${employeeType}/${id}`, {
			body: JSON.stringify(accountDto),
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
		}).then((response) => {
			console.log("*** response after attempting login");
			console.log(response);

			if (response.status === 200) {
				updateAcct({ type: employeeType, user: username, id: id });
				navigate("/");
			}
		});
	}

	return (
		<div className="MainForm">
			<label htmlFor="employeetype">Employee Type</label>
			<select id="employeetype">
				<option value="manager">Manager</option>
				<option value="salesRep">Salesperson</option>
			</select>

			<label htmlFor="id">ID</label>
			<input id="id" />
			<label htmlFor="username">Username</label>
			<input id="username" />
			<label htmlFor="password">Password</label>
			<input type="password" id="password" />
			<button className="SubmitBtn" onClick={()=>{login()}}>Login</button>
		</div>
	);
}

function EmployeeLoginPage() {
	return (
		<div className="LoginPage">
			<Header />
			<MainForm />
		</div>
	);
}

export default EmployeeLoginPage
