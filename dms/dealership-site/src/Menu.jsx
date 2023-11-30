import React, { useState, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo.png';
import { WelcomePage, ViewCars } from './Pages'

const PageContext = createContext(1);

function LogoBar() {
  return (
    <div className="LogoBar">
      <img src={logo} alt="Car dealership logo" />
			<div className="AcctBtns">
				<a href="/login">
					<div className="Login Button">
						Login
					</div>
				</a>
				<a href="/signup">
					<div className="SignUp Button">
						Sign up
					</div>
				</a>
			</div>
    </div>
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
