package com.seliganoacai.acai.service;

import com.seliganoacai.acai.entity.Optional;
import com.seliganoacai.acai.entity.Orders;
import com.seliganoacai.acai.entity.Product;
import com.seliganoacai.acai.entity.RelationsOrdersProduct;
import com.seliganoacai.acai.repository.OrdersRepository;
import com.seliganoacai.acai.repository.RelationsOrdersProductRepository;
import com.seliganoacai.acai.utils.Utils;
import com.seliganoacai.acai.webConfig.dto.createDto.OrdersCreateDto;
import com.seliganoacai.acai.webConfig.dto.createDto.RelationsOrderProductCreateDto;
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
    private RelationsOrdersProductRepository relationsOrdersProductRepository;


    @Transactional
    public Orders create(OrdersCreateDto dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("O seu nome é  obrigatório.");
        }

        if (dto.getRelationsOrderProductCreateDtos() == null || dto.getRelationsOrderProductCreateDtos().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto.");
        }

        if (dto.getOptionals() == null) {
            throw new IllegalArgumentException("A lista de opcionais não pode estar em branco.");
        }

        Orders newOrders = new Orders();
        newOrders.setName(dto.getName());
        String ticket = Utils.generateTicket();
        newOrders.setNumberOrder(ticket);


        List<Product> products = productService.findByIds(
                dto.getRelationsOrderProductCreateDtos().stream()
                        .map(RelationsOrderProductCreateDto::getId)
                        .collect(Collectors.toList())
        );
        if (products.isEmpty() || products.size() != dto.getRelationsOrderProductCreateDtos().size()) {
            throw new EntityNotFoundException("Produtos não foram encontrados.");
        }


        List<RelationsOrdersProduct> relationsOrdersProducts = new ArrayList<>();
        for (RelationsOrderProductCreateDto relationsDto : dto.getRelationsOrderProductCreateDtos()) {

            Product product = products.stream()
                    .filter(filter -> filter.getId().equals(relationsDto.getId()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

            int quantityProduct = relationsDto.getQuantity(); // Quantidade fornecida no DTO

            RelationsOrdersProduct relacionament = new RelationsOrdersProduct(newOrders, product, quantityProduct);
            relationsOrdersProducts.add(relacionament);
        }

        List<Optional> optionals = new ArrayList<>();
        if (!dto.getOptionals().isEmpty()) {
            optionals = optionalService.findByIds(dto.getOptionals());
            if (optionals.size() != dto.getOptionals().size()) {
                throw new EntityNotFoundException("Opcionais não foram encontrados.");
            }
        }

        newOrders.setRelationsOrdersProducts(relationsOrdersProducts);
        newOrders.setOpcionals(optionals);


        double totalValue = newOrders.calculateTotalValue();
        newOrders.setTotalValue(totalValue);


        newOrders = repository.save(newOrders);
        relationsOrdersProductRepository.saveAll(relationsOrdersProducts);

        return newOrders;
    }


}
