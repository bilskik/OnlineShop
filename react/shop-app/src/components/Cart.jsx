import React, { useEffect, useState } from "react"
import { myAxios } from "../api/axios"
import { getHeaders } from "../api/getHeaders";
import { Modal } from "./Modal"
import { useNavigate } from "react-router-dom";
const CART_URL = "/cart";
const PRODUCT_URL = '/products';
const ORDER_URL = '/orders';

export const Cart = () => {
    
    const [productList,setProductList] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [openModal,setOpenModal] = useState(false);
    const [modalProduct, setModalProduct] = useState({});
    const [sum,setSum] = useState(0);
    const [cart,setCart] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const headers = getHeaders();
        const getCart = async () => {
            let url = CART_URL + '${product.productId}';
            const response = await myAxios.get(CART_URL,{
                headers
            })
            .then(response => {
                setProductList(response.data.productList);
                setCart(response.data);
                setIsLoading(false);
                countInitialSum(response.data.productList);
            }) 
        }
        getCart();

    },[]) 
    const countInitialSum = function(productList) {
        let currSum = 0;
        productList.forEach(product => {
            currSum += product.price*product.cartItemsAmount;
        })
        setSum(currSum);
    }
    const countDeletionSum = function(product) {
        let currSum = sum;
        currSum -= product.cartItemsAmount * product.price;
        setSum(currSum);
    }

    const deleteFromCart = (product) => {
        const updatedProduct = {
            ...product,
            amount : product.amount + product.cartItemsAmount,
            cartItemsAmount : 0
        };
        const headers = getHeaders();
        const deleteProductFromCart = async (product) => {
            let url = CART_URL + '/' + product.productId;
            const response = await myAxios.delete(url,{
                headers
            })
            updateProduct(product);
        }
        const updateProduct = async (product) => {
            const headers = getHeaders();
            const response = await myAxios.put(PRODUCT_URL,
                product, {
                    headers
                });

        }
        countDeletionSum(product);
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
    const goToOrder = () => {
        // const date = getDate();
        // const order = {
        //     total : sum,
        //     cart : cart,
        //     orderDate : date
        // }
        // console.log(order);
        // const headers = getHeaders();
        // const createOrder = async () => {
        //     const response = await myAxios.post(ORDER_URL,
        //         order, {headers}
        //     )
        // }
        // createOrder()
        const data = encodeURIComponent(JSON.stringify(productList));
        console.log(data);
        navigate(`/order/${data}`);
    }
    // const getDate = () => {
    //     let currDate = new Date();
    //     const year = currDate.getFullYear();
    //     const month = (currDate.getMonth() + 1).toString().padStart(2,'0');
    //     const day = currDate.getDay().toString().padStart(2,'0');
    //     return day + "-" + month + "-" + year;
    // }
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
            <p>Suma: {sum}</p>
            <button onClick={goToOrder}>Przejdz do platnosci</button>
        </div>
    )
}