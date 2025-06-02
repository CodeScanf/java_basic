package tech.insight.jvm;

import tech.medivh.classpy.classfile.bytecode.Instruction;

import java.util.Iterator;

/**
 * @ClassName: PcRegister
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/2 17:17
 * @Version: 1.0
 **/
public class PcRegister implements Iterable<Instruction> {

    private final JvmStack stack;

    public PcRegister(JvmStack stack) {
        this.stack = stack;
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    class Itr implements Iterator<Instruction> {

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Instruction next() {
            StackFrame topFrame = stack.peek();
            return topFrame.getNextInstruction();
        }
    }
}
