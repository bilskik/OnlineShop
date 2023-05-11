import React, { useEffect, useState } from "react";
import { myAxios } from "./api/axios";
import { Navigate, useNavigate } from "react-router-dom";

const REGISTER_URL = '/register';

export const Register = (props) => {
    const [name, setName] = useState('');
    const [surename, setSurename] = useState('');
    const [gender,setGender] = useState('male');
    const [day,setDay] = useState('');
    const [month,setMonth] = useState('');
    const [year,setYear] = useState('');
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [date,setDate] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await myAxios.post(REGISTER_URL,
                {name,surename,email,dateOfBirth: date,gender,password:pass}, {
                    headers: { 'Content-Type' : 'application/json'},
                    withCredentials: true
                }
                )
                // console.log(response.data)
                // console.log(response.accessToken)
                // console.log(JSON.stringify(response))
                localStorage.setItem('token', response.data.token);
                navigate('/products')
        }
        catch(err) {

        }

    }
    useEffect(() => {
        setDate(day + "-" + month + "-" + year);
    },[day,month,year])

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">name</label>
                <input value={name} onChange={(e) => setName(e.target.value)} placeholder="name" id="name" name="name" />
                <label htmlFor="surename">surename</label>
                <input value={surename} onChange={(e) => setSurename(e.target.value)} placeholder="surename" id="surename" name="surename" />
                <label htmlFor="email">email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="email" id="email" name="email" />
                <label>gender</label>
                <select value={gender} onChange={(e) => setGender(e.target.value)}>
                    <option value="male">male</option>
                    <option value="female">female</option>
                </select>
                <label htmlFor="day">Date</label>
                <input value={day} onChange={(e) => setDay(e.target.value)} placeholder="dd" id="day" name="day"/>
                <label htmlFor="month">Month</label>
                <input value={month} onChange={(e) => setMonth(e.target.value)} placeholder="mm" id="month" name="month"/>
                <label htmlFor="year">Year</label>
                <input value={year} onChange={(e) => setYear(e.target.value)} placeholder="yyyy" id="year" name="year"/>
                <label htmlFor="password">password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="password" id="password" name="password" />
                <button type="submit">Register</button>
            </form>
            <button onClick={() => props.onFormSwitch('login')}>Already have an account? Login here.</button>

        </>
    )
}