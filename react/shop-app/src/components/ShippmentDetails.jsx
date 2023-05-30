import React from "react"
import "../css/order.css"
export const ShippmentDetails = ({mappedAddress}) => {
    return (
        <div className="shippment-details">
            <p>Address details:</p>
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