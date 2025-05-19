package tech.insight.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 15:23:36
 */
@Component
@SupportUserType(UserType.PERSONAL)
public class PersonalCustomerService implements CustomerService{



    @Override
    public String findCustomer() {
        System.out.println("专属客服");
        return "专属客服";
    }
}
