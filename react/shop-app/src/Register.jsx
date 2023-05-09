import React, { useState } from "react";

export const Register = (props) => {
    const [name, setName] = useState();
    const [surename, setSurename] = useState();
    const [gender,setGender] = useState();
    const [day,setDay] = useState();
    const [month,setMonth] = useState();
    const [year,setYear] = useState();
    const [email, setEmail] = useState();
    const [pass, setPass] = useState();


    const handleSubmit = (e) => {
        e.preventDefault();
    }

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
                <select>
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
            </form>
            <button onClick={() => props.onFormSwitch('login')}>Already have an account? Login here.</button>

        </>
    )
}