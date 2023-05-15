import React, { useEffect, useState } from "react"
import { myAxios } from "../api/axios"
import { getHeaders } from "../api/getHeaders";
import { Modal } from "./Modal"
const CART_URL = "/cart";
const PRODUCT_URL = '/products';

export const Cart = () => {
    
    const[productList,setProductList] = useState([]);
    const[isLoading,setIsLoading] = useState(true);
    const[openModal,setOpenModal] = useState(false);
    const[modalProduct, setModalProduct] = useState({});
    useEffect(() => {
        const headers = getHeaders();
        const getCart = async () => {
            let url = CART_URL + '${product.productId}';
            const response = await myAxios.get(CART_URL,{
                headers
        })
        console.log(response.data);
        setProductList(response.data.productList);
        setIsLoading(false);
        }
        getCart();

    },[]) 

    const deleteFromCart = (product) => {
        const updatedProduct = {
            ...product,
            amount : product.amount + product.cartItemsAmount,
            cartItemsAmount : 0
        };
        const headers = getHeaders();
        const deleteProductFromCart = async (product) => {
            console.log(product);
            let url = CART_URL + '/' + product.productId;
            const response = await myAxios.delete(url,{
                headers
            });
            updateProduct(product);
        }
        const updateProduct = async (product) => {
            console.log(product);
            const headers = getHeaders();
            const response = await myAxios.put(PRODUCT_URL,
                product, {
                    headers
                });

        }
        deleteProductFromCart(updatedProduct);
        deleteFromUseState(product.productId);

    }
    const deleteFromUseState = function (productId) {
        const filteredData = productList.filter(item => item.productId !== productId);
        setProductList(filteredData);
    }
    const setDetails = function(product) {
        setOpenModal(true);
        setModalProduct(product);
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
                                <th>Ilosc Produktow:</th>
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
                                            {product.cartItemsAmount}
                                        </td>
                                        <td>
                                            {product.price} zł
                                        </td>
                                        <td>
                                            <button onClick={() => setDetails(product)}>zobacz</button>
                                        </td>
                                        <td>
                                            <button onClick={() => deleteFromCart(product)}>Usun</button>
                                        </td>
                                    </tr>
                                    )
                                })
                            }
                        </tbody>
                    </table>
                    
                )
            }
            <Modal open={openModal} product={modalProduct} onClose={() => setOpenModal(false)} />
        </div>
    )
}