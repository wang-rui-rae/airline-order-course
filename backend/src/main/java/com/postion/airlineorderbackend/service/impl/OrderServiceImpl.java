package com.postion.airlineorderbackend.service.impl;

import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.exception.BusinessException;
import com.postion.airlineorderbackend.mapper.OrderMapper;
import com.postion.airlineorderbackend.model.Order;
import com.postion.airlineorderbackend.model.OrderStatus;
import com.postion.airlineorderbackend.repo.OrderRepo;
import com.postion.airlineorderbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public OrderDto payOrder(Long id) {
        // 1.先判断是否有这个订单
        //   > 有订单的话，直接返回Order对象
        //   > 没有订单的话，抛出异常
        Order order = orderRepo.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Order not found"));
        // 2.更新订单状态并持久化到数据库中
        order.setStatus(OrderStatus.PAID);
        //   > 如果数据库异常发生的话，在全局异常中通过DataAccessException进行捕获
        orderRepo.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderDto cancelOrder(Long id) {
        // 1.先判断是否有这个订单
        //   > 有订单的话，直接返回Order对象
        //   > 没有订单的话，抛出异常
        Order order = orderRepo.findById(id).orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Order not found"));
        // 2.更新订单状态并持久化到数据库中
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
        return orderMapper.toDto(order);
    }


    @Override
    public void requestTicketIssuance(Long id) {

    }




}
