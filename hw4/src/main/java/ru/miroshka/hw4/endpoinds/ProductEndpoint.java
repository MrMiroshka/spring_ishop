package ru.miroshka.hw4.endpoinds;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.miroshka.hw4.converters.ProductConverter;
import ru.miroshka.hw4.dto.ProductDto;
import ru.miroshka.hw4.servicies.ProductService;
import ru.miroshka.hw4.soap.products.GetAllProductsRequest;
import ru.miroshka.hw4.soap.products.GetAllProductsResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.miroshka.ru/hw4/products";
    private final ProductService productService;
    private final ProductConverter productConverter;


    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().stream().map(p->productConverter.entityToDto(p)).collect(Collectors.toList()).forEach(response.getProducts()::add);
        return response;
    }
}
