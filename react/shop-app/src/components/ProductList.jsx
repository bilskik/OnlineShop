import React from 'react';
import '../index.css'
import Modal from './Modal';
import { myAxios } from '../api/axios';
import { useState } from "react"
import '../css/Modal.css'
const CART_URL = '/cart';

export const ProductList = (props) => {
    const productList = props.productList;
    const [clickedButtons, setClickedButtons] = useState({});
    const [openModal, setOpenModal] = useState(false);
    const [modalPoduct, setModalProduct] = useState({});

    const addToCart = async (product) => {
        disableButton(product.productId);
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
    const setDetails = (id) => {
        const product = productList.filter(product => product.productId === id)
        console.log(product[0]);
        setModalProduct(product[0]);
        setOpenModal(true);
    }
    const disableButton = function(productId) {
        setClickedButtons({...clickedButtons, [productId] : true})
    }
    return (
        <div className='table'>
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
                                        <button onClick={() => setDetails(product.productId)}>Zobacz</button>
                                    </td>
                                    <td>
                                        <button onClick={() => addToCart(product)} disabled={clickedButtons[product.productId]}>Dodaj</button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            
            </table>
            <Modal open={openModal} product={modalPoduct} onClose={() => setOpenModal(false)}/>
         </div>
    )

}

export default ProductList