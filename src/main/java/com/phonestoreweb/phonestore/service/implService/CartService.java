package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.exception.AppException;
import com.phonestoreweb.phonestore.exception.ErrorCode;
import com.phonestoreweb.phonestore.models.Cart;
import com.phonestoreweb.phonestore.repositories.CartDetailsRepository;
import com.phonestoreweb.phonestore.repositories.CartRepository;
import com.phonestoreweb.phonestore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    private final AtomicLong cardIdGenerator = new AtomicLong(0);
    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.CART_NOT_FOUND));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartDetailsRepository.deleteAllByCartId(id);
        cart.getCartDetails().clear();
        cartDetailsRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }
    @Override
    public Long initializeNewCart(){
        Cart newCart = new Cart();
        Long newCartId = cardIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();
    }
}
