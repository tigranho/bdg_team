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
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String maker;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String type;

}
