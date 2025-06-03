package tech.insight.chain.validation;

import tech.insight.chain.exception.ValidatorException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ValidatorChain
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 11:34
 * @Version: 1.0
 **/
public class ValidatorChain {

    private final List<ValidatorHandler> handlers = new ArrayList<>();

    public void validate(Object value) throws ValidatorException {
        ValidatorContext context = new ValidatorContext(value);
        while (true) {
            int index = context.currentIndex();
             if (handlers.size() == index){
                 break;
             }
            ValidatorHandler handler = handlers.get(index);
             handler.validate(context.getValue(), context);
            if (index == context.currentIndex()) {
                break;
            }
        }
        context.throwExceptionIfNecessary();
    }

    public void addLastHandler(ValidatorHandler handler) {
        this.handlers.add(handler);
    }
}
