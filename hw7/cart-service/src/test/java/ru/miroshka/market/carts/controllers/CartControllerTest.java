package ru.miroshka.market.carts.controllers;

import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.miroshka.market.api.dto.ProductDto;
import ru.miroshka.market.api.models.CartDto;
import ru.miroshka.market.carts.converters.CartConverter;
import ru.miroshka.market.carts.integrations.ProductServiceIntegration;
import ru.miroshka.market.carts.models.Cart;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
class CartControllerTest {


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CartConverter cartConverter;

    @Autowired
    Cart cart;

    @Autowired
    ProductServiceIntegration productServiceIntegration;

    @Test
    void getCurrentCart() {

        //т.к. микросервис, то запускаем отдельно core-service - там есть несколько продуктов
        //1) берем из продукт и ложим в корзину
        //2) запрашиваем  корзину
        //3) сравниваем нашу корзину после добавления туда продукта и запрошенную корзину


        CartDto responseDtoInput = webTestClient.get()
                .uri("/api/v1/cart/add/1")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;


        CartDto responseDto = webTestClient.get()
                .uri("/api/v1/cart")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
        ;

        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDtoInput);
        Assertions.assertNotNull(responseDto.getItems());
        Assertions.assertNotNull(responseDtoInput.getItems());
        Assertions.assertEquals(responseDto.getClass(),responseDtoInput.getClass());

        for (int i = 0; i < responseDtoInput.getItems().size(); i++) {
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPrice(),
                    responseDto.getItems().get(i).getPrice());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductId(),
                    responseDto.getItems().get(i).getProductId());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getQuantity(),
                    responseDto.getItems().get(i).getQuantity());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductTitle(),
                    responseDto.getItems().get(i).getProductTitle());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPricePerProduct(),
                    responseDto.getItems().get(i).getPricePerProduct());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getClass(),
                    responseDto.getItems().get(i).getClass());

        }


        //   Assertions.assertEquals(cartConverter.entityToDto(cart).getTotalPrice(),responseDto.getTotalPrice());
    }

    @Test
    void putProductToCast() {
        //т.к. микросервис, то запускаем отдельно core-service - там есть несколько продуктов
        //1) берем из продукт и ложим в корзину
        //2) запрашиваем  корзину
        //3) сравниваем нашу корзину после добавления туда продукта и запрошенную корзину


        CartDto responseDtoInput = webTestClient.get()
                .uri("/api/v1/cart/add/1")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;


        CartDto responseDto = webTestClient.get()
                .uri("/api/v1/cart")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;

        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDtoInput);
        Assertions.assertNotNull(responseDto.getItems());
        Assertions.assertNotNull(responseDtoInput.getItems());
        Assertions.assertEquals(responseDto.getClass(),responseDtoInput.getClass());

        for (int i = 0; i < responseDtoInput.getItems().size(); i++) {
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPrice(),
                    responseDto.getItems().get(i).getPrice());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductId(),
                    responseDto.getItems().get(i).getProductId());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getQuantity(),
                    responseDto.getItems().get(i).getQuantity());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductTitle(),
                    responseDto.getItems().get(i).getProductTitle());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPricePerProduct(),
                    responseDto.getItems().get(i).getPricePerProduct());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getClass(),
                    responseDto.getItems().get(i).getClass());

        }


    }

    @Test
    void deleteProductBasket() {

        webTestClient.get()
                .uri("/api/v1/cart/add/1")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;

        CartDto responseDtoInput = webTestClient.get()
                .uri("/api/v1/cart/add/2")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;



        webTestClient.get()
                .uri("/api/v1/cart/delete/2")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;

        CartDto responseDto = webTestClient.get()
                .uri("/api/v1/cart")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;


        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDtoInput);
        Assertions.assertNotNull(responseDto.getItems());
        Assertions.assertNotNull(responseDtoInput.getItems());
        Assertions.assertEquals(responseDto.getClass(),responseDtoInput.getClass());
        Assertions.assertEquals(responseDtoInput.getItems().size(),2);
        Assertions.assertEquals(responseDto.getItems().size(),1);

        int i = 0;

            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPrice(),
                    responseDto.getItems().get(i).getPrice());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductId(),
                    responseDto.getItems().get(i).getProductId());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getQuantity(),
                    responseDto.getItems().get(i).getQuantity());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getProductTitle(),
                    responseDto.getItems().get(i).getProductTitle());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getPricePerProduct(),
                    responseDto.getItems().get(i).getPricePerProduct());
            Assertions.assertEquals(responseDtoInput.getItems().get(i).getClass(),
                    responseDto.getItems().get(i).getClass());



    }

    @Test
    void deleteAllProductBasket() {
        webTestClient.get()
                .uri("/api/v1/cart/add/1")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
        ;

        CartDto responseDtoInput = webTestClient.get()
                .uri("/api/v1/cart/add/2")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;



        webTestClient.get()
                .uri("/api/v1/cart/delete")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
        ;

        CartDto responseDto = webTestClient.get()
                .uri("/api/v1/cart")
                .exchange()
                //.expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody()
                ;

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(responseDto.getItems().size(),0);
    }

    @Test
    void testPutProductToCast() {
    }
}