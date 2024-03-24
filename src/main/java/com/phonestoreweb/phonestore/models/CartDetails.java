package com.phonestoreweb.phonestore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Value("1")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "productId",insertable = false,updatable = false)
    private Product productCartDetails;
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "cartId",insertable = false,updatable = false)
    private Cart cartDetails;
    private Long cartId;
}
