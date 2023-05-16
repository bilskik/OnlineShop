import React from "react";

export const Table = (props) => {
    const productList = props.productList;
    return (
        <table>
            <thead>
                <tr>
                    <td>Nazwa Produktu:</td>
                    <td>Ilosc produktów:</td>
                    <td>Cena</td>
                </tr>
            </thead>
            {productList.map((product) => {
                return (
                    <tbody key={product.productId}>
                        <tr>
                            <td>{product.productName}</td>
                            <td>{product.cartItemsAmount}</td>
                            <td>{product.price} zł </td>
                        </tr>
                    </tbody>
                )
            })}
        </table>
    )
}