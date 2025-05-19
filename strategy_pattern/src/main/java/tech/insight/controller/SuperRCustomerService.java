package tech.insight.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 14:45:46
 */
@Component
@SupportUserType(UserType.SUPERR)
public class SuperRCustomerService implements CustomerService{


    @Override
    public String findCustomer() {
        System.out.println("超R玩家客服");
        return "超R玩家客服";
    }
}
