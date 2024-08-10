package com.turkcell.sol.catalog_service.service.impl;

import com.turkcell.sol.catalog_service.dto.responses.GetCatalogItemResponse;
import com.turkcell.sol.catalog_service.dto.responses.GetProductResponse;
import com.turkcell.sol.catalog_service.mapper.CatalogMapper;
import com.turkcell.sol.catalog_service.mapper.ProductMapper;
import com.turkcell.sol.catalog_service.model.CatalogItem;
import com.turkcell.sol.catalog_service.model.Product;
import com.turkcell.sol.catalog_service.repository.CatalogRepository;
import com.turkcell.sol.catalog_service.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    @Override
    public List<GetCatalogItemResponse> getAll() {
        List<CatalogItem> catalogItemList = catalogRepository.getAll();
        return catalogMapper.toGetCatalogItemResponse(catalogItemList);
    }

    @Override
    public GetCatalogItemResponse getById(String id) {

        Optional<CatalogItem> catalogItem = catalogRepository.getById(id);
        return catalogMapper.toGetCatalogItemResponse(catalogItem.get());
    }

    @Override
    public void delete(String id) {
        catalogRepository.delete(id);
    }

    @Override
    public void add(CatalogItem catalogItem) {
        catalogRepository.addOrUpdate(catalogItem);
    }
}
