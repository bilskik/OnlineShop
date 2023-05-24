import axios from "axios"
import React, { useEffect, useState } from "react"
import { myAxios } from "../api/axios"
import { useNavigate } from "react-router-dom";
import  ProductList  from "./ProductList"
import { getHeaders } from "../api/getHeaders";
import "../css/Product.css"
const PRODUCT_URL = '/products';

export const Product = () => {

    const [products,setProducts] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const controller = new AbortController();
        let isMounted = true;
        const headers = getHeaders();
        const getUsers = async () => {
            try {
                const response = await myAxios.get(PRODUCT_URL, {
                    headers,
                    signal: controller.signal
                })
                setProducts(response.data);
                setIsLoading(false);
            }
            catch(err) {
                console.log(err);
            }
        }
        getUsers();
        return () => {
            isMounted = false;
            controller.abort();
        }
    },[])

    const logout = () => {
        localStorage.setItem('token', null);
        navigate('/')

    }
    const goToCart = () => {
        navigate('/cart')
    }
    const goToLoginPage = () => {
        navigate('/');
    }
    return (
        <div className="product-page">
            <header className='product-header-container'>
                <h2>Shop</h2>
                <nav className="nav-bar">
                        <img src="/img/user-interface.png" alt="login" onClick={goToLoginPage}/>
                        <img src="/img/shopping-cart.png" alt="shopping-cart" onClick={goToCart}/>
                        <img src="/img/exit.png" alt="logout" onClick={logout}/>
                </nav>
            </header>
            {
                isLoading ? (
                    <p>LOADING...</p>
                ) : (
                   <ProductList productList= {products}/>
                )
            }
        </div>
        
    )
}