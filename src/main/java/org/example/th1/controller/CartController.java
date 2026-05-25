package org.example.th1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.th1.dto.CartItemRequest;
import org.example.th1.model.CartItem;
import org.example.th1.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartItem addProduct(
            @Valid
            @RequestBody CartItemRequest request
    ) {

        log.info(
                "Request thêm sản phẩm: userId={}, productId={}, quantity={}",
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );

        return cartService.addProduct(request);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(
            @PathVariable String userId
    ) {

        log.info(
                "Request lấy giỏ hàng user={}",
                userId
        );

        return cartService.getCart(userId);
    }
}