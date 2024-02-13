package dev.jeffersonfreitas.shopapi.model;

import dev.jeffersonfreitas.shopapi.dto.ShopDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy	=	GenerationType.IDENTITY)
    private	Long	id;

    private	String	identifier;

    private	String	status;

    @Column(name	=	"date_shop")
    private LocalDate dateShop;

    @OneToMany( cascade	=	CascadeType.ALL, mappedBy	=	"shop")
    private List<ShopItem> items;

    public	static	Shop	convert(ShopDTO shopDTO) {
        Shop	shop	=	new	Shop();
        shop.setIdentifier(shopDTO.getIdentifier());
        shop.setStatus(shopDTO.getStatus());
        shop.setDateShop(shopDTO.getDateShop());
        shop.setItems(shopDTO
                .getItems()
                .stream()
                .map(i	->	ShopItem.convert(i))
                .collect(Collectors.toList()));
        return	shop;
    }

}
