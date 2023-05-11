import axios from "axios"
import React, { useEffect, useState } from "react"
import { myAxios } from "./api/axios"
import { useNavigate } from "react-router-dom";
import  ProductList  from "./components/ProductList"

const PRODUCT_URL = '/products';

export const Product = () => {

    const [products,setProducts] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        const controller = new AbortController();
        let isMounted = true;
        let token = localStorage.getItem('token');
        token = "Bearer " + token;
        const headers = {
            'Authorization' : token,
            'X-User-Role' : 'CUSTOMER'
        };
        const getUsers = async () => {
            console.log(token);
            try {
                const response = await myAxios.get(PRODUCT_URL, {
                    headers ,
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
    return (
        <div>
            <article>
                <h2>SIEMA</h2>
               {
                isLoading ? (
                    <p>LOADING...</p>
                ) : (
                   <ProductList productList= {products}/>
                )
               }
            </article>
            <button onClick={logout}>
                Log Out
            </button>
            <button onClick={goToCart}>
                Koszyk
            </button>
        </div>
        
    )
}