import React, { useEffect, useState, useRef } from "react"
import { myAxios } from "../api/axios";
import { useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import '../css/Login.css'
import '../css/Alert.css'
const LOGIN_URL = '/login'

export const Login = (props) => {
    const { setAuth } = useAuth();
    const emailRef = useRef();
    const errRef = useRef();

    const[email,setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const[emailFocus, setEmailFocus] = useState(false);

    const[pass,setPass] = useState('');
    const [validPass, setValidPass] = useState(false);
    const[passFocus, setPassFocus] = useState(false);

    const [errMsg,setErrMsg] = useState('');
    const [error,setError] = useState(false);
    const [success, setSuccess] = useState(false);

    const navigate = useNavigate();
    


    useEffect(() => {
        setErrMsg('');
    },[email,pass])

    // useEffect(() => {
    //     const result = true;
    //     setValidEmail(result);
    // },[email])

    // useEffect(() => {
    //     const result = true;
    //     setValidPass(result);
    // },[pass])
    
    // useEffect( () => {
    //     setErrMsg('');
    // },[email,pass]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            
            const response = await myAxios.post(LOGIN_URL,
                { email, password : pass }, {
                    headers: { 'Content-Type' : 'application/json'},
                    withCredentials: true
                }
            );
            localStorage.setItem('token', response.data.token);
            const accessToken = response.data.accessToken;
            const roles = response.data.roles;
            setAuth({ email, pass, accessToken});
            setEmail('')
            setPass('')
            setSuccess(true);
            navigate('/products')
            //clear input fields
        }
        catch(err) {
            setError(true);
            setErrMsg('Registration failed!');
        }
    }
    return (
        <>
            <div className="alert">
                <span className="fas fa-exclamation-circle"></span> 
                <span className="msg">SIEMA</span>
                <div class="close-btn">
                    <span class="fas fa-times"></span>
                </div>
            </div>
        <div className="page-container">
            { error && (
                <p>SIEMA</p>
            )}

            <div className="login-container">
                <h1 className="h1">Login</h1>
                <section className="section">
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive"></p>
                    <form onSubmit={handleSubmit} className="form">
                        <label htmlFor="email" className="label">Email</label>
                        <input 
                            className="input"
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)}
                            type="email" 
                            placeholder="email"
                            id="email"
                            name="email"
                            required
                            autoComplete="off"

                        />
                        <label htmlFor="password" className="label">Password</label>
                        <input value={pass} onChange={(e) => setPass(e.target.value)} required autoComplete="off"
                         type="password" placeholder="*******" id="password" name="password" className="input"/>
                        <button type="submit" className="btn">Zaloguj się</button>
                        <button onClick={() => props.onFormSwitch('register')} className="btn">Zarejstruj</button>
                    </form>
                    
                </section>
            </div>
        </div>
        </>
    )
}