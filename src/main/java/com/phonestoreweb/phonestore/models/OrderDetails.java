package com.phonestoreweb.phonestore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "productId",insertable = false,updatable = false)
    private Product productOrderDetails;
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "orderId",insertable = false,updatable = false)
    private Orders orderDetails;
    private Long orderId;

    @OneToOne(mappedBy = "orderDetails")
    private OrderStatus orderStatus;

}
