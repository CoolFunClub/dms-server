import React, { useState, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo.png';
import { WelcomePage, ViewCars } from './Pages'
import { useLoginData } from './signuplogin/LoginContext';

const PageContext = createContext(1);

function LogoBar() {
	const [hovered, setHovered] = useState(false);
  const { acct, updateAcct } = useLoginData();

  console.log(`*** Currently logged in: ${acct.type} ${acct.user} with DL#/SSN ${acct.id}`);
	const signUpBtnClass = hovered ? "SignUpBtn" : "SignUpBtn Collapsed";

  return (
    <div className="LogoBar">
      <img src={logo} alt="Car dealership logo" />
			<div className="AcctBtns">
				<a href="/login">
					<div className="Login Button">
						Login
					</div>
				</a>
					<div className={signUpBtnClass} onClick={()=>{setHovered(!hovered)}}>
						Sign Up
						<DropDownLink isVisible={hovered} href={"/signup/customer"} label="Customer" />
						<DropDownLink isVisible={hovered} href={"/signup/manager"} label="Manager" />
						<DropDownLink isVisible={hovered} href={"/"} label="Salesperson" />
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
