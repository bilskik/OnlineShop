import React from "react";

export const ProductCard = (props) => {

    const handleSetDetailsFromChild = (product) => {
        props.setDetails(product);
    }

    const productList = props.productList;
    return (
        <div className='grid-container-cart'>
        {productList.map((product) => {
            return (
            <div className="wrapper">
                <div className="image">
                    <img src={product.image} alt="product-image"/>
                </div>
                <div className="rest">
                    <div className="p-container">
                        <p className="price">{product.price} zł</p>
                        <p>{product.productName}</p>
                        <p>Sztuk: {product.cartItemsAmount}</p>
                    </div>
                    <div className="btn-container">
                        <button onClick={() => handleSetDetailsFromChild(product)}>zobacz</button>
                    </div>
                </div>
            </div>
            )
        })}
        </div>
    )
}