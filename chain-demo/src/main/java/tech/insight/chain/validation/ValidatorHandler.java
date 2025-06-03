package tech.insight.chain.validation;

import tech.insight.chain.exception.ValidatorException;

/**
 * @ClassName: ValidatorHandler
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:39
 * @Version: 1.0
 **/
public interface ValidatorHandler {
    void validate(Object value, ValidatorContext context);
}
