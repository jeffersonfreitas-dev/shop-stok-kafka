package dev.jeffersonfreitas.shopapi.dto;

import dev.jeffersonfreitas.shopapi.model.ShopReport;
import lombok.Data;

@Data
public class ShopReportDTO {

    private String identifier;
    private Integer amount;

    public static ShopReportDTO convert(ShopReport shopReport) {
        ShopReportDTO shopDTO = new ShopReportDTO();
        shopDTO.setIdentifier(shopReport.getIdentifier());
        shopDTO.setAmount(shopReport.getAmount());
        return shopDTO;
    }
}
