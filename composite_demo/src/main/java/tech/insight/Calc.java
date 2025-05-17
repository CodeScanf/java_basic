package tech.insight;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description 计算器
 * @date 2025/5/16 18:10:33
 */
public class Calc {
    public static void main(String[] args) {
        //  1 + 2 + 3 + 4
        Exprission oneAndOne = new AddExprission(new NumberExprission(1), new NumberExprission(1));
        Exprission twoAndThree = new AddExprission(new NumberExprission(2), new NumberExprission(3));
        Exprission exprission = new AddExprission(oneAndOne, twoAndThree);
        System.out.println(exprission.getValue());
    }

    interface Exprission {
        int getValue();
    }

    static  class NumberExprission implements Exprission {
        final int value;

        public NumberExprission(int value) {
            this.value = value;
        }
        @Override
        public int getValue() {
            return value;
        }
    }

    static class AddExprission implements Exprission {
        Exprission left;
        Exprission right;

        public AddExprission(Exprission left, Exprission right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int getValue() {
            return left.getValue() + right.getValue();
        }
    }
}
