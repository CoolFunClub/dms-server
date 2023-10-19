import React, { useState, useEffect, useContext, createContext } from 'react';
import './Menu.css';
import logo from './assets/logo-placeholder.png';

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
    </div>
  );
}

function WelcomePage({ page }) {
  const pageClass = page == 0 ? '' : 'Hidden';

  return (
    <div className={pageClass}>
      <p>Welcome!</p>
    </div>
  );
}

function App() {
  // const [msg, setMsg] = useState('');

  // useEffect(() => {
  //   const getMsg = async () => {
  //     const data = await fetch("http://localhost:8080/cars");
  //     const msgJson = await data.json();
  //     console.log(msgJson);
  //     setMsg(msgJson[0].vin);
  //   }
  //   getMsg();
  // }, []);

  return (
    <div>
      <LogoBar />
      <NavBarAndContent />
    </div>
  );
}

export default App;
