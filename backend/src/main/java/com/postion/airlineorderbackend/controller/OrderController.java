package com.postion.airlineorderbackend.controller;

import com.postion.airlineorderbackend.dto.ApiResponse;
import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name="订单管理",description = "提供订单相关的增删改查接口")
public class OrderController {

    private final OrderService orderService;

    /**
     * get order info
     * url : [localhost:8080/api/orders]
     * @return a list of DTOs
     */
    @GetMapping
    @Operation(summary = "获取全部订单信息", description = "这个接口用于查询全部订单的详细信息")
    public ResponseEntity<ApiResponse<Object>> getAllOrders(){
        return ResponseEntity.ok(ApiResponse.success(orderService.getAllOrders()));

    }

    /**
     * get a single order info by id
     * url : [localhost:8080/api/orders/????]
     * @param id
     * @return a single order info
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID获取订单信息", description = "这个接口用于查询单个订单的详细信息")
    @Parameter(name = "id",description = "主键",required = true)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "成功")
    public ResponseEntity<ApiResponse<Object>> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrderById(id)));
    }

    // TODO
    @PostMapping("/create")
    public String insertOrder(){
        return "";
    }

}
