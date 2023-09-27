import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [msg, setMsg] = useState('');

  useEffect(() => {
    const getMsg = async () => {
      const data = await fetch("http://localhost:8080/cars");
      const msgJson = await data.json();
      console.log(msgJson);
      setMsg(msgJson[0].vin);
    }
    getMsg();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {msg}
        </p>
      </header>
    </div>
  );
}

export default App;
