import React from "react";
import { useNavigate } from "react-router-dom";
import "./Pages.css";
import { MAKE_SALE } from "./PageNumbers";
import { InputCell } from "./Pages";


export function MakeSalePage({ page }) {
	const visClass = page === MAKE_SALE ? "Page" : "Hidden"; 
	const navigate = useNavigate();

	return (
		<div className={visClass}>
			<div className="SaleForm Card">
				<InputCell id="accountId" label="Account ID" />
				<InputCell id="purDate" label="Purchase date" />
				<InputCell id="tax" label="Tax" />
				<InputCell id="registrationFee" label="Registration fee" />
				<InputCell id="totalPaid" label="Total paid" />
				<button
					className="Button"
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
