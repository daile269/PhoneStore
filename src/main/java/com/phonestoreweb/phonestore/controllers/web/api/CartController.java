package com.phonestoreweb.phonestore.controllers.web.api;

import com.phonestoreweb.phonestore.dto.response.ApiResponse;
import com.phonestoreweb.phonestore.models.Cart;
import com.phonestoreweb.phonestore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/carts")
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/{cardId}/my-cart")
    public Cart getCart(@PathVariable Long cardId){
        return cartService.getCart(cardId);
    }

    @DeleteMapping("/{cardId}/clear-card")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long cardId){
        cartService.clearCart(cardId);
        return ResponseEntity.ok(new ApiResponse("Clear cart Success",null));
    }

    @GetMapping("/{cardId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable Long cardId){
        BigDecimal totalPrice = cartService.getTotalPrice(cardId);
        return ResponseEntity.ok(new ApiResponse("Total price: ",totalPrice));
    }


}
