import React, { useState } from "react"

export const Login = () => {
    const[email,setEmail] = useState('');
    const[pass,setPass] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
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
            <button>Already have an account? Register here</button>
        </>
    )
}