import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';
import Menu from './Menu';
import MainForm from './LogIn';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route exact path="/" Component={Menu} />
        <Route path="/login" Component={MainForm} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

