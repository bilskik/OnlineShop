import React, { useEffect } from 'react';
import '../index.css'
import Modal from './Modal';
import { myAxios } from '../api/axios';
import { useState } from "react"
import '../css/Modal.css'
import { getHeaders } from '../api/getHeaders';

const CART_URL = '/cart';
const PRODUCT_URL = '/products';

export const ProductList = (props) => {
    const [productList,setProductList] = useState(props.productList);
    const [clickedButtons, setClickedButtons] = useState({});
    const [openModal, setOpenModal] = useState(false);
    const [modalPoduct, setModalProduct] = useState({});

    const addToCart = async (product) => {
        const headers = getHeaders();
        try {
            console.log("przed response");
            console.log(product);
            const response = await myAxios.post(CART_URL,
                { productId : product.productId },
                {
                    headers,
                    withCredentials : true
                }
            )
            checkIfDisable(product);

        }
        catch(err) {
            
        }
    }
    const disableButton = function(productId) {
        setClickedButtons({...clickedButtons, [productId] : true})
    }
    const checkIfDisable = function(product) {
        const productId = product.productId;
        const productIndex = productList.findIndex(product => product.productId === productId);

        if(productId >= 0) {
            let updatedProductList = [...productList];
            let productUpdated = updatedProductList[productIndex];
            productUpdated.amount--;
            productUpdated.cartItemsAmount++;
            updatedProductList[productIndex] = productUpdated;
            setProductList(updatedProductList);
            updateProduct(updatedProductList[productIndex]);
            if(productUpdated.amount === 0) {
                disableButton(productId);
            }

        } 
    }
    const setDetails = (product) => {
        setModalProduct(product);
        setOpenModal(true);
    }
    const updateProduct = async (product) => {
        console.log("PRODUCTTT");
        console.log(product);
        const headers = getHeaders();
        console.log(headers.Authorization);
        try {
            const response = await myAxios.put(PRODUCT_URL,
                product, 
                {
                headers,
            })
        }
        catch(err) {
            console.log(err);
        }
    }
    return (
        <div className='table'>
            {/* <table>
                <thead>
                    <tr>
                        <th>Nazwa Produktu:</th>
                        <th>Cena produktu:</th>
                        <th>Ilość produktu</th>
                        <th>Zobacz szczegóły:</th>
                        <th>Dodaj do koszyka:</th>
                    </tr>
                </thead>
                <tbody>
                    {productList.map(product => {
                            return(
                                <tr key = {product.productId}>                  
                                    <td>
                                        {product.productName}
                                    </td>
                                    <td>
                                        {product.price} zł
                                    </td>
                                    <td>
                                        {product.amount}
                                    </td>
                                    <td>
                                        {product.productDetails.details}
                                        <button onClick={() => setDetails(product)}>Zobacz</button>
                                    </td>
                                    <td>
                                        <button onClick={() => addToCart(product)} disabled={clickedButtons[product.productId]}>Dodaj</button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            
            </table> */}
            <div className='grid-container'>
                {productList.map((product) => {
                    return (
                    <div className="wrapper">
                        <div className="image">
                            <img src={product.image} alt="image" />
                        </div>
                        <div className="rest">
                            <div className="p-container">
                                <p className="price">{product.price} zł</p>
                                <p>{product.productName}</p>
                            </div>
                            <div className='btn-container'>
                                <button onClick={() => setDetails(product)}>Zobacz</button>
                                <button onClick={() => addToCart(product)} disabled={clickedButtons[product.productId]}>Dodaj</button>
                            </div>
                        </div>
                    </div>
                    )
                })}
            </div>
            <Modal open={openModal} product={modalPoduct} onClose={() => setOpenModal(false)}/>
         </div>
    )

}

export default ProductList