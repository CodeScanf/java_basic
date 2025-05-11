package tech.insight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lijiaobin
 * @version 1.0
 * @project Default (Template) Project
 * @description ${description}
 * @date 2025/5/11 15:11:52
 */
public class Main {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("lijiaobin", 18));
        users.add(new User("zhangsan", 18));
        users.add(new User("lisi", 20));
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("*********************************************");
        User Tom =  new User("Tom", 18);
        Iterator<String> iterator = Tom.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("*********************************************");

        Iterator<User> userIterator = users.iterator();
        while (userIterator.hasNext()){
            User next = userIterator.next();
            if (next.getAge() == 18) {
                users.add(new User("wangwu", 18));
                break;
            }
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

}