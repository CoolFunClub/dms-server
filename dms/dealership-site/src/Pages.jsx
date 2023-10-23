import React, { useEffect, useState } from 'react';
import './Pages.css';

export function ViewCars({ page }) {
  const pageClass = page === 1 ? '' : 'Hidden';
  const [carList, setCarList] = useState([]);

  useEffect(() => {
    const getMsg = async () => {
      const data = await fetch("http://localhost:8080/cars");
      const msgJson = await data.json();
      console.log(msgJson);
      setCarList(msgJson);
    }
    getMsg();
  }, []);

  const results = [];

  if (carList.length > 0) {
    for (const car of carList) {
      results.push(
        <div className="CarCard">
          <b>{`${car.carYear} ${car.manufacturer} ${car.model}`}</b>
          <InfoCell header={"VIN:"} value={`${car.vin}`} />
          <InfoCell header={"Status:"} value={`${car.status}`} />
          <InfoCell header={"Mileage:"} value={`${car.mileage}`} />
          <InfoCell header={"Price:"} value={`${car.price}`} />
          <InfoCell header={"Color:"} value={`${car.color}`} />
          <InfoCell header={"Trim:"} value={`${car.trim}`} />
        </div>
      );
    }
  } else {
    results.push(<p>"No cars found!"</p>);
  }

  return (
    <div className={pageClass}>
      {results}
    </div>
  );
}

function InfoCell({header, value}) {
  return (
    <div className="InfoCell">
      <b>{header}</b>&nbsp;
      {value}
    </div>
  );
}