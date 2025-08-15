package com.postion.airlineorderbackend.controller;


import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders/{id}")
@RequiredArgsConstructor
@Tag(name="订单状态管理",description = "提供订单相关的变更接口")
public class OrderActionController {

    private final OrderService orderService;

    @PostMapping("/pay")
    @Operation(summary = "=更新支付状态", description = "这个接口用于更新用户订单状态为支付")
    public ResponseEntity<OrderDto> pay(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.payOrder(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Or return a proper error DTO
        }
    }

    @PostMapping("/cancel")
    @Operation(summary = "=更新取消状态", description = "这个接口用于更新用户订单状态为取消")
    public ResponseEntity<OrderDto> cancel(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.cancelOrder(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // TODO
    @PostMapping("/retry-ticketing")
    public ResponseEntity<Void> retryTicketing(@PathVariable Long id) {
        // 直接调用异步方法，立即返回202 Accepted
        orderService.requestTicketIssuance(id);
        return ResponseEntity.accepted().build();
    }
}
