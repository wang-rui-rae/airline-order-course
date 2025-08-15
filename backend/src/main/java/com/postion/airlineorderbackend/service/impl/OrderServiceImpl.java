package com.postion.airlineorderbackend.service.impl;

import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.exception.BusinessException;
import com.postion.airlineorderbackend.mapper.OrderMapper;
import com.postion.airlineorderbackend.model.Order;
import com.postion.airlineorderbackend.repo.OrderRepo;
import com.postion.airlineorderbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final OrderMapper orderMapper;

    /**
     * get a list of orders
     * @return a list of orders
     */
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepo.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        if(!orderList.isEmpty()){
            for (Order order : orderList) {
                System.out.println(order);
            orderDtoList.add(orderMapper.toDto(order));
            }
        }
        // 如果有的话，就返回全部的order信息
        // 如果没有的话，就返回一个空list
        return orderDtoList;
    }


    /**
     * get a single order
     * @param id primary key
     * @return exist > return Dto;not exist > throw exception
     */
    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepo.findById(id).map(orderMapper::toDto)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @Override
    public OrderDto payOrder(Long id) {
        return null;
    }

    @Override
    public void requestTicketIssuance(Long id) {

    }

    @Override
    public OrderDto cancleOrder(Long id) {
        return null;
    }


}
