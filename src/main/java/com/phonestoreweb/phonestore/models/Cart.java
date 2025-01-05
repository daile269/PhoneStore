package com.phonestoreweb.phonestore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private User userCart;
    private Long userId;

    @OneToMany(mappedBy = "cart")
    private List<CartDetails> cartDetails;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    public void addItem(CartDetails item){
        this.cartDetails.add(item);
        item.setCart(this);
        updateTotalAmount();
    }

    public void removeItem(CartDetails cartDetail){
        this.cartDetails.remove(cartDetail);
        cartDetail.setCart(null);
        updateTotalAmount();
    }
    public void clearCart(){
        this.cartDetails.clear();
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = (BigDecimal) cartDetails.stream().map(cartDetails1 -> {
            BigDecimal unitPrice = cartDetails1.getUnitPrice();
            if(unitPrice==null){
                return BigDecimal.ZERO;
            }
            return unitPrice.multiply(BigDecimal.valueOf(cartDetails1.getQuantity()));
        }).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
