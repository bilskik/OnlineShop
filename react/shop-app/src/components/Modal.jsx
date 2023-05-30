import React, { useEffect, useState } from "react"
import '../css/Modal.css';

export const Modal = ({open, product, showAvailabilty, onClose}) => {
    if(!open) {
        return null;
    }
    return (
        <div className="overlay" onClick={onClose}>
            <div className="modal-container">
                <img src={product.image} alt="product-image"/>
                <div className="down-row">
                    <p clasname="description">{product.productDetails.details}</p>
                    <p className="description">{product.productDetails.description}</p>
                    {
                        showAvailabilty && (
                            <p>Amount: {product.amount}</p>
                        )
                    }
                </div> 
            </div>
        </div>
    )
}
export default Modal