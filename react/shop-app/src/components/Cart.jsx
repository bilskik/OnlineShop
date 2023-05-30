import React, { useEffect, useState } from "react"
import { myAxios } from "../api/axios"
import { getHeaders } from "../api/getHeaders";
import { Modal } from "./Modal"
import { useNavigate } from "react-router-dom";
import { CART_URL,PRODUCT_URL } from "../constraints/urls";
import "../css/cart.css";
import { ORDER_PAGE, HOME_PAGE } from "../constraints/pages";
import { Loading } from "./Loading";
import { NoContent } from "./NoContent";

export const Cart = () => {
    
    const [productList,setProductList] = useState([]);
    const [isLoading,setIsLoading] = useState(true);
    const [openModal,setOpenModal] = useState(false);
    const [modalProduct, setModalProduct] = useState({});
    const [sum,setSum] = useState(0);
    const [isCartEmpty,setIsCartEmpty] = useState(false);
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
                checkIfProductListIsEmpty(response.data.productList);
                setProductList(response.data.productList);
                setCart(response.data);
                countInitialSum(response.data.productList);
                setIsLoading(false);
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
        checkIfAmountZero(product);
    }
    const checkIfAmountZero = function(updatedProduct) {
        if(updatedProduct.amount === 0) {
            const productId = updatedProduct.productId;
            let savedDisabledButtonObject = localStorage.getItem('clickedButtons');
            const parsedDisabledButtonObject = JSON.parse(savedDisabledButtonObject);
            delete parsedDisabledButtonObject[productId.toString()];
            savedDisabledButtonObject = JSON.stringify(parsedDisabledButtonObject);
            localStorage.setItem('clickedButtons',savedDisabledButtonObject);
        }

    }
    const deleteFromUseState = function (productId) {
        const filteredData = productList.filter(item => item.productId !== productId);
        checkIfProductListIsEmpty(filteredData);
        setProductList(filteredData);
    }
    const setDetails = function(product) {
        setOpenModal(true);
        setModalProduct(product);
    }
    const goToOrder = () => {
        const data = encodeURIComponent(JSON.stringify(productList));
        navigate(ORDER_PAGE + `/${data}`);
    }
    const checkIfProductListIsEmpty = (productList) => {
        if(productList.length === 0) {
            setIsCartEmpty(true);
        }
        else {
            setIsCartEmpty(false);
        }
    }
    const logout = () => {
        localStorage.setItem('token', null);
        localStorage.setItem('clickedButtons',null);
        navigate(HOME_PAGE)
    }
    return (
        <div>
            {
                isLoading ? (
                    <Loading/>
                ) : (
                    !isCartEmpty ? (
                        <>
                            <header className="product-header-container-cart">
                                <h2>Cart</h2>
                                <nav className="nav-bar">
                                    <img src="/img/exit.png" alt="logout" onClick={logout}/>
                                </nav>
                            </header>
                            <div className="main-grid-cart">
                                <div className='grid-container-cart'>
                                    {productList.map((product) => {
                                        return (
                                        <div className="wrapper">
                                            <div className="image">
                                                <img src={product.image} alt="product-image"/>
                                            </div>
                                            <div className="rest">
                                                <div className="p-container">
                                                    <p className="price">{product.price} zł</p>
                                                    <p>{product.productName}</p>
                                                    <p>Amount: {product.cartItemsAmount}</p>
                                                </div>
                                                <div className="btn-container">
                                                    <button onClick={() => setDetails(product)}>See details</button>
                                                    <button onClick={() => deleteFromCart(product)}>Remove</button>
                                                </div>
                                            </div>
                                        </div>
                                        )
                                    })}
                                </div>
                                <div className="summary">
                                        <p>Suma: {sum} zł</p>
                                        <button onClick={goToOrder}>Go to payment</button>
                                </div>
                            </div>
                            <Modal open={openModal} product={modalProduct} showAvailabilty={false} onClose={() => setOpenModal(false)} />
                      </>
                    ) : (
                        <>
                            <NoContent message={"Your cart is empty!"}/>
                        </>
                    )
                    
                )
            }
            
        </div>
    )
}