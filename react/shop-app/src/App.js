import logo from './logo.svg';
import React, { useState } from "react";
import { Login } from './Login.jsx'
import { Register } from './Register.jsx'
function App() {
  const [currentForm, setCurrentForm] = useState("login");

  return (

    <div className="App">
      {
        currentForm === "login" ? <Login/> : <Register/>
      }
    </div>
  );
}

export default App;
