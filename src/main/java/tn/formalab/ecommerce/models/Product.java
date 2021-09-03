package tn.formalab.ecommerce.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "name",nullable = false)
    public String name;

    @Column(name = "description",nullable = false)
    public String description;

    @Column(name = "imageUrl",nullable = false)
    public String imageUrl;

    @Column(name = "price",nullable = false)
    public Double price;


    //product 0..*----1 category
    //ManyToOne ------- OneToMany
    @ManyToOne
    @JoinColumn(name = "idCategory")
    public Category category;
}
