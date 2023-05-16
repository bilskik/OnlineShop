import React, { useEffect, useState } from 'react';
import { myAxios } from '../api/axios';
import { getHeaders } from '../api/getHeaders';
import { json, useNavigate, useParams } from 'react-router-dom';
import { Table } from "./Table"
const ORDER_URL = '/orders';

export const Order = () => {
    const [productList,setProductList] = useState();
    const [isLoading,setIsLoading] = useState(true);
    const [price,setPrice] = useState(0);
    const { data } = useParams();
    const [country,setCountry] = useState();
    const [city,setCity] = useState()
    const [street,setStreet] = useState();
    const [code,setCode] = useState();
    const [orderState,setOrderState] = useState(false);
    const [mappedAddress, setMappedAddress] = useState({});
    const [reloadPage,setReloadPage] = useState(false);
    const [order,setOrder] = useState();
    const navigate = useNavigate();
    // useEffect(() => {
    //     const headers = getHeaders();
    //     const getData = async () => {
    //         const response = await myAxios.get(ORDER_URL,{
    //             headers
    //         })
    //         console.log(response.data);
    //         setOrder(response.data);
    //         setProductList(response.data.productList);
    //         setIsLoading(false);
    //         console.log(response.data.productList);
    //         countPrice(response.data.productList);
    //     }
    //     getData();
        
    // },[])
    useEffect(() => {
        
        const getOrder = async () => {
            const headers = getHeaders();
            const response = await myAxios.get(ORDER_URL,
                {headers}
            ).then(response => {
                console.log(response.data);
                if(response.data) {
                    setOrder(response.data);
                    setProductList(response.data.productList);
                    setIsLoading(false);
                    setOrderState(true);
                    countPrice(response.data.productList);
                    mapNamesInAddress(response.data.address);
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
            address : address
        }
        console.log(order);
        const headers = getHeaders();
        const postData = async () => {
            const response = await myAxios.post(ORDER_URL,
                order
            ,{
                headers
            }).then(response => {
                setReloadPage(true);
            })
        }
        postData();
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
        navigate("/products");
    }
    return (
        <div>
            {
                isLoading ? ( 
                    <p>is loading...</p>
                ) : (
                        <>
                            { !orderState ? (
                            <> 
                                <Table productList={productList}/>
                                <p>Do zaplaty: {price}</p>
                                <form onSubmit={handleSubmit}>
                                    <label htmlFor='county'>Państwo</label>
                                    <input id='country' name='country' placeholder='city' onChange={(e) => setCountry(e.target.value)}/>
                                    <label htmlFor='city'>Miasto</label>
                                    <input id='city' name='city' placeholder='city' onChange={(e) => setCity(e.target.value)}/>
                                    <label htmlFor=''>Ulica</label>
                                    <input id='street' name='street' placeholder='street' onChange={(e) => setStreet(e.target.value)}/>
                                    <label htmlFor='zip-code'>Kod Pocztowy</label>
                                    <input id='zip-code' name='zip-code' placeholder='zip-code' onChange={(e) => setCode(e.target.value)}/>
                                    <button type='submit'>Submit</button>
                                </form>
                            </>
                            ) : (
                                <div>
                                    <Table productList={productList}/>
                                    <p>Do zaplaty : {price}</p>
                                    <p>Szczegóły wysyłki:</p>
                                    {
                                        Object.keys(mappedAddress).map(key => {
                                            return (
                                                <>
                                                    <p key={key}>
                                                        {key} : {mappedAddress[key]}
                                                    </p>
                                                </>
                                            )
                                        })
                                    }
                                    <p>Zrezygnuj z kupna:</p>
                                    <button onClick={deleteOrder}>Zrezygnuj</button>
                                </div>
                            )}
                        </>
                )
            }
        </div>
    )
}