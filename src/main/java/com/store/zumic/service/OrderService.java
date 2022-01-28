package com.store.zumic.service;

import com.store.zumic.dto.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);

}
