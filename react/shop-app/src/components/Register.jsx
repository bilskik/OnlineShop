import React, { useEffect, useState } from "react";
import { myAxios } from "../api/axios";
import { Navigate, useNavigate } from "react-router-dom";
import "../css/Register.css"
import { Alert } from "./Alert"
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
    const [dayData,setDayData] = useState([]);
    const [yearData,setYearData] = useState([]);
    const [error,setError] = useState(false);
    const [errMsg,setErrMsg] = useState('');
    const navigate = useNavigate();
    //translate to eng
    const monthData = [
        "styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", 
        "lipiec", "sierpień", "wrzesień", "październik","listopad","grudzień"
    ];
    useEffect(() => {
        setInitBirthDay()
        createData();
    },[])
    const setInitBirthDay = () => {
        const currDate = new Date();
        setYear(currDate.getFullYear());
        setMonth(currDate.getMonth() + 1)
        setDay(currDate.getDay())
    }
    const createData = function() {
        let dayData = [];
        let yearData = [];
        for(let i=1; i<31; i++){
            dayData.push(i);
        }
        for(let i=1950; i<=2023; i++) {
            yearData.push(i);
        }
        setDayData(dayData);
        setYearData(yearData);
    }
    //data tez jest źle 

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await myAxios.post(REGISTER_URL,
                {name,surename,email,dateOfBirth: date,gender,password:pass}, {
                    headers: {
                         'Content-Type' : 'application/json'
                },
                    withCredentials: true
                }).then((response) => {
                        localStorage.setItem('token', response.data.token);
                        navigate('/products')
                })
        }
        catch(err) {
            setErrMsg(err.response.data.message)
            setError(true);
        }

    }
    useEffect(() => {
        let currIndex = 0;
        let indexMonth = 0;
        monthData.forEach(currMonth => {
            if(currMonth === month) {
                indexMonth = currIndex;
            }
            currIndex++;
        })
        indexMonth++;
        let day1= day.toString().padStart(2,'0');
        let month1 = month.toString().padStart(2,'0');
        console.log(day1 + "-" + month1 + "-" + year);
        setDate(day1 + "-" + month1 + "-" + year);
    },[day,month,year])

    const handleDataFromChild = () => {
        setError(false);
    }

    return (
        <div className="page-container">
            { error && (
                 <Alert defaultValue="show" sendDataToParent={handleDataFromChild} data={errMsg}/>
            )}
            <div className="login-container-register">
            <h1>Register</h1>
            <section className="section">
                <form onSubmit={handleSubmit} className="form">
                    <label htmlFor="name">name</label>
                    <input value={name} onChange={(e) => setName(e.target.value)} placeholder="name" id="name" name="name" />
                    <label htmlFor="surename">surename</label>
                    <input value={surename} onChange={(e) => setSurename(e.target.value)} placeholder="surename" id="surename" name="surename" />
                    <label htmlFor="email">email</label>
                    <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="email" id="email" name="email" />
                    <p className="p">gender:</p>
                    <div className="radio-group">
                        <div className="radio-option">
                            <label htmlFor="male" className="radio-label">
                            <input type="radio" id="male" name="gender" value="male" checked={gender === "male"} onChange={(e) => setGender(e.target.value)} />
                                Male
                            </label>
                        </div>
                        <div className="radio-option">
                            <label htmlFor="female" className="radio-label">
                                <input type="radio" id="female" name="gender" value="female" checked={gender === "female"} onChange={(e) => setGender(e.target.value)} />
                                Female
                            </label>
                        </div>
                    </div>
                    <p className="p">date:</p>
                    <div className="select-group-register">
                        <div className="select-option">
                            <select value={day} onChange={(e) => setDay(e.target.value)}>
                                {
                                    dayData.map(day => {
                                        return (
                                            <option value={day}>{day}</option>
                                        )
                                    })
                                }
                            </select>
                        </div>
                        <div className="select-option">
                            <select value={month} onChange={(e) => setMonth(e.target.value)}>
                                    {
                                        monthData.map(month => {
                                            return (
                                                <option value={month}>{month}</option>
                                            )
                                        })
                                    }
                            </select>
                        </div>
                        <div className="select-option">
                            <select value={year} onChange={(e) => setYear(e.target.value)}> 
                                {
                                    yearData.map(year => {
                                        return (
                                            <option value={year}>{year}</option>
                                        )
                                    })
                                }
                            </select>
                        </div>
                    </div>
                    <label htmlFor="password">password</label>
                    <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="password" id="password" name="password" />
                    <button type="submit" className="btn">Register</button>
                    <button onClick={() => props.onFormSwitch('login')} className="btn">Login</button>

                </form>
            </section>
            </div>
        </div>
    )
}