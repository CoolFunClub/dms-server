import React, { useState, useEffect } from 'react';
import './Menu.css';

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
        <p>
          {msg}
        </p>
      </header>
    </div>
  );
}

export default App;
