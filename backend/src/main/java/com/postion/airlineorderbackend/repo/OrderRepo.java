package com.postion.airlineorderbackend.repo;

import com.postion.airlineorderbackend.model.Order;
import com.postion.airlineorderbackend.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Long> {

     // get all orders info
     List<Order> findAll();

     // get order info by id
     Optional<Order> findById(long id);

     // 新增方法，用于查找特定状态和创建时间早于某个时间的订单
     List<Order> findByStatusAndCreationDateBefore(OrderStatus status, LocalDateTime creationDate);
}
