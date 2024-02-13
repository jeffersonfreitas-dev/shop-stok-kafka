package dev.jeffersonfreitas.shopapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product")
@Table(name = "product", schema = "shop")
public class Product {

    @Id
    @GeneratedValue(strategy	=	GenerationType.IDENTITY)
    private	Long	id;

    @Column(name	=	"product_identifier")
    private	String	identifier;

    private	Integer	amount;
}
