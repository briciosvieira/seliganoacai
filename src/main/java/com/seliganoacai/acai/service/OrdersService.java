package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.entity.RelacionamentOrdersProduct;
import com.seliganoacai.acai.repository.OrdersRepository;
import com.seliganoacai.acai.repository.RelacionamentOrdersProductRepository;
import com.seliganoacai.acai.utils.Utils;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import com.seliganoacai.acai.webConfig.dto.createDto.ProductQuantityDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repository;
    @Autowired
    private OptionalService optionalService;
    @Autowired
    private ProductService productService;

    @Autowired
    private RelacionamentOrdersProductRepository relacionamentOrdersProductRepository;


    @Transactional
    public Orders create(OrdersCreateDto dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do pedido é obrigatório.");
        }

        if (dto.getProducts() == null || dto.getProducts().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto.");
        }

        if (dto.getOptionals() == null) {
            throw new IllegalArgumentException("A lista de opcionais não pode ser nula.");
        }

        Orders newOrders = new Orders();
        newOrders.setName(dto.getName());
        String ticket = Utils.generateTicket();
        newOrders.setNumberOrder(ticket);


        List<Product> products = productService.findByIds(
                dto.getProducts().stream()
                        .map(ProductQuantityDto::getId)
                        .collect(Collectors.toList())
        );
        if (products.isEmpty() || products.size() != dto.getProducts().size()) {
            throw new EntityNotFoundException("Produtos não foram encontrados.");
        }


        List<RelacionamentOrdersProduct> relacionamentOrdersProducts = new ArrayList<>();
        for (ProductQuantityDto productDto : dto.getProducts()) {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(productDto.getId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

            int quantity = productDto.getQuantity(); // Quantidade fornecida no DTO
            RelacionamentOrdersProduct relacionament = new RelacionamentOrdersProduct(newOrders, product, quantity);
            relacionamentOrdersProducts.add(relacionament);
        }

        List<Optional> optionals = new ArrayList<>();
        if (!dto.getOptionals().isEmpty()) {
            optionals = optionalService.findByIds(dto.getOptionals());
            if (optionals.size() != dto.getOptionals().size()) {
                throw new EntityNotFoundException("Opcionais não foram encontrados.");
            }
        }

        newOrders.setRelacionamentOrdersProducts(relacionamentOrdersProducts);
        newOrders.setOpcionals(optionals);


        double totalValue = newOrders.calculateTotalValue();
        newOrders.setTotalValue(totalValue);


        newOrders = repository.save(newOrders);
        relacionamentOrdersProductRepository.saveAll(relacionamentOrdersProducts);

        return newOrders;
    }


}
