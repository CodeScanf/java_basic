package tech.insight.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 14:48:55
 */
@Component
@SupportUserType(UserType.SMALL)
public class SmallRCustomerService implements CustomerService{

    @Override
    public String findCustomer() {
        System.out.println("小R玩家客服");
        return "小R玩家客服";
    }
}
