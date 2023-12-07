import React, { useEffect, useState } from "react";
import "./Pages.css";
import { WELCOME, VIEW_CARS } from "./PageNumbers";
import welcomeCar from "../assets/welcome-car.jpg";
import carPlaceholder from "../assets/CarPlaceholder.png";


export function InfoCell({id, label, value}) {
	return (
		<div className="FormCols">
			<label htmlFor={id}>
				<b>{label}</b>
			</label>
			{value}
		</div>
	);
}

export function InputCell({id, label, value}) {
	return (
		<div className="FormCols">
			<label htmlFor={id}>
				<b>{label}</b>
			</label>
			<input id={id} defaultValue={value} />
		</div>
	);
}

export function WelcomePage({ page }) {
	const pageClass = page === WELCOME ? "Welcome Page" : "Hidden";

	return (
		<div className={pageClass}>
			Welcome!
			<img
				className="center"
				src={welcomeCar}
				alt="Clipart of a red sports car with a teal background"
			/>
		</div>
	);
}

export function ViewCars({ page }) {
	const pageClass = page === VIEW_CARS ? "Page" : "Hidden";
	const [carList, setCarList] = useState([]);

	// only causes a CORS error when testing locally
	useEffect(() => {
		const getMsg = async () => {
			const data = await fetch("https://www.afkauto.com/api/cars", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const msgJson = await data.json();
			setCarList(msgJson);
		}
		getMsg();
	}, []);

	const results = [];

	if (carList.length > 0) {
		for (const car of carList) {
			results.push(
				<div key={car.vin} >
					<img className="CarPic" src={carPlaceholder} alt="Placeholder icon for a car" />
					<CarCard car={car} />
				</div>
			);
		}
	} else {
		results.push(<p key="no-cars" >No cars found!</p>);
	}

	return (
		<div className={pageClass}>
			{results}
		</div>
	);
}

export function CarCard({ car }) {
	return (
		<div className="Car Card">
			<b>{`${car.carYear} ${car.manufacturer} ${car.model}`}</b>
			<InfoCell label={"VIN:"} value={car.vin} />
			<InfoCell label={"Status:"} value={car.status} />
			<InfoCell label={"Mileage:"} value={car.mileage} />
			<InfoCell label={"Price:"} value={car.price} />
			<InfoCell label={"Color:"} value={car.color} />
			<InfoCell label={"Trim:"} value={car.trim} />
		</div>
	);
}