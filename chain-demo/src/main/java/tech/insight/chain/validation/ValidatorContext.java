package tech.insight.chain.validation;

import tech.insight.chain.exception.ValidatorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ValidatorContext
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 15:14
 * @Version: 1.0
 **/
public class ValidatorContext {
    private final List<String> errorMessageList = new ArrayList<>();

    private boolean stop = false;

    private int index = 0;

    private Object value;

    private Map<String,Object> data = new HashMap<>();

    public ValidatorContext(Object value){
        this.value = value;
    }

    public void appendError(String message) {
        errorMessageList.add(message);
    }

    public void stopChain() {
        this.stop = true;
    }

    public boolean shouldStop() {
        return this.stop;
    }

    public Object getValue() {
        return value;
    }

    public void doNext(Object value) {
        this.value = value;
        index++;
    }

    public int currentIndex(){
        return index;
    }

    public void put(String key, Object value){
        this.data.put(key,value);
    }

    public Object get(String key){
        return this.data.get(key);
    }

    public void throwExceptionIfNecessary() throws ValidatorException {
        if (errorMessageList.isEmpty()) {
            return;
        }
        throw new ValidatorException(errorMessageList.toString());
    }
}
