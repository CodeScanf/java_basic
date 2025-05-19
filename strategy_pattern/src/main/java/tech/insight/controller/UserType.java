package tech.insight.controller;

import java.util.function.IntPredicate;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/19 15:37:44
 */
public enum UserType {
    NORMAL(recharge -> recharge > 0 && recharge <= 100),
    SMALL(recharge -> recharge > 100 && recharge <= 1000),
    BIG(recharge -> recharge > 1000 && recharge <= 10000),
    SUPERR(recharge -> recharge > 10000 && recharge <= 100000),
    PERSONAL(recharge -> recharge > 100000);

    private final IntPredicate support;

    UserType(IntPredicate support) {
        this.support = support;
    }

    public static UserType typeOf(int recharge) {
        for (UserType value : UserType.values()) {
            if (value.support.test(recharge)) {
                return value;
            }
        }
        return null;
    }
}
