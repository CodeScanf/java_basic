package tech.insight.expression;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/17 14:53:49
 */
public class Main {
    public static void main(String[] args) {
        // 1+15*(9+4(1+5))+6
        ExpressionParser expressionParser = new ExpressionParser("1+15*(9+4+(1+5))+6");
        Expression parse = expressionParser.parse();
        int a = 1+15*(9+4+(1+5))+6;
        System.out.println(a);
        System.out.println(parse.getValue());
    }
}
