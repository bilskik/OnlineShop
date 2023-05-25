import React from "react"
import "../css/order.css"
export const ShippmentDetails = ({mappedAddress}) => {
    return (
        <div className="shippment-details">
            <p>Szczegóły wysyłki:</p>
            {
                Object.keys(mappedAddress).map(key => {
                    return (
                        <>
                            <p key={key} className="shippment-element">
                                {key} : {mappedAddress[key]}
                            </p>
                        </>
                    )
                })
            }
        </div>
    )
}