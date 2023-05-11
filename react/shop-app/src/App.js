import  React,{ useState } from "react";
import { Login } from './Login.jsx'
import { Register } from './Register.jsx'
import { useRoutes } from "react-router-dom";
import { Product } from "./Product.jsx"
import { Cart } from "./Cart.jsx"
function App() {
  const [currentForm, setCurrentForm] = useState("login");

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }
  const routes = useRoutes([
    {
      path: '/',
      element: currentForm === "login" ? <Login onFormSwitch={toggleForm}/> : <Register onFormSwitch={toggleForm}/>
    },
    {
      path: '/products',
      element: <Product/>
    },
    {
      path : '/cart',
      element: <Cart/>
    }
  ]);
  return (
    <div className="main">
        {routes}
    </div>
  );
}

export default App;
