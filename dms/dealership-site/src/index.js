import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';
import { LoginProvider } from './signuplogin/LoginContext';
import Menu from './Menu';
import CustomerLoginPage from './signuplogin/CustomerLogin';
import CustomerSignUpPage from './signuplogin/CustomerSignUp';
import EmployeeLoginPage from './signuplogin/EmployeeLogin';
import EmployeeSignUpPage from './signuplogin/EmployeeSignUp';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<LoginProvider>
			<BrowserRouter>
				<Routes>
					<Route exact path="/" Component={Menu} />
					<Route path="/login/customer" Component={CustomerLoginPage} />
					<Route path="/signup/customer" Component={CustomerSignUpPage} />
					<Route path="/login/employee" Component={EmployeeLoginPage} />
					<Route path="/signup/employee" Component={EmployeeSignUpPage} />
				</Routes>
			</BrowserRouter>
		</LoginProvider>
	</React.StrictMode>
);
