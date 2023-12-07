import React, { useEffect, useState } from "react";
import "./Pages.css";
import { WELCOME, VIEW_CARS } from "./PageNumbers";
import welcomeCar from "../assets/welcome-car.jpg";
import redCar from "../assets/car-placeholder-red.jpeg";


export function InfoCell({id, label, value}) {
	return (
		<div className="FormCols">
			<label htmlFor={id}>
				<b>{label}</b>
			</label>
			<p id={id}>{value}</p>
		</div>
	);
}

export function InputCell({id, label, value}) {
	return (
		<div className="FormCols">
			<label htmlFor={id}>
				<b>{label}</b>
			</label>
			<input id={id} defaultValue={value} />
		</div>
	);
}

export function WelcomePage({ page }) {
	const pageClass = page === WELCOME ? "Welcome Page" : "Hidden";

	return (
		<div className={pageClass}>
			Welcome!
			<img
				className="center"
				src={welcomeCar}
				alt="Clipart of a red sports car with a teal background"
			/>
		</div>
	);
}

export function ViewCars({ page }) {
  const pageClass = page === VIEW_CARS ? "Page" : "Hidden";
  const [carList, setCarList] = useState([]);

	// only causes a CORS error when testing locally
  useEffect(() => {
    const getMsg = async () => {
      const data = await fetch("https://www.afkauto.com/api/cars", {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });

			const msgJson = await data.json();
      setCarList(msgJson);
    }
    getMsg();
  }, []);

  const results = [];

  if (carList.length > 0) {
    for (const car of carList) {
      results.push(
        <div key={car.vin} >
          <div className="CarPic">
            <img src={redCar} alt="A red 2020 Honda Accord" />
          </div>
          <div className="Car Card">
            <b>{`${car.carYear} ${car.manufacturer} ${car.model}`}</b>
            <InfoCell label={"VIN:"} value={`${car.vin}`} />
            <InfoCell label={"Status:"} value={`${car.status}`} />
            <InfoCell label={"Mileage:"} value={`${car.mileage}`} />
            <InfoCell label={"Price:"} value={`${car.price}`} />
            <InfoCell label={"Color:"} value={`${car.color}`} />
            <InfoCell label={"Trim:"} value={`${car.trim}`} />
          </div>
        </div>
      );
    }
  } else {
    results.push(<p key="no-cars" >No cars found!</p>);
  }

  return (
    <div className={pageClass}>
      {results}
    </div>
  );
}
