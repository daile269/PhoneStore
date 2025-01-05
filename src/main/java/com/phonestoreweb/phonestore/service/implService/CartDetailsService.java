package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.exception.AppException;
import com.phonestoreweb.phonestore.exception.ErrorCode;
import com.phonestoreweb.phonestore.models.Cart;
import com.phonestoreweb.phonestore.models.CartDetails;
import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.repositories.CartDetailsRepository;
import com.phonestoreweb.phonestore.repositories.CartRepository;
import com.phonestoreweb.phonestore.service.ICartDetailsService;
import com.phonestoreweb.phonestore.service.ICartService;
import com.phonestoreweb.phonestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartDetailsService implements ICartDetailsService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.findProductById(productId);
        CartDetails cartItem = cart.getCartDetails()
                .stream()
                .filter(cartDetails -> cartDetails.getProductCartDetails().getId().equals(productId))
                .findFirst().orElse(new CartDetails());

        if (cartItem.getId()== null){
            cartItem.setCart(cart);
            cartItem.setCartId(cart.getId());
            cartItem.setProductCartDetails(product);
            cartItem.setProductId(product.getId());
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(BigDecimal.valueOf(product.getCurrentPrice()));
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity()+ quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartDetailsRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartDetails itemToRemove = getCartDetail(cartId,productId);
        cart.removeItem(itemToRemove);
        cartDetailsRepository.deleteById(itemToRemove.getId());
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getCartDetails()
                .stream()
                .filter(cartDetails -> cartDetails.getProductCartDetails().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(BigDecimal.valueOf(item.getProductCartDetails().getCurrentPrice()));
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    public CartDetails getCartDetail(Long cartId, Long productId){
        Cart cart = cartService.getCart(cartId);
        return cart.getCartDetails()
                .stream()
                .filter(cartDetails -> cartDetails.getProductCartDetails().getId().equals(productId))
                .findFirst().orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
