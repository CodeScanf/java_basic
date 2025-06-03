package tech.insight.chain.validation;

import tech.insight.chain.exception.ValidatorException;

/**
 * @ClassName: MaxValidatorHandler
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:40
 * @Version: 1.0
 **/
public class LengthValidatorHandler implements ValidatorHandler {

    private final int length;

    public LengthValidatorHandler(int length) {
        this.length = length;
    }

    @Override
    public void validate(Object value, ValidatorContext context) {
        if (value instanceof String stringValue) {
            if (stringValue.length() != length){
                context.appendError("你的字符串长度是" + stringValue.length() + "应该是" + length);
            }
        }
    }
}
