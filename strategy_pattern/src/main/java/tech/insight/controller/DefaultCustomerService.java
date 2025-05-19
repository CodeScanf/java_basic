package tech.insight.controller;

import org.springframework.stereotype.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 15:31:00
 */
@Component
public class DefaultCustomerService implements CustomerService{
    @Override
    public String findCustomer() {
        return "找不到客服";
    }
}
