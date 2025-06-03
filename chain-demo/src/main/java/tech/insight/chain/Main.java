package tech.insight.chain;

import tech.insight.chain.validation.Validator;

/**
 * @ClassName: Main
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 11:44
 * @Version: 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        User tom = new User("tom", 18);
        Validator validator = new Validator();
        validator.validate(tom);
    }
}
