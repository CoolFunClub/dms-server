import React from "react";
import { VIEW_REPS } from "./PageNumbers";


export function ViewSalesReps({ page }) {
	const visClass = page === VIEW_REPS ? "" : "Hidden";
	const reps = [];

	fetch("https://www.afkauto.com/api/rep", {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
		},
	}).then((response) => {
		const repList = response.json();

		for (const r in repList) {
			console.log(r);
			reps.push(<div key={r.user}>{r.user}</div>)
		}
	});

	return (
		<div className={visClass}>
			{reps}
		</div>
	);
}