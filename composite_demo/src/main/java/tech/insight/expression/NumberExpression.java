package tech.insight.expression;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/17 14:06:20
 */
public class NumberExpression implements Expression {

    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
