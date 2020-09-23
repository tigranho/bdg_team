package com.companyandproduct.company1.book;


import com.companyandproduct.company1.commonimplementations.OrderOrder;
import com.companyandproduct.company1.picture.Picture;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String author;




    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "picture_id",nullable = false)
    Picture picture;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "order1", column = @Column(name = "order_1")),
            @AttributeOverride(name = "order2", column = @Column(name = "order_2"))
    })
    OrderOrder orderOrder;
    public Book(String name,String author,Picture picture,OrderOrder orderOrder){
        this.name = name;
        this.author = author;
      this.picture = picture;
      this.orderOrder = orderOrder;

    }


    public Book(String name,String author){
        this.name = name;
        this.author = author;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public OrderOrder getOrderOrder() {
        return orderOrder;
    }

    public void setOrderOrder(OrderOrder orderOrder) {
        this.orderOrder = orderOrder;
    }
}
