package tech.insight.chain.validation;


/**
 * @ClassName: MaxValidatorHandler
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:40
 * @Version: 1.0
 **/
public class MinValidatorHandler implements ValidatorHandler {

    private final int min;

    public MinValidatorHandler(int min) {
        this.min = min;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        Object name = context.get("name");
        if (name != null){
            System.out.println("之前有：" + name + "校验过");
        }
        if (value instanceof Integer intValue) {
            if (intValue < min){
                context.appendError("你的值是" + value + "不能小于" + min);
            }
        }
    }
}
