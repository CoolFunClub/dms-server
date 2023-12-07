import React, { useEffect, useState } from "react";
import "./Pages.css"
import { EMAIL_REP } from "./PageNumbers";


export function EmailRep({ page }) {
	const [reps, setReps] = useState([]);
	const [repEmail, setRepEmail] = useState("");
	const pageClass = page === EMAIL_REP ? "flexCol" : "Hidden";

	useEffect(() => {
		const getReps = async () => {
			const data = await fetch("https://www.afkauto.com/api/cfc/rep", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const repsJson = await data.json();
			setReps(repsJson);

			if (repsJson.length > 0) {
				setRepEmail(repsJson[0].email);
			}
		}

		getReps();
	}, []);

	const repList = reps.map((r) => {
		return (<option key={r.ssn} value={r.email}>{`${r.firstName} ${r.lastName}`}</option>);
	});

	return (
		<div className={pageClass}>
			<div className="Tile Card">
				<label className="ContactLabel" htmlFor="rep">Choose a sales representative.</label>
				{reps.length > 1 ?
					<div>
						<select id="rep"
							onChange={(event) => {
								setRepEmail(event.target.value);
							}}
						>
							{repList}
						</select>

						<a href={`mailto:${repEmail}?subject=${encodeURIComponent("Buying a car")}`}>
							<button className="Button SendEmail" >
								Send them an email
							</button>
						</a>
					</div>
					: <p id="rep">No sales representatives found</p>
				}
			</div>
		</div>
	);
}