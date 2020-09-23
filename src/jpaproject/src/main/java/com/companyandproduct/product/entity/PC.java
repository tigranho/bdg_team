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

public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    private int code;

    @NotNull
    private String model;

    @NotNull
    private int speed;
    @NotNull
   private int ram;

    @NotNull
    private int hd;
    @Column(nullable = false)
    private int cd;

    @Column(nullable = false)
    private float price;

}
