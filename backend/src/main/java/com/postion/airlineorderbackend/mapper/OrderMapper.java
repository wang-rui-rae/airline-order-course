package com.postion.airlineorderbackend.mapper;

import com.postion.airlineorderbackend.dto.OrderDto;
import com.postion.airlineorderbackend.model.Order;
import com.postion.airlineorderbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//它告诉 MapStruct 生成一个 Spring Bean，这样你就可以在其他 Service 或 Controller 中通过 @Autowired 注入它。
// Generates a Spring Bean for injection
@Mapper(componentModel = "spring")
public interface OrderMapper {

    //    @Mapping: 用于定义字段之间的映射规则。
    //    source: 源对象（Entity）的属性名。
    //    target: 目标对象（DTO）的属性名。
    // MapStruct can map nested objects automatically if they have a corresponding mapping method
//    @Mappings({
//            @Mapping(source = "Order", target = "OrderDto"), // Map the user object
//            @Mapping(target = "flightInfo", ignore = true) // flightInfo is added later, not from the entity
//    })
    @Mappings({})
    OrderDto toDto(Order order);

    // This method will be used by the above toDto method to map the User entity to UserDto
    OrderDto.UserDto userToUserDto(User user);
}
