package tech.insight.expression;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/17 14:08:06
 */
public abstract class BinaryOperationExpression implements Expression{

    Expression left;
    Expression right;

    public BinaryOperationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
