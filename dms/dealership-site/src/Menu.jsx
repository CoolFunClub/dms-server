import React, { useState, useContext, createContext } from "react";
import "./Menu.css";
import logo from "./assets/MenuLogo.png";
import { WELCOME, VIEW_CARS, EMAIL_REP, EDIT_INV, MAKE_SALE, VIEW_CUSTOMERS, VIEW_REPS, VIEW_MANAGERS, MANAGE_CUSTOMERS } from "./pages/PageNumbers.js";
import { useLoginData } from "./signuplogin/LoginContext";
import { WelcomePage, ViewCars } from "./pages/Pages.jsx";
import { EmailRep } from "./pages/CustomerPages";
import { EditInventory, MakeSalePage } from "./pages/SalesRepPages.jsx";
import { ViewManagers, ViewCustomers, ViewSalesReps, ManageCustomers } from "./pages/ManagerPages.jsx";

const PageContext = createContext(1);

function LogoBar() {
	return (
		<div className="LogoBar">
			<img src={logo} alt="AFK Automotive's logo" />
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
					<DropDownLink isVisible={loginHovered} href={"/login/customer"} label="Customer" />
					<DropDownLink isVisible={loginHovered} href={"/login/employee"} label="Employee" />
					Login
				</div>
				<div className={signUpClass} onClick={()=>{setSignUpHovered(!signUpHovered)}}>
					<DropDownLink isVisible={signUpHovered} href={"/signup/customer"} label="Customer" />
					<DropDownLink isVisible={signUpHovered} href={"/signup/employee"} label="Employee" />
					Sign Up
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
	const vis = isVisible ? "DropDownLink" : "Hidden";

	return (
		<a href={href} className={vis}>{label}</a>
	);
}
function NavBarAndContent() {
	const { acct } = useLoginData();
	const [page, setPage] = useState(0);

	function NavBtn({ linkedPage, label }) {
		const btnClass = page === linkedPage ? "NavBtn selected" : "NavBtn";

		return (
			<button
				className={btnClass}
				onClick={()=>{setPage(linkedPage)}}
			>
				{label}
			</button>
		);
	}

	return (
		<div className="NavBarAndContent">
			<PageContext.Provider value={page}>
				<div className="NavBar">
					<NavBtn linkedPage={WELCOME} label="Home" />
					<NavBtn linkedPage={VIEW_CARS} label="View all cars" />
					{acct.type === "customer" &&
						<NavBtn linkedPage={EMAIL_REP} label="Interested in buying a car?" />
					}
					{(acct.type === "manager" || acct.type === "salesRep") &&
						<>
							<NavBtn linkedPage={VIEW_CUSTOMERS} label="View all customers" />
							<NavBtn linkedPage={EDIT_INV} label="Manage inventory" />
						</>
					}
					{acct.type === "salesRep" &&
						<NavBtn linkedPage={MAKE_SALE} label="Make a sale" />
				}
					{acct.type === "manager" &&
						<>
							<NavBtn linkedPage={VIEW_REPS} label="View all sales reps" />
							<NavBtn linkedPage={VIEW_MANAGERS} label="View all managers" />
							<NavBtn linkedPage={MANAGE_CUSTOMERS} label="Manage accounts" />
						</>
					}
				</div>
				<MainContent />
			</PageContext.Provider>
		</div>
	);
}

function MainContent() {
	const page = useContext(PageContext);

	return (
		<div className="MenuBox">
			<WelcomePage page={page} />
			<ViewCars page={page} />
			<EmailRep page={page} />
			<EditInventory page={page} />
			<ViewCustomers page={page} />
			<MakeSalePage page={page} />
			<ViewManagers page={page} />
			<ViewSalesReps page={page} />
			<ManageCustomers page={page} />
		</div>
	);
}

function Menu() {
	return (
		<div className="Menu">
			<LogoBar />
			<NavBarAndContent />
		</div>
	);
}

export default Menu;
