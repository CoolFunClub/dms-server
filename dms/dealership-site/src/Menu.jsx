import React, { useState, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo.png';
import { WelcomePage, ViewCars } from './Pages'
import { useLoginData } from './signuplogin/LoginContext';

const PageContext = createContext(1);

function LogoBar() {
	return (
		<div className="LogoBar">
			<img src={logo} alt="Car dealership logo" />
			<LoginGreeting />
		</div>
	);
}

function LoginGreeting() {
	const { acct } = useLoginData();
	const [signUpHovered, setSignUpHovered] = useState(false);
	const [loginHovered, setLoginHovered] = useState(false);

	console.log(`*** Currently logged in: ${acct.type} ${acct.user} with DL#/SSN ${acct.id}`);

	if (acct.user.length < 1) {
		const signUpClass = signUpHovered ? "SignUp Button" : "SignUp Button Collapsed";
		const loginClass = loginHovered ? "Login Button" : "Login Button Collapsed";

		return (
			<div className="AcctBtns top">
				<div className={loginClass} onClick={()=>{setLoginHovered(!loginHovered)}}>
					Login
					<DropDownLink isVisible={loginHovered} href={"/login/customer"} label="Customer" />
					<DropDownLink isVisible={loginHovered} href={"/login/employee"} label="Employee" />
				</div>
				<div className={signUpClass} onClick={()=>{setSignUpHovered(!signUpHovered)}}>
					Sign Up
					<DropDownLink isVisible={signUpHovered} href={"/signup/customer"} label="Customer" />
					<DropDownLink isVisible={signUpHovered} href={"/signup/employee"} label="Employee" />
				</div>
			</div>
		);
	} else {
		return (
			<p className="LoginGreeting top">{`Welcome, ${acct.user}!`}</p>
		);
	}


}

function DropDownLink({ isVisible, href, label }) {
	const vis = isVisible ? "DropDownLink" : "DropDownLinkHidden";

	return (
		<a href={href} className={vis}>{label}</a>
	);
}
function NavBarAndContent() {
	const [page, setPage] = useState(0);

	return (
		<div className="NavBarAndContent">
			<PageContext.Provider value={page}>
				<div className="NavBar">
					<button
						className="NavBtn"
						onClick={() => {
							setPage(0);
						}}
					>
						Welcome page
					</button>
					<button
						className="NavBtn"
						onClick={() => {
							setPage(1);
						}}
					>
						View all cars
					</button>
				</div>
				<MainContent />
			</PageContext.Provider>
		</div>
	);
}

function MainContent() {
	const page = useContext(PageContext);

	return (
		<div>
			<WelcomePage page={page} />
			<ViewCars page={page} />
		</div>
	);
}

function Menu() {
	return (
		<div>
			<LogoBar />
			<NavBarAndContent />
		</div>
	);
}

export default Menu;
