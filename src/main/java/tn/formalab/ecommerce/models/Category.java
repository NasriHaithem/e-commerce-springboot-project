package tn.formalab.ecommerce.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "name",unique = true, nullable = false)
    public String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    public List<Product> products;

}
