package com.postion.airlineorderbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    // 测试controller方式
    // step1:项目启动成功后，会在控制台生成 > Using generated security password: a164fd38-42fd-49d5-899d-613eb25fed30
    // step2:浏览器访问 > http://localhost:8080/api/test
    // step3:浏览器显示OrderController.getAllOrders()就成功了
    @GetMapping
    public String getAllOrders(){
        return "OrderController.getAllOrders()";
    }
}
