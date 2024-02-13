package dev.jeffersonfreitas.shopapi.controller;

import dev.jeffersonfreitas.shopapi.dto.ShopDTO;
import dev.jeffersonfreitas.shopapi.events.KafkaClient;
import dev.jeffersonfreitas.shopapi.model.Shop;
import dev.jeffersonfreitas.shopapi.model.ShopItem;
import dev.jeffersonfreitas.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRepository shopRepository;
    private final KafkaClient kafkaClient;

    @GetMapping
    public List<ShopDTO> getShop() {
        return	shopRepository.findAll().stream().map(ShopDTO::convert).toList();
    }


    @PostMapping
    public	ShopDTO	saveShop(@RequestBody ShopDTO	shopDTO) {
        shopDTO.setIdentifier(UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDate.now());
        shopDTO.setStatus("PENDING");
        Shop shop = Shop.convert(shopDTO);

        for	(ShopItem shopItem	:	shop.getItems())	{
            shopItem.setShop(shop);
        }
        shopDTO = ShopDTO.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDTO);
        return shopDTO;
    }

}
