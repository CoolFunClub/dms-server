import React from "react";
import "./Pages.css";
import { WELCOME } from "./PageNumbers";
import welcomeCar from "../assets/welcome-car.jpg";


export function InfoCell({header, value}) {
	return (
		<div className="InfoCell">
			<b>{header}</b>&nbsp;
			{value}
		</div>
	);
}

export function WelcomePage({ page }) {
	const pageClass = page === WELCOME ? "" : "Hidden";

	return (
		<div className={pageClass}>
			<div className="Welcome Page">
				Welcome!
				<img className="center" src={welcomeCar} alt="Clipart of a red sports car with a teal background" />
			</div>
		</div>
	)
}
