import React from "react"
import "../css/nocontent.css"
export const NoContent = ({message}) => {
    return (
        <div className="nocontent-container">
            <div className="message-container">
                <h2>{message}</h2>
            </div>
        </div>
    )
}