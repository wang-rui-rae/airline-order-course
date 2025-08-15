package com.postion.airlineorderbackend.service;

import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.model.Order;
import com.postion.airlineorderbackend.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    /**
     * get all orders and convert to DTOs
     * @return a list of converted DTOs
     */
    List<OrderDto> getAllOrders();

    /**
     * get a single order by its unique key and convert to DTO
     * @param id primary key
     * @return a converted DTOs
     */
    OrderDto getOrderById(Long id);

    OrderDto payOrder(Long id);

    OrderDto cancelOrder(Long id);

    // 这是一个异步触发方法
    void requestTicketIssuance(Long id);



}
