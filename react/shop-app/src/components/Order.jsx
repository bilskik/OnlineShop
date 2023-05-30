import React, { useEffect, useState } from 'react';
import { myAxios } from '../api/axios';
import { getHeaders } from '../api/getHeaders';
import {useNavigate, useParams } from 'react-router-dom';
import { ProductCard } from "./ProductCard"
import { ORDER_URL, PRODUCT_URL } from '../constraints/urls';
import "../css/order.css";
import {Modal} from "./Modal";
import { ShippmentDetails } from './ShippmentDetails';
import { HOME_PAGE, PRODUCT_PAGE } from '../constraints/pages';
import { Alert } from './Alert';
import { Loading } from './Loading';
import { NoContent } from './NoContent';
export const Order = () => {
    const [productList,setProductList] = useState();
    const [isLoading,setIsLoading] = useState(true);
    const [price,setPrice] = useState(0);
    const { data } = useParams();
    const [country,setCountry] = useState('');
    const [city,setCity] = useState('')
    const [street,setStreet] = useState('');
    const [code,setCode] = useState('');
    const [orderState,setOrderState] = useState(false);
    const [mappedAddress, setMappedAddress] = useState({});
    const [reloadPage,setReloadPage] = useState(false);
    const [order,setOrder] = useState();
    const [isOrderAvailable,setIsOrderAvailable] = useState(false);
    const [isAddressEdited,setIsAddressEdited] = useState(false);
    const [paymentMethod,setPaymentMethod] = useState("Karta Płatnicza");
    const [openModal,setOpenModal] = useState(false);
    const [modalProduct, setModalProduct] = useState();

    const [error, setError] = useState({
        hasError : false,
        message : ''
    });

    const navigate = useNavigate();
    useEffect(() => {
        
        const getOrder = async () => {
            const headers = getHeaders();
            const response = await myAxios.get(ORDER_URL,
                {headers}
            ).then(response => {
                if(response.data) {
                    setOrder(response.data);
                    setProductList(response.data.productList);
                    setIsLoading(false);
                    setOrderState(true);
                    countPrice(response.data.productList);
                    mapNamesInAddress(response.data.address);
                    checkIfProdutListIsEmpty(response.data.productList);
                    setPaymentMethod(response.data.paymentType)
                }
                else {
                    loadDataFromURL();
                }
            })
           
        }
        getOrder();
    },[data,reloadPage])

    const loadDataFromURL = function(){
        const loadedData = JSON.parse(decodeURIComponent(data));
        setProductList(loadedData);
        setIsLoading(false);
        countPrice(loadedData);
        checkIfProdutListIsEmpty(loadedData);
    }
    const countPrice = (productList) => {
        let sum = 0;
        productList.forEach(product => {
            sum += product.cartItemsAmount*product.price;
        })
        setPrice(sum);
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        const date = getDate();
        const address = getAddress();
        if(address !== null) {
            const order = {
                total : price,
                orderDate : date,
                address : address,
                paymentType : paymentMethod
            }
            const isValid = isValidForm(order);
            const headers = getHeaders();
            const postData = async () => {
                const response = await myAxios.post(ORDER_URL,
                    order
                ,{
                    headers
                }).then(response => {
                    reloadPageFunction()
                })
            }
            postData();
            setAddressValueToEmpty();
        }
    }
    const setAddressValueToEmpty = () => {
        setCountry('');
        setCity('');
        setCode('');
        setStreet('');
    }
    const isValidForm = (order) => {
        const isValid = Object.values(order).every(value => value != '');
        return isValid;
    }
    const getDate = () => {
        let currDate = new Date();
        const year = currDate.getFullYear();
        const month = (currDate.getMonth() + 1).toString().padStart(2,'0');
        const day = currDate.getDate().toString().padStart(2,'0');
        return day + "-" + month + "-" + year;
    }

    const mapNamesInAddress = function(address) {
        const updatedAddress = {
            "Państwo" : address.country,
            "Miasto" : address.city,
            "Ulica" : address.street,
            "Kod Pocztowy" : address.zipCode
        };
        setMappedAddress(updatedAddress);
    }
    const deleteOrder = () => {
        const deleteOrder = async () => {
            const headers = getHeaders();
            const response = await myAxios.delete(ORDER_URL,
                order,
                {headers}
            ).then(response => {
                navigate(PRODUCT_PAGE);
            })
        }
    }
    const editAddress = () => {
        setOrderState(false);
        setIsAddressEdited(true);
    }

        const checkIfProdutListIsEmpty = (productList) => {
        if(productList.length === 0) {
            setIsOrderAvailable(false);
        }
        else {
            setIsOrderAvailable(true);
        }
    }
    const addressError = (addressType) => {
        if(!country) {
            setError({
                hasError: true,
                message: "Country cannot be empty!"
            })
            return true;
        }
        else if(!city) {
            setError({
                hasError: true,
                message: "City cannot be empty!"
            })
            return true;
        }
        else if(!street) {
            setError({
                hasError: true,
                message: "Street cannot be empty!"
            })
            return true;
        }
        else if(!code) {
            setError({
                hasError: true,
                message: "Code cannot be empty!"
            })
            return true;
        }
        else {
            return false;
        }
    }
    const getAddress = () => {
        let error = addressError();
        return error ? 
        null : {
            "country" : country,
            "city": city,
            "street" : street,
            "zipCode" : code
        } 
    }
    const handleAddressSubmit = (e) => {
        e.preventDefault();
        const address = getAddress();
        if(address !== null) {
            const order = {
                address,
                paymentType : paymentMethod
            }
            const headers = getHeaders();
            const updateAddress = async () => {
                const response = await myAxios.put(ORDER_URL,
                    order,
                    {headers}
                ).then(response => {
                    reloadPageFunction()
                    setOrderState(true);
                })
            }
            updateAddress();
        } 
    }
    const reloadPageFunction = function() {
        if(reloadPage) {
            setReloadPage(false)
        }
        else {
            setReloadPage(true);
        }
    }
    const deleteProductList = () => {
        performOperationOnProductList();
    }
    const performOperationOnProductList = async function() {
        let productListWithAmountZero = productList.filter(product => product.amount === 0);
        let productListWithAmountDifferThanZero = productList.filter(product => product.amount !== 0);

        if(productListWithAmountZero.length === productList.length) { //delete logic
            const productIdsListToDelete = mapToProductIdsList(productListWithAmountZero);
            deleteProductIdsFromDB(productIdsListToDelete)
        }
        else if(productListWithAmountZero.length !== 0) { //both logic
            const productIdsListToDelete = mapToProductIdsList(productListWithAmountZero);
            deleteProductIdsFromDB(productIdsListToDelete)
            updateProductInDB(productListWithAmountDifferThanZero);
        }
        else {   //update logic
            updateProductInDB(productListWithAmountDifferThanZero);
        }
    }
    const mapToProductIdsList = (productList) => {
        const productIds = productList.map(product => product.productId); 
        return productIds;
    }

    
    const deleteProductIdsFromDB = async(productIdsList) => {
        const headers = getHeaders();
        const deleteHttpMethod = async(productIdsList,headers) => {
            updateClickedButtonsLocalStorage(productIdsList);
            const response = await myAxios.delete(PRODUCT_URL,    {
                data: productIdsList,
                headers: headers
              }
            ).then(() => {
                navigate(PRODUCT_PAGE);
            })
        }
        deleteHttpMethod(productIdsList,headers);

    }  

    const updateProductInDB = async(productList) => {
        const updateEachProduct = async (product,headers) => {
            const deleteProduct = await myAxios.put(PRODUCT_URL, product,
                {headers}
            ).then(() => {
                navigate(PRODUCT_PAGE);
            })
        }
        const headers = getHeaders();
        for(const key in productList) {
            const product = productList[key];
            product.cartItemsAmount = 0;
            await updateEachProduct(product,headers);
        }

    }
    const setDetails = (product) => {
        setModalProduct(product);
        setOpenModal(true);
    }
    const updateClickedButtonsLocalStorage = (productIds) => {
        const clickedButtons = JSON.parse(localStorage.getItem('clickedButtons'));
        for(const productId of productIds) {
            delete clickedButtons[productId];
        }
        localStorage.setItem('clickedButtons',JSON.stringify(clickedButtons));
    }
    const handleErrorDisplaying = () => {
        setError({
            ...error,
            hasError: false
        });
    }
    useEffect(() => {
        if(error.hasError) {
            setTimeout(() => {
                setError({
                    ...error,
                    hasError: false
                });
            },5000)
        }
    },[error.hasError]);
    const logout = () => {
        localStorage.setItem('token', null);
        localStorage.setItem('clickedButtons', null);
        navigate(HOME_PAGE)
    }
    return (
        <div className='product-page'>
            {
                error.hasError && (
                    <Alert defaultValue="show" sendDataToParent={handleErrorDisplaying} data={error.message}/>
                )
            }
            {
                isLoading ? ( 
                    <Loading/>
                ) : (
                    <>
                    { isOrderAvailable ? (

                        <>
                            <header className='product-header-container-cart'>
                                <h2>Order</h2>
                                <nav className='nav-bar'>
                                    <img src="/img/exit.png" alt="logout" onClick={logout}/>
                                </nav>
                            </header>
                            { !orderState ? (
                            <>
                                <div className="main-grid-order">
                                    <ProductCard productList={productList} setDetails={setDetails}/>
                                    <div className="second-column-order">
                                        <p className='to-pay'>To pay: {price} zł</p>
                                        <form onSubmit={!isAddressEdited ? handleSubmit : handleAddressSubmit} className='form-container-order'>
                                            <label htmlFor='county'>Country</label>
                                            <input id='country' name='country' placeholder='country' onChange={(e) => setCountry(e.target.value)}/>
                                            <label htmlFor='city'>City</label>
                                            <input id='city' name='city' placeholder='city' onChange={(e) => setCity(e.target.value)}/>
                                            <label htmlFor=''>Street</label>
                                            <input id='street' name='street' placeholder='street' onChange={(e) => setStreet(e.target.value)}/>
                                            <label htmlFor='zip-code'>Zip Code</label>
                                            <input id='zip-code' name='zip-code' placeholder='zip-code' onChange={(e) => setCode(e.target.value)}/>
                                            <label>Payment method:</label>
                                            <select value={paymentMethod} onChange={(e) => {setPaymentMethod(e.target.value)}}>
                                                <option value="Karta Płatnicza">Debit card</option>
                                                <option value="Google Pay">Google Pay</option>
                                                <option value="Apple Pay">Apple Pay</option>
                                                <option value="BLIK">BLIK</option>
                                                <option value="Przelew">Bank transfer</option>
                                            </select>
                                            <button type='submit'>Submit</button>
                                        </form>
                                    </div>
                                </div>
                                <Modal open={openModal} product={modalProduct} showAvailabilty={false} onClose={() => setOpenModal(false)} />

                            </>
                            ) : (
                                <div className='main-grid-order'>
                                    <ProductCard productList={productList} setDetails={setDetails}/>
                                    <div className='second-column-order-display'>
                                        <p className='to-pay-tag'>To pay : {price}</p>
                                        <ShippmentDetails mappedAddress={mappedAddress}/>
                                        <div className='btn-order'>
                                            <button onClick={editAddress} className='edit-btn'>Edit data</button>
                                        </div>
                                        <p>Payment method : {paymentMethod}</p>
                                        <div className='btn-order'>
                                            <button onClick={deleteProductList} className='buy-btn'>Buy</button>
                                        </div>     
                                    </div>
                                </div>
                            )}

                        </>
                    ) : (
                        <>
                             <NoContent message={"Your order is empty!"}/>
                        </>  
                    )}
                    </>

                )
            }
        </div>
    )
}