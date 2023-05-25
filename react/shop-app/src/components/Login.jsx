import React, { useEffect, useState, useRef } from "react"
import { myAxios } from "../api/axios";
import { useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import '../css/Login.css'
import '../css/Alert.css'
import { Alert }  from "./Alert"
import { LOGIN_URL } from "../constraints/urls";
import { PRODUCT_PAGE } from "../constraints/pages";

export const Login = (props) => {
    const { setAuth } = useAuth();
    // const errRef = useRef();
    const[email,setEmail] = useState('');
    const[pass,setPass] = useState('');
    const [error,setError] = useState({
        hasError : false,
        message : ''
    });
    const navigate = useNavigate();
    useEffect(() => {
        setError({
            hasError: false,
            message: ''
        })
    },[email,pass])

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            
            const response = await myAxios.post(LOGIN_URL,
                { email, password : pass }, {
                    headers: { 'Content-Type' : 'application/json'},
                    withCredentials: true
                }
            ).then((response) => {
                console.log(response);
                    localStorage.setItem('token', response.data.token);
                    const accessToken = response.data.accessToken;
                    const roles = response.data.roles;
                    setAuth({ email, pass, accessToken});
                    setEmail('')
                    setPass('')
                    navigate(PRODUCT_PAGE)
            })
            //clear input fields
        }
        catch(err) {
            setError({
                hasError: true,
                message: err.response.data.message
            });
        }
    }
    const handleDataFromChild = () => {
        setError({
            hasError: false,
            message: ''
        });
    }
    useEffect(() => {
        if(error.hasError) {
            setTimeout(() => {
                setError(false);
            },5000)
        }
    },[error]);
    return (
        <>
        <div className="page-container">
            { error.hasError && 
                <Alert defaultValue="show" sendDataToParent={handleDataFromChild} data={error.message}/>

            }

            <div className="login-container">
                <h1 className="h1">Login</h1>
                <section className="section">
                    {/* <p ref={errRef} className={error.hasError ? "errmsg" : "offscreen"} aria-live="assertive"></p> */}
                    <form onSubmit={handleSubmit} className="form">
                        <label htmlFor="email">Email</label>
                        <input 
                            // className="input"
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)}
                            type="email" 
                            placeholder="email"
                            id="email"
                            name="email"
                            required
                            autoComplete="off"

                        />
                        <label htmlFor="password">Password</label>
                        <input value={pass} onChange={(e) => setPass(e.target.value)} required autoComplete="off"
                         type="password" placeholder="*******" id="password" name="password"/>
                        <button type="submit" className="btn">Log in</button>
                        <button onClick={() => props.onFormSwitch('register')} className="btn">Sign up</button>
                    </form>
                    
                </section>
            </div>
        </div>
        </>
    )
}