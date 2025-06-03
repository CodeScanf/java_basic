package tech.insight.chain.validation;


/**
 * @ClassName: MaxValidatorHandler
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:40
 * @Version: 1.0
 **/
public class MaxValidatorHandler implements ValidatorHandler {

    private final int max;

    public MaxValidatorHandler(int max) {
        this.max = max;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        if (value instanceof Integer intValue) {
            if (intValue > max){
                context.appendError("你的值是" + value + "不能大于" + max);
                context.stopChain();
            }
        }
        context.put("name","xiaoli");
        context.doNext(value);
    }
}
