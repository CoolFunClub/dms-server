import React from "react";
import { useNavigate } from "react-router-dom";
import "./Pages.css";
import { EDIT_INV, MAKE_SALE } from "./PageNumbers";
import { InputCell } from "./Pages";


export function EditInventory({ page }) {
	const visClass = page === EDIT_INV ? "Page" : "Hidden";

	return (
		<div className={visClass}>
			<div className="Tile Card">
				<InputCell id="vin" label="VIN" />
				<InputCell id="manufacturer" label="Manufacturer" />
				<InputCell id="model" label="Model" />
				<InputCell id="carYear" label="Year" />
				<InputCell id="mileage" label="Mileage" />
				<InputCell id="trim" label="Trim" />
				<InputCell id="color" label="Color" />
				<InputCell id="price" label="Price" />
				<InputCell id="status" label="Status" />
				<div className="ActionBtn" onClick={async ()=>{await addCar()}}>Add car</div>
			</div>
		</div>
	);
}

async function addCar() {
	const body = {
		vin: document.getElementById("vin").value,
		manufacturer: document.getElementById("manufacturer").value,
		model: document.getElementById("model").value,
		carYear: parseInt(document.getElementById("carYear").value),
		mileage: parseInt(document.getElementById("mileage").value),
		trim: document.getElementById("trim").value,
		color: document.getElementById("color").value,
		status: document.getElementById("status").value,
		price: parseFloat(document.getElementById("price").value),
	};

	const data = await fetch("https://www.afkauto.com/api/cars", {
		body: JSON.stringify(body),
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** added car");
	console.log(data);
}

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

// this function can't be async because it is followed by a redirect immediately after finishing
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
