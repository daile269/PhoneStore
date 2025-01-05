package com.phonestoreweb.phonestore.controllers.web.api;

import com.phonestoreweb.phonestore.dto.response.ApiResponse;
import com.phonestoreweb.phonestore.exception.AppException;
import com.phonestoreweb.phonestore.exception.ErrorCode;
import com.phonestoreweb.phonestore.models.Cart;
import com.phonestoreweb.phonestore.service.ICartDetailsService;
import com.phonestoreweb.phonestore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(value = "/api/v1/cartDetails")
public class CartDetailController {

    @Autowired
    private ICartDetailsService cartDetailsService;

    @Autowired
    private ICartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cardId,
                                                     @RequestParam Long productId,
                                                     @RequestParam int quantity){
        try {
            if(cardId== null){
              cardId = cartService.initializeNewCart();
            }
            cartDetailsService.addItemToCart(cardId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Add to Cart success",null));
        } catch (AppException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cardId}/item/{productId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cardId,
                                                          @PathVariable Long productId){

        try {
            cartDetailsService.removeItemFromCart(cardId,productId);
            return ResponseEntity.ok(new ApiResponse("Remove success",null));
        } catch (AppException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cardId}/item/{productId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cardId,
                                                          @PathVariable Long productId,
                                                          @RequestParam int quantity){

        try {
            cartDetailsService.updateItemQuantity(cardId,productId,quantity);
            return ResponseEntity.ok(new ApiResponse("Update success",null));
        } catch (AppException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
