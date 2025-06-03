package tech.insight.chain.validation;

import tech.insight.chain.annotation.Length;
import tech.insight.chain.annotation.Max;
import tech.insight.chain.annotation.Min;
import tech.insight.chain.exception.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @ClassName: Validator
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:31
 * @Version: 1.0
 **/
public class Validator {

    public void validate(Object bean) throws Exception {
        Class<?> aClass = bean.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            ValidatorChain chain = bulidValidateChain(field);
            chain.validate(field.get(bean));
        }
    }

    private ValidatorChain bulidValidateChain(Field field) {
        ValidatorChain chain = new ValidatorChain();
        Max max = field.getAnnotation(Max.class);
        if (max != null){
            chain.addLastHandler(new MaxValidatorHandler(max.value()));
        }
        Min min = field.getAnnotation(Min.class);
        if (min != null){
            chain.addLastHandler(new MinValidatorHandler(min.value()));
        }
        Length length = field.getAnnotation(Length.class);
        if (length != null){
            chain.addLastHandler(new LengthValidatorHandler(length .value()));
        }
        return chain;
    }
}
