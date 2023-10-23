import React, { useState, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo.png';
import { ViewCars } from './Pages'

const PageContext = createContext(1);

function LogoBar() {
  return (
    <div className="LogoBar">
      <img src={logo} alt="Car dealership logo" />
    </div>
  );
}

function NavBarAndContent() {
  const [page, setPage] = useState(1);

  return (
    <div className="NavBarAndContent">
      <PageContext.Provider value={page}>
        <div className="NavBar">
          <button
            className="NavButton"
            onClick={() => {
              setPage(0);
            }}
          >
            Welcome page
          </button>
          <button
            className="NavButton"
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
      <ViewCars page={page} />
    </div>
  );
}

function App() {
  return (
    <div>
      <LogoBar />
      <NavBarAndContent />
    </div>
  );
}

export default App;
