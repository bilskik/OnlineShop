import React, { useEffect, useState } from "react"
import { myAxios } from "../api/axios"
import { useNavigate } from "react-router-dom";
import  ProductList  from "./ProductList"
import { getHeaders } from "../api/getHeaders";
import { PRODUCT_URL } from "../constraints/urls";
import { Loading } from "./Loading";
import "../css/product.css"
import { CART_PAGE, HOME_PAGE } from "../constraints/pages";
import { NoContent } from "./NoContent";

//No products to buy
export const Product = () => {

    const [products,setProducts] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [error,setError] = useState({
        hasError : false,
        message : ""
    });
    const navigate = useNavigate();

    useEffect(() => {
        console.log("Wersja Reacta: " + React.version);

        const controller = new AbortController();
        const headers = getHeaders();
        const getUsers = async () => {
            try {
                const response = await myAxios.get(PRODUCT_URL, {
                    headers,
                    signal: controller.signal
                }).then(response => {
                    setProducts(response.data);
                    setIsLoading(false);
                })
            }
            catch(err) {
                setError({
                    hasError : true,
                    message : err.response.data.message
                })
            }
        }
        getUsers();
        return () => {
            controller.abort();
        }
    },[])
    const updateClickedButtonsLocalStorage = (products) => {
        const productIds = products.filter(product => product.amount === 0);
        const clickedButtons = JSON.parse(localStorage.getItem('clickedButtons'));
        if(!clickedButtons) {
            return
        }
        for(const productId of productIds) {
            delete clickedButtons[productId];
        }
        localStorage.setItem('clickedButtons',JSON.stringify(clickedButtons));
    }
    const logout = () => {
        localStorage.setItem('token', null);
        localStorage.setItem('clickedButtons',null);
        navigate(HOME_PAGE)
    }
    const goToCart = () => {
        navigate(CART_PAGE)
    }
    const goToLoginPage = () => {
        navigate(HOME_PAGE);
    }
    return (
        <>
        {
            error.hasError ? (
                <NoContent message={error.message}/>
            ) : (
            isLoading ? (
                <Loading/>
            ) : (
                <div className="product-page">
                    <header className='product-header-container'>
                        <h2>Shop</h2>
                        <nav className="nav-bar">
                                <img src="/img/user-interface.png" alt="login" onClick={goToLoginPage}/>
                                <img src="/img/shopping-cart.png" alt="shopping-cart" onClick={goToCart}/>
                                <img src="/img/exit.png" alt="logout" onClick={logout}/>
                        </nav>
                    </header>
                    <ProductList productList= {products}/>
                </div>
            )
        )

        }

        </>
        
    )
}