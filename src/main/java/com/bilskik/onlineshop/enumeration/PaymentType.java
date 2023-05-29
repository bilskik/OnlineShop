package com.bilskik.onlineshop.enumeration;

import jakarta.persistence.Enumerated;

public enum PaymentType {
    BLIK,
    KartaPlatnicza,
    GooglePay,
    ApplePay,
    Przelew
}
