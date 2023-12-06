import React from "react";
import logo from "../assets/MenuLogo.png";


export function Header({ type }) {
	return (
		<div className="Header">
			<a href="/">
				<img className="ClickableLogo" src={logo} alt="Go back to the home page" />
			</a>
			{`${type} sign up`}
		</div>
	);
}

export function FormItem({id, label, type, onChange}) {
	return (
		<div className="FormItem">
			<label htmlFor={id}>{label}</label>
			<input id={id} type={type || ""} onChange={onChange || ""}/>
		</div>
	);
}
