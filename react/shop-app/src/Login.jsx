import React, { useEffect, useState, useRef } from "react"
import { myAxios } from "./api/axios";
import { useNavigate } from "react-router-dom";
import useAuth from "./hooks/useAuth";
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
            console.log(response.data)
            console.log(response.accessToken)
            console.log(JSON.stringify(response))

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
            setErrMsg('Registration failed!');
            // errRef.current.focus();
        }
    }
    return (
        <section>
            <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive"></p>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">email</label>
                <input 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)}
                    type="email" 
                    placeholder="email"
                    id="email"
                    name="email"
                    required
                    autoComplete="off"

                />
                <label htmlFor="password">password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} required autoComplete="off" type="password" placeholder="*******" id="password" name="password"/>
                <button type="submit">Log In</button>
            </form>
            <button onClick={() => props.onFormSwitch('register')}>Already have an account? Register here</button>
        </section>
    )
}