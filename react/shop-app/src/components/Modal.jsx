import React, { useEffect, useState } from "react"
import '../css/Modal.css';

export const Modal = ({open, product, onClose}) => {
    if(!open) {
        return null;
    }
    return (
        <div className="overlay" onClick={onClose}>
            <div className="modalContainer">
                <p>{product.productDetails.details}</p>
                <p>{product.productDetails.description}</p>
                <p>{product.amount}</p>
                <p onClick={onClose} className="closeBtn">X</p>
            </div>
        </div>
    )
}
export default Modal