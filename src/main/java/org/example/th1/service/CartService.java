package org.example.th1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.th1.dto.CartItemRequest;
import org.example.th1.model.CartItem;
import org.example.th1.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;

    private final AtomicLong idGenerator =
            new AtomicLong(1);

    public CartItem addProduct(CartItemRequest request) {

        CartItem item = cartRepository
                .findByUserIdAndProductId(
                        request.getUserId(),
                        request.getProductId()
                )
                .orElse(null);

        if (item != null) {

            item.setQuantity(
                    item.getQuantity()
                            + request.getQuantity()
            );

            log.info(
                    "Cộng dồn sản phẩm {} cho user {}",
                    request.getProductId(),
                    request.getUserId()
            );

            return item;
        }

        CartItem newItem = new CartItem(
                idGenerator.getAndIncrement(),
                request.getUserId(),
                request.getProductId(),
                request.getQuantity()
        );

        cartRepository.save(newItem);

        log.info(
                "Thêm mới sản phẩm {} cho user {}",
                request.getProductId(),
                request.getUserId()
        );

        return newItem;
    }

    public List<CartItem> getCart(String userId) {

        log.info(
                "Lấy giỏ hàng của user {}",
                userId
        );

        return cartRepository.findByUserId(userId);
    }
}