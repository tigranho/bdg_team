package com.companyandproduct.product.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Laptop {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private int code;

    @NotNull
    private String model;
    private int speed;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int hd;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int screen;
}
