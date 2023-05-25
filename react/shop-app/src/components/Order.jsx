import React, { useEffect, useState } from 'react';
import { myAxios } from '../api/axios';
import { getHeaders } from '../api/getHeaders';
import {useNavigate, useParams } from 'react-router-dom';
import { ProductCard } from "./ProductCard"
import { ORDER_URL, PRODUCT_URL } from '../constraints/urls';
import "../css/order.css";
import {Modal} from "./Modal";
import { ShippmentDetails } from './ShippmentDetails';
import { PRODUCT_PAGE } from '../constraints/pages';

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

    const navigate = useNavigate();
    useEffect(() => {
        
        const getOrder = async () => {
            const headers = getHeaders();
            const response = await myAxios.get(ORDER_URL,
                {headers}
            ).then(response => {
                console.log("JESTEM W GECIE!");
                console.log(response.data);
                if(response.data) {
                    setOrder(response.data);
                    setProductList(response.data.productList);
                    setIsLoading(false);
                    setOrderState(true);
                    countPrice(response.data.productList);
                    mapNamesInAddress(response.data.address);
                    checkIfProdutListIsEmpty(response.data.productList);
                    setPaymentMethod(response.data.paymentType)
                    console.log(response.data.paymentType);
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
        console.log(sum);
        setPrice(sum);
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        const date = getDate();
        const address = getAddress();
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
    }
    const isValidForm = (order) => {
        const isValid = Object.values(order).every(value => value != '');
        return isValid;
    }
    const getDate = () => {
        let currDate = new Date();
        const year = currDate.getFullYear();
        const month = (currDate.getMonth() + 1).toString().padStart(2,'0');
        const day = currDate.getDay().toString().padStart(2,'0');
        return day + "-" + month + "-" + year;
    }
    const getAddress = () => {
        return {
            "country" : country,
            "city": city,
            "street" : street,
            "zipCode" : code
        }
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
            )
        }
        navigate(PRODUCT_PAGE);
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
    const handleAddressSubmit = (e) => {
        e.preventDefault();
        const address = getAddress();
        const order = {
            address,
            paymentType : paymentMethod
        }
        console.log(address);
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
        navigate(PRODUCT_PAGE);
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
            const response = await myAxios.delete(PRODUCT_URL,    {
                data: productIdsList,
                headers: headers
              }
            )
        }
        deleteHttpMethod(productIdsList,headers);

    }  

    const updateProductInDB = async(productList) => {
        const updateEachProduct = async (product,headers) => {
            const deleteProduct = await myAxios.put(PRODUCT_URL, product,
                {headers}
            ).then(() => {
    
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
    return (
        <div className='product-page'>

            {
                isLoading ? ( 
                    <p>is loading...</p>
                ) : (
                    <>
                    { isOrderAvailable ? (

                        <>
                            <header className='product-header-container-cart'>
                                <h2>Order</h2>
                            </header>
                            { !orderState ? (
                            <>
                                <div className="main-grid-order">
                                    <ProductCard productList={productList} setDetails={setDetails}/>
 
                                    <div className="second-column-order">
                                        <p className='to-pay'>Do zaplaty: {price} zł</p>
                                        <form onSubmit={!isAddressEdited ? handleSubmit : handleAddressSubmit} className='form-container-order'>
                                            <label htmlFor='county'>Państwo</label>
                                            <input id='country' name='country' placeholder='country' onChange={(e) => setCountry(e.target.value)}/>
                                            <label htmlFor='city'>Miasto</label>
                                            <input id='city' name='city' placeholder='city' onChange={(e) => setCity(e.target.value)}/>
                                            <label htmlFor=''>Ulica</label>
                                            <input id='street' name='street' placeholder='street' onChange={(e) => setStreet(e.target.value)}/>
                                            <label htmlFor='zip-code'>Kod Pocztowy</label>
                                            <input id='zip-code' name='zip-code' placeholder='zip-code' onChange={(e) => setCode(e.target.value)}/>
                                            <label>Metoda płatności:</label>
                                            <select value={paymentMethod} onChange={(e) => {setPaymentMethod(e.target.value)}}>
                                                <option value="Karta Płatnicza">Karta Płatnicza</option>
                                                <option value="Google Pay">Google Pay</option>
                                                <option value="Apple Pay">Apple Pay</option>
                                                <option value="BLIK">BLIK</option>
                                                <option value="Przelew">Przelew</option>
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
                                        <p className='to-pay-tag'>Do zaplaty : {price}</p>
                                        <ShippmentDetails mappedAddress={mappedAddress}/>
                                        <div className='btn-order'>
                                            <button onClick={editAddress} className='edit-btn'>Edit data</button>
                                        </div>
                                        <p>Metoda płatności : {paymentMethod}</p>
                                        <div className='btn-order'>
                                            <button onClick={deleteProductList} className='buy-btn'>Buy</button>
                                        </div>     
                                    </div>
                                </div>
                            )}

                        </>
                    ) : (
                        <>
                            <p>Nie ma wybranego produktu do kupienia!</p>
                        </>  
                    )}
                    </>

                )
            }
        </div>
    )
}