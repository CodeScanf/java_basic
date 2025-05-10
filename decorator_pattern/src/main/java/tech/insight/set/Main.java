package tech.insight.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 21:48:42
 */
public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HistorySet<>(new HashSet<>());
//        Set<Integer> set = new HistorySet<>(historySet);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.remove(2);
        set.remove(6);
        set.remove(1);
        System.out.println(set);
    }
}
