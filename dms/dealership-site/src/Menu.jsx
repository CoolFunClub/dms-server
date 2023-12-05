import React, { useState, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo.png';
import { WelcomePage, ViewCars } from './Pages'
import { useLoginData } from './signuplogin/LoginContext';

const PageContext = createContext(1);

function LogoBar() {
	const [signUpHovered, setSignUpHovered] = useState(false);
	const [loginHovered, setLoginHovered] = useState(false);
  const { acct, updateAcct } = useLoginData();

  console.log(`*** Currently logged in: ${acct.type} ${acct.user} with DL#/SSN ${acct.id}`);
	const signUpClass = signUpHovered ? "SignUp Button" : "SignUp Button Collapsed";
	const loginClass = loginHovered ? "Login Button" : "Login Button Collapsed";

  return (
    <div className="LogoBar">
      <img src={logo} alt="Car dealership logo" />
			<div className="AcctBtns">
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
    </div>
  );
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
