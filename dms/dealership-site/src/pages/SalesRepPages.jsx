import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Pages.css";
import { EDIT_INV, MAKE_SALE } from "./PageNumbers";
import { InputCell, CarCard } from "./Pages";


export function EditInventory({ page }) {
	const [car, setCar] = useState();
	const visClass = page === EDIT_INV ? "Page" : "Hidden";

	async function getCar(vin) {
		const data = await fetch(`https://www.afkauto.com/api/cars/${vin}`, {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
			},
		});

		const msgJson = await data.json();
		setCar(msgJson);
		console.log("*** got car");
		console.log(msgJson);
	}

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
			<div className="Tile Card">
				<label className="ContactLabel" htmlFor="getVin">VIN</label>
				<input id="getVin" />
				<div
					className="ActionBtn"
					onClick={async ()=>{await getCar(document.getElementById("getVin").value)}}
				>
					View car
				</div>
				{car !== undefined &&
					<>
						<div
							className="ActionBtn"
							onClick={async ()=>{await deleteCar(car.vin)}}
						>
							Delete car
						</div>
						<CarInput car={car} />
						<div className="ActionBtn" onClick={async ()=>{await editCar(car.vin)}}>Update car</div>
					</>
				}
			</div>
		</div>
	);
}

function CarInput({ car }) {
	return (
		<div className="Car Card">
			<InputCell id="editVin" label="VIN" value={car.vin} />
			<InputCell id="editMan" label="Manufacturer" value={car.manufacturer} />
			<InputCell id="editModel" label="Model" value={car.model} />
			<InputCell id="editYear" label="Year" value={car.carYear} />
			<InputCell id="editMileage" label="Mileage" value={car.mileage} />
			<InputCell id="editTrim" label="Trim" value={car.trim} />
			<InputCell id="editColor" label="Color" value={car.color} />
			<InputCell id="editPrice" label="Price" value={car.price} />
			<InputCell id="editStatus" label="Status" value={car.status} />
		</div>
	);
}

async function addCar() {
	const body = {
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

async function deleteCar(vin) {
	const data = await fetch(`https://www.afkauto.com/api/cars/${vin}`, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** deleted car");
	console.log(data);
}

async function editCar(vin) {
	const body = {
		vin: vin,
		manufacturer: document.getElementById("editMan").value,
		model: document.getElementById("editModel").value,
		carYear: parseInt(document.getElementById("editYear").value),
		mileage: parseInt(document.getElementById("editMileage").value),
		trim: document.getElementById("editTrim").value,
		color: document.getElementById("editColor").value,
		status: document.getElementById("editStatus").value,
		price: parseFloat(document.getElementById("editPrice").value),
	};

	const data = await fetch(`https://www.afkauto.com/api/cars/${vin}`, {
		body: JSON.stringify(body),
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
	});

	console.log("*** edited car");
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
