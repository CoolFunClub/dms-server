import React, { useEffect, useState } from "react";
import "./Pages.css";
import { VIEW_REPS, VIEW_CUSTOMERS, VIEW_MANAGERS, MANAGE_CUSTOMERS } from "./PageNumbers";
import { InfoCell } from "./Pages";


export function ManageCustomers({ page }) {
	const visClass = page === MANAGE_CUSTOMERS ? "" : "Hidden";

	return (
		<div className={visClass}>
			<label htmlFor="dlNum">Driver's license #</label>
			<input id="dlNum" />
			<button
				onClick={async () => {await deleteUser(document.getElementById("dlNum").value)}}
			>
				Clear purchases
			</button>
		</div>
	);
}

async function deleteUser(dlNum) {
	const data = await fetch(`https://www.afkauto.com/api/cfc/customers/${dlNum}`, {
		method: "DELETE",
		headers: {
			'Content-Type': 'application/json',
		},
	});

	const msgJson = await data.json();
	console.log("*** got msg json");
	console.log(msgJson);
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
			managerList.push(<SalesRepCard rep={m} />);
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
		<div key={customer.dlNum} className="SalesRep Card">
			<b>{`${customer.firstName} ${customer.lastName}`}</b>
			<InfoCell header="Username:" value={customer.account.userName} />
			<InfoCell header="Driver's license #:" value={customer.dlNum} />
			<InfoCell header="Email:" value={customer.email} />
			<InfoCell header="DoB:" value={customer.dateBirth} />
			<InfoCell header="Gender:" value={customer.gender} />
			<InfoCell header="Phone #:" value={customer.phone} />
			<InfoCell header="Address:" value={customer.address} />
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
			repList.push(<SalesRepCard rep={r} />);
		}
	}

	return (
		<div className={visClass}>
			{repList}
		</div>
	);
}

function SalesRepCard({ rep }) {
	return (
		<div key={rep.ssn} className="SalesRep Card">
			<b>{`${rep.firstName} ${rep.lastName}`}</b>
			<InfoCell header="Username:" value={rep.account.userName} />
			<InfoCell header="ID:" value={rep.ssn} />
			<InfoCell header="Email:" value={rep.email} />
			<InfoCell header="DoB:" value={rep.dateBirth} />
			<InfoCell header="Gender:" value={rep.gender} />
			<InfoCell header="Phone #:" value={rep.phone} />
			<InfoCell header="Address:" value={rep.address} />
		</div>
	);
}
