import React from 'react';
import { useNavigate } from 'react-router-dom';
import Register from './pages/Register/Register';
import './App.scss';

function App() {
  const navigate = useNavigate();

  return (
    <div className="main">
      <Register />
    </div>
  );
}

export default App;
