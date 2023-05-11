import React from 'react';
import '../index.css'
import { myAxios } from '../api/axios';
import { useState } from "react"
const CART_URL = '/cart';

export const ProductList = (props) => {
    const productList = props.productList;
    const [buttonDisabled,setButtonDisabled] = useState(null);

    const addToCart = async (product) => {
        setButtonDisabled(product.productId);
        let token = localStorage.getItem('token');
        token = "Bearer " + token;
        const headers = {
            'Authorization' : token,
            'X-User-Role' : "CUSTOMER",
            'Content-Type' : 'application/json'
        }
        try {
            const response = await myAxios.post(CART_URL,
                { productId : product.productId },
                {
                    headers,
                    withCredentials : true
                }
            )
        
        }
        catch(err) {
            
        }
    }
    const seeDetails = () => {

    }
    return (
        <table>
            <thead>
                <tr>
                    <th>Nazwa Produktu:</th>
                    <th>Cena produktu:</th>
                    <th>Zobacz szczegóły:</th>
                    <th>Dodaj do koszyka:</th>
                </tr>
            </thead>
            <tbody>
                {productList.map(product => {
                        return(
                            <tr key = {product.productId}>                  
                                <td>
                                    {product.productId} + {product.productName}
                                </td>
                                <td>
                                    {product.price} zł
                                </td>
                                <td>
                                    {/* {product.productDetails.details} */}
                                    <button onClick={seeDetails}>Zobacz</button>
                                </td>
                                <td>
                                    <button onClick={() => addToCart(product)}>Dodaj</button>
                                </td>
                            </tr>
                        )
                    })
                }
            </tbody>
        </table>
    )

}

export default ProductList