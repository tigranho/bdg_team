package com.companyandproduct.company1.picture;



import com.companyandproduct.company1.book.Book;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "picture")
public class Picture {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String pictureName;

    @OneToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL,mappedBy = "picture")
    Book book;


}
