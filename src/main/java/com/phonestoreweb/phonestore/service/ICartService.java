package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id        );

    Long initializeNewCart();
}
