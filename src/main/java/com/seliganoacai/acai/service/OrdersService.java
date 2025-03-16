package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.entity.RelacionamentOrdersProduct;
import com.seliganoacai.acai.repository.OrdersRepository;
import com.seliganoacai.acai.repository.RelacionamentOrdersProductRepository;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
        // Valida o DTO
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do pedido é obrigatório.");
        }

        // Valida a quantidade de produtos (deve ser exatamente 1)
        if (dto.getProducts() == null || dto.getProducts().size() != 1) {
            throw new IllegalArgumentException("O pedido deve conter exatamente um produto.");
        }

        // Valida a quantidade de opcionais (pode ser 0 ou mais)
        if (dto.getOptionals() == null) {
            throw new IllegalArgumentException("A lista de opcionais não pode ser nula.");
        }

        // Cria o pedido
        Orders newOrders = new Orders();
        newOrders.setName(dto.getName());

        // Busca o produto no banco de dados
        List<Product> products = productService.findByIds(dto.getProducts());
        if (products.isEmpty() || products.size() != 1) {
            throw new EntityNotFoundException("O produto selecionado não foi encontrado.");
        }
        Product product = products.get(0); // Pega o único produto da lista

        // Busca os opcionais no banco de dados (apenas se a lista não estiver vazia)
        List<Optional> optionals = new ArrayList<>();
        if (!dto.getOptionals().isEmpty()) {
            optionals = optionalService.findByIds(dto.getOptionals());
            if (optionals.size() != dto.getOptionals().size()) {
                throw new EntityNotFoundException("Um ou mais opcionais não foram encontrados.");
            }
        }

        // Cria o relacionamento entre pedido e produto
        List<RelacionamentOrdersProduct> relacionamentOrdersProducts = new ArrayList<>();
        int quantity = 1; // Quantidade padrão, pode ser ajustada conforme o DTO
        RelacionamentOrdersProduct relacionament = new RelacionamentOrdersProduct(newOrders, product, quantity);
        relacionamentOrdersProducts.add(relacionament);

        // Associa o produto e os opcionais ao pedido
        newOrders.setRelacionamentOrdersProducts(relacionamentOrdersProducts);
        newOrders.setOpcionals(optionals);

        // Calcula o valor total do pedido
        double totalValue = newOrders.calculateTotalValue();
        newOrders.setTotalValue(totalValue);

        // Salva o pedido e os relacionamentos
        newOrders = repository.save(newOrders); // Salva o pedido e os relacionamentos (cascade)
        relacionamentOrdersProductRepository.saveAll(relacionamentOrdersProducts);

        return newOrders;
    }
}
