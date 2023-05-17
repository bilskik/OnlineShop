package com.bilskik.onlineshop.enumeration;

import jakarta.persistence.Enumerated;

public enum PaymentType {
    BLIK,
    KartaPłatnicza,
    GooglePay,
    ApplePay,
    Przelew
}
