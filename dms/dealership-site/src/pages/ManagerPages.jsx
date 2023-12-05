import React, { useEffect, useState } from "react";
import "./Pages.css";
import { VIEW_REPS } from "./PageNumbers";
import { InfoCell } from "./Pages";


export function ViewSalesReps({ page }) {
	const visClass = page === VIEW_REPS ? "ViewSalesReps Page" : "Hidden";
	const [reps, setReps] = useState([]);

	useEffect(() => {
		const getMsg = async () => {
			const data = await fetch("https://www.afkauto.com/api/cfc/rep", {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
				},
			});

			const msgJson = await data.json();
			setReps(msgJson);
		};

		getMsg();
	}, []);

	const repList = [];

	for (const r of reps) {
		repList.push(<SalesRepCard rep={r} />);
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
