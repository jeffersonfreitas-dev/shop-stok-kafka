package dev.jeffersonfreitas.shopapi.controller;

import dev.jeffersonfreitas.shopapi.dto.ShopReportDTO;
import dev.jeffersonfreitas.shopapi.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop_report")
@RequiredArgsConstructor
public class ShopController {

    private final ReportRepository repository;

    @GetMapping
    public List<ShopReportDTO> getShopReports() {
        return repository.findAll().stream()
                .map(ShopReportDTO::convert).toList();
    }
}
