import React, { useEffect, useState } from "react";
import "./Pages.css";
import { VIEW_REPS, VIEW_CUSTOMERS, VIEW_MANAGERS, MANAGE_CUSTOMERS } from "./PageNumbers";
import { InfoCell, InputCell } from "./Pages";


export function ManageCustomers({ page }) {
	const [id, setId] = useState("");
	const [acctType, setAcctType] = useState("manager");
	const [acct, setAcct] = useState();
	const visClass = page === MANAGE_CUSTOMERS ? "flexCol" : "Hidden";

	return (
		<div className={visClass}>
			<label className="marginTop" htmlFor="acctType">Account type</label>
			<select id="acctType" value={acctType} onChange={(event)=>{setAcctType(event.target.value)}}>
				<option value="manager">Manager</option>
				<option value="cfc/rep">Sales representative</option>
				<option value="cfc/customers">Customer</option>
			</select>
				<button className="ActionBtn">
					<a href={`https://afkauto.com/api/${acctType}`} target="_blank" rel="noreferrer">
						View all accounts of this type
					</a>
				</button>
			<label className="marginTop" htmlFor="dlNum">Driver's license #/ID&nbsp;</label>
			<input id="dlNum" onChange={(event)=>{setId(event.target.value)}}/>
			<button
				className="ActionBtn"
				onClick={async ()=>{setAcct(await getUser(id, acctType))}}
			>
				View account
			</button>
			{acct !== undefined &&
				<>
					<AccountFields acct={acct} />
					<button
						className="ActionBtn"
						onClick={async () => {await deleteUser(id, acctType)}}
					>
						Delete account
					</button>
					<button
						className="ActionBtn"
						onClick={async ()=>{await editUser(id, acctType)}}
					>
						Submit changes
					</button>
				</>}
		</div>
	);
}

function AccountFields({ acct }) {
	return (
		<div key={acct.ssn} className="Tile Card">
			<InputCell id="fn" label="First name" value={acct.firstName} />
			<InputCell id="ln" label="Last name" value={acct.lastName} />
			<InputCell id="id" label="Driver's license #/ID" value={acct.ssn || acct.driverLicenseID} />
			<InputCell id="email" label="Email" value={acct.email} />
			<InputCell id="dob" label="DoB" value={acct.dateBirth} />
			<InputCell id="gender" label="Gender" value={acct.gender} />
			<InputCell id="phone" label="Phone #" value={acct.phone} />
			<InputCell id="address" label="Address" value={acct.address} />
		</div>
	);
}

async function editUser(id, acctType) {
	const identifier = acctType === "cfc/customers" ? "driverLicenseID" : "ssn";
	const body = {
		firstName: document.getElementById("fn").value,
		lastName: document.getElementById("ln").value,
		dateBirth: document.getElementById("dob").value,
		gender: document.getElementById("gender").value,
		phone: parseInt(document.getElementById("phone").value),
		email: document.getElementById("email").value,
		address: document.getElementById("address").value,
	};
	body[identifier] = document.getElementById("id").value;

	const data = await fetch(`https://www.afkauto.com/api/${acctType}/${id}`, {
		body: JSON.stringify(body),
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** got edit response");
	console.log(data);
}

async function deleteUser(id, acctType) {
	const data = await fetch(`https://www.afkauto.com/api/${acctType}/${id}`, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** got delete response");
	console.log(data);
}

async function getUser(id, acctType) {
	const data = await fetch(`https://www.afkauto.com/api/${acctType}/${id}`,	{
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		},
	});

	const msgJson = await data.json();
	console.log("*** got get response");
	console.log(msgJson);

	return msgJson;
}

export function ViewManagers({ page }) {
	const visClass = page === VIEW_MANAGERS ? "ViewSalesReps Page" : "Hidden";
	const [managers, setManagers] = useState([]);

	useEffect(() => {
		const getMsg = async () => {
			const data = await fetch("https://www.afkauto.com/api/manager", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const msgJson = await data.json();
			setManagers(msgJson);
		};

		getMsg();
	}, []);

	const managerList = [];

	for (const m of managers) {
		if (m.account) {
			managerList.push(<EmployeeCard rep={m} />);
		}
	}

	return (
		<div className={visClass}>
			{managerList}
		</div>
	);
}

export function ViewCustomers({ page }) {
	const visClass = page === VIEW_CUSTOMERS ? "ViewSalesReps Page" : "Hidden";
	const [customers, setCustomers] = useState([]);

	useEffect(() => {
		const getMsg = async () => {
			const data = await fetch("https://www.afkauto.com/api/cfc/customers", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const msgJson = await data.json();
			setCustomers(msgJson);
		};

		getMsg();
	}, []);

	const customerList = [];

	for (const c of customers) {
		if (c.account) {
			customerList.push(<CustomerCard customer={c} />);
		}
	}

	return (
		<div className={visClass}>
			{customerList}
		</div>
	);
}

function CustomerCard({ customer }) {
	return (
		<div key={customer.driverLicenseID} className="Tile Card">
			<b>{`${customer.firstName} ${customer.lastName}`}</b>
			<InfoCell id="username" label="Username:" value={customer.account.userName} />
			<InfoCell id="dlNum" label="Driver's license #:" value={customer.driverLicenseID} />
			<InfoCell id="email" label="Email:" value={customer.email} />
			<InfoCell id="dob" label="DoB:" value={customer.dateBirth} />
			<InfoCell id="gender" label="Gender:" value={customer.gender} />
			<InfoCell id="phone" label="Phone #:" value={customer.phone} />
			<InfoCell id="address" label="Address:" value={customer.address} />
		</div>
	);
}

export function ViewSalesReps({ page }) {
	const visClass = page === VIEW_REPS ? "ViewSalesReps Page" : "Hidden";
	const [reps, setReps] = useState([]);

	useEffect(() => {
		const getMsg = async () => {
			const data = await fetch("https://www.afkauto.com/api/cfc/rep", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const msgJson = await data.json();
			setReps(msgJson);
		};

		getMsg();
	}, []);

	const repList = [];

	for (const r of reps) {
		if (r.account) {
			repList.push(<EmployeeCard rep={r} />);
		}
	}

	return (
		<div className={visClass}>
			{repList}
		</div>
	);
}

function EmployeeCard({ rep }) {
	return (
		<div key={rep.ssn} className="Tile Card">
			<b>{`${rep.firstName} ${rep.lastName}`}</b>
			<InfoCell label="Username:" value={rep.account.userName} />
			<InfoCell label="ID:" value={rep.ssn} />
			<InfoCell label="Email:" value={rep.email} />
			<InfoCell label="DoB:" value={rep.dateBirth} />
			<InfoCell label="Gender:" value={rep.gender} />
			<InfoCell label="Phone #:" value={rep.phone} />
			<InfoCell label="Address:" value={rep.address} />
		</div>
	);
}
