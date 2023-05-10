import axios from "axios"
import React, { useEffect, useState } from "react"
import { myAxios } from "./api/axios"

const PRODUCT_URL = '/products';

export const Product = () => {

    const [products,setProducts] = useState({});

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
            }
            catch(err) {
                console.log(err);
            }
        }
        getUsers();
        console.log(products);
        return () => {
            isMounted = false;
            controller.abort();
        }
    },[])
    return (
        <article>
            <h2>Products:</h2>

        </article>
    )
}