package com.companyandproduct.product.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private char color;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String maker;

}
