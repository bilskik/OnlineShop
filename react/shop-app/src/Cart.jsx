import React, { useEffect, useState } from "react"
import { myAxios } from "./api/axios"

const CART_URL = "/cart";


export const Cart = () => {
    
    const[productList,setProductList] = useState([]);
    const[isLoading,setIsLoading] = useState(true);

    useEffect(() => {
        console.log("SIEMA")
        let token = localStorage.getItem('token');
        token = "Bearer " + token;
        const headers = {
            'Authorization' : token,
            'X-User-Role' : 'CUSTOMER'
        };
        const getCart = async () => {
            const response = await myAxios.get(CART_URL,{
                headers
        })
        console.log(response.data);
        setProductList(response.data.productList);
        setIsLoading(false);
        }
        getCart();

    },[]) 

    const deleteFromCart = () => {
        
    }

    return (
        <div>
            {
                isLoading ? (
                    <p>Is Loading...</p>
                ) : (
                    <table>
                        <thead>
                            <tr>
                                <th>Nazwa Produktu:</th>
                                <th>Cena Produktu:</th>
                                <th>Zobacz Szczegóły</th>
                                <th>Usun z koszyka</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                productList.map(product => {
                                    return (
                                    <tr key = {product.productId}>
                                        <td>
                                            {product.productName}
                                        </td>
                                        <td>
                                            {product.price}
                                        </td>
                                        <td>
                                            <button>zobacz</button>
                                        </td>
                                        <td>
                                            <button onClick={deleteFromCart}>Usun</button>
                                        </td>
                                    </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>
                    
                )
            }

        </div>
    )
}