package tech.insight.expression;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/17 14:10:00
 */
public class SubstractExpression extends BinaryOperationExpression{
    public SubstractExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int getValue() {
        return right.getValue() - left.getValue();
    }
}
