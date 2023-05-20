import React, { useEffect } from "react";
import {useState} from "react";
export const Alert = (props) => { 
    const [changeClassName,setChangeClassName] = useState();
    useEffect(() => {
        setChangeClassName(props.defaultValue);
    },[])
    const sendDataToParent = function() {
        props.sendDataToParent(false);
    }
    return (
        <div className={`alert ${changeClassName}`}>
            <span className="fas fa-exclamation-circle"></span> 
            <span className="msg">{props.data}</span>
            <div className="close-btn" onClick={() => {setChangeClassName("hide"); sendDataToParent()}}>
                <span className="fas fa-times"></span>
            </div>
        </div>
    )
}
