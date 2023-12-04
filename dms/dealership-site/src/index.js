import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';
import Menu from './Menu';
import LoginPage from './LogIn';
import SignUpPage from './SignUp';
import { LoginProvider } from './LoginContext';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<LoginProvider>
			<BrowserRouter>
				<Routes>
					<Route exact path="/" Component={Menu} />
					<Route path="/login" Component={LoginPage} />
					<Route path="/signup" Component={SignUpPage} />
				</Routes>
			</BrowserRouter>
		</LoginProvider>
	</React.StrictMode>
);

