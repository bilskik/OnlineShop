import  React,{ useState } from "react";
import { Login } from './components/Login.jsx'
import { Register } from './components/Register.jsx'
import { useRoutes } from "react-router-dom";
import { Product } from "./components/Product.jsx"
import { Cart } from "./components/Cart.jsx"
import { Order } from "./components/Order.jsx"
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
    },
    {
      path : '/order/:data',
      element : <Order/>
    }
  ]);
  return (
    <div className="main">
        {routes}
    </div>
  );
}

export default App;
