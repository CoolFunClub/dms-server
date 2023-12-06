import React, { useState, useContext, createContext } from "react";
import "./Menu.css";
import logo from "./assets/logo.png";
import { WELCOME, VIEW_CARS, MAKE_SALE, VIEW_CUSTOMERS, VIEW_REPS, VIEW_MANAGERS, MANAGE_CUSTOMERS } from "./pages/PageNumbers.js";
import { useLoginData } from "./signuplogin/LoginContext";
import { WelcomePage } from "./pages/Pages.jsx";
import { ViewCars } from "./pages/CustomerPages";
import { MakeSalePage } from "./pages/SalesRepPages.jsx";
import { ViewManagers, ViewCustomers, ViewSalesReps, ManageCustomers } from "./pages/ManagerPages.jsx";

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
	const { acct } = useLoginData();
	const [page, setPage] = useState(0);

	return (
		<div className="NavBarAndContent">
			<PageContext.Provider value={page}>
				<div className="NavBar">
					<button
						className="NavBtn"
						onClick={()=>{setPage(WELCOME)}}
					>
						Welcome page
					</button>
					<button
						className="NavBtn"
						onClick={()=>{setPage(VIEW_CARS)}}
					>
						View all cars
					</button>
					{(acct.type === "manager" || acct.type === "salesRep") &&
						<button
							className="NavBtn"
							onClick={()=>{setPage(VIEW_CUSTOMERS)}}
						>
							View all customers
						</button>
					}
					{acct.type === "salesRep" &&
						<button
							className="NavBtn"
							onClick={()=>{setPage(MAKE_SALE)}}
						>
							Make a sale
						</button>
				}
					{acct.type === "manager" &&
						<>
							<button
								className="NavBtn"
								onClick={()=>{setPage(VIEW_REPS)}}
							>
								View all sales reps
							</button>
							<button
								className="NavBtn"
								onClick={()=>{setPage(VIEW_MANAGERS)}}
							>
								View all managers
							</button>
							<button
								className="NavBtn"
								onClick={()=>{setPage(MANAGE_CUSTOMERS)}}
							>
								Manage customers
							</button>
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
	const { acct } = useLoginData();

	return (
		<div className="MenuBox">
			<WelcomePage page={page} />
			<ViewCars page={page} />
			{(acct.type === "manager" || acct.type === "salesRep") &&
				<ViewCustomers page={page} />
			}
			{acct.type === "salesRep" &&
				<MakeSalePage page={page} />
			}
			{acct.type === "manager" &&
				<>
					<ViewManagers page={page} />
					<ViewSalesReps page={page} />
					<ManageCustomers page={page} />
				</>
			}
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
