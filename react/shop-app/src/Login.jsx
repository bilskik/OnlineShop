import React, { useState } from "react"

export const Login = (props) => {
    const[email,setEmail] = useState('');
    const[pass,setPass] = useState('');

    const loginEndpoint = 'siema';
    
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
        const userLogin = { email, pass};
        fetch(loginEndpoint, {
            method: 'POST',
            headers : {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify(userLogin)
        });
    }
    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="email">email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="email" id="email" name="email"/>
                <label htmlFor="password">password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="*******" id="password" name="password"/>
                <button type="submit">Log In</button>
            </form>
            <button onClick={() => props.onFormSwitch('register')}>Already have an account? Register here</button>
        </>
    )
}