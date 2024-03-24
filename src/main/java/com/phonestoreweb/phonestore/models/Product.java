package com.phonestoreweb.phonestore.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private double discount;

    @Formula("price*(100-discount)/100")
    private double currentPrice;
    private Integer saleNumber;
    private Integer stockNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfManufacture;
    private String cpu;
    private Integer ram;
    private String color;
    private double screen;
    private Integer battery;

    @ManyToOne
    @JoinColumn(name = "supplierId",insertable = false,updatable = false)
    private Supplier supplierProduct;
    private Long supplierId;

    @ManyToOne
    @JoinColumn(name = "categoryId",insertable = false,updatable = false)
    private Category categoryProduct;
    private Long categoryId;

    @OneToMany(mappedBy = "productOrderDetails")
    private List<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "productCartDetails")
    private List<CartDetails> cartDetails;
}
