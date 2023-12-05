import React from "react";
import { useNavigate } from "react-router-dom";
import "./Pages.css";
import { MAKE_SALE } from "./PageNumbers";


export function MakeSalePage({ page }) {
	const visClass = page === MAKE_SALE ? "Page" : "Hidden"; 
	const navigate = useNavigate();

	return (
		<div className={visClass}>
			<div className="SaleForm Card">
				<FormCol
					ids={["accountId", "purDate", "tax", "registrationFee", "totalPaid"]}
					labels={["Account ID", "Today's date", "Tax", "Registration fee", "Total paid"]}
				/>
				<button
					onClick={() => {
						submitSale();
						navigate("/");
					}}
				>
					Confirm sale
				</button>
			</div>
		</div>
	);
}

function FormCol({ ids, labels}) {
	const labelCol = [];
	const inputCol = [];

	for (const row in ids) {
		inputCol.push(
			<input key={ids[row]} id={ids[row]} />
		);
		labelCol.push(
			<label key={ids[row]} htmlFor={ids[row]}>
				<b>{labels[row]}</b>
			</label>
		);
	}
	
	return (
		<div className="FormCols">
			<div className="LabelCol">{labelCol}</div>
			<div className="IdCol">{inputCol}</div>
		</div>
	);
}

function submitSale() {
	const acctId = parseInt(document.getElementById("accountId").value);
	const sale = {
		accountId: acctId,
		purDate: document.getElementById("purDate").value,
		tax: parseFloat(document.getElementById("purDate").value),
		registrationFee: parseFloat(document.getElementById("registrationFee").value),
		totalPaid: parseFloat(document.getElementById("totalPaid").value),
	}

	fetch(`https://www.afkauto.com/api/cfc/fullp/${acctId}`, {
			body: JSON.stringify(sale),
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
		}).then((response) => {
			console.log("*** Sales attempt response");
			console.log(response);
		});
}
