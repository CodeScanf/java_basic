package tech.insight.code;

/**
 * @ClassName: demo
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 15:26
 * @Version: 1.0
 **/
public class Demo {
    public static void main(String[] args) {
        System.out.println(2);
        System.out.println(max(3,1));
    }

    public static int max(int a, int b){
        if (a > b){
            return a;
        }
        return b;
    }
}
