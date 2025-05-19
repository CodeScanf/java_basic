package tech.insight.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 14:44:02
 */
@Component
@SupportUserType(UserType.NORMAL)
public class NormalCustomerService implements CustomerService{

    @Override
    public String findCustomer() {
        System.out.println("普通玩家客服");
        return "普通玩家客服";
    }
}
