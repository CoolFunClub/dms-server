import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom';
import './index.css';
import { LoginProvider } from './signuplogin/LoginContext';
import Menu from './Menu';
import CustomerLoginPage from './signuplogin/CustomerLogin';
import CustomerSignUpPage from './signuplogin/CustomerSignUp';
import ManagerLoginPage from './signuplogin/ManagerLogin';
import ManagerSignUpPage from './signuplogin/ManagerSignUp';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<LoginProvider>
			<BrowserRouter>
				<Routes>
					<Route exact path="/" Component={Menu} />
					<Route path="/login/customer" Component={CustomerLoginPage} />
					<Route path="/signup/customer" Component={CustomerSignUpPage} />
					<Route path="/login/manager" Component={ManagerLoginPage} />
					<Route path="/signup/manager" Component={ManagerSignUpPage} />
				</Routes>
			</BrowserRouter>
		</LoginProvider>
	</React.StrictMode>
);

function RedirectToHome() {
	const navigate = useNavigate();

	return (
		<div onLoad={()=>{navigate.push("/")}} />
	);
}
