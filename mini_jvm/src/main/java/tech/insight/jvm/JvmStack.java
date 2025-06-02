package tech.insight.jvm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName: jvmStack
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 16:48
 * @Version: 1.0
 **/
public class JvmStack {

    private Deque<StackFrame> stack = new ArrayDeque<>();

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public StackFrame peek() {
        return stack.peek();
    }

    public StackFrame pop() {
        return stack.pop();
    }

    public void push(StackFrame frame) {
        stack.push(frame);
    }

}
