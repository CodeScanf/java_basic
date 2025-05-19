package tech.insight.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 14:48:09
 */
@Component
@SupportUserType(UserType.BIG)
public class BigRCustomerService implements CustomerService{

    @Override
    public String findCustomer() {
        System.out.println("大R玩家客服");
        return "大R玩家客服";
    }
}
