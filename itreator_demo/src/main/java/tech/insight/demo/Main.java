package tech.insight.demo;

import tech.insight.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/11 22:24:58
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        readUsers((user) ->{
            System.out.println(user);
            users.add(user);
        });
        System.out.println(users.size());
    }

    private static void readUsers(Consumer<User> userConsumer) throws IOException {
        List<String> lines = Files.readAllLines(new File("itreator_demo/demo.user").toPath());
        for (String line : lines) {
            String substring = line.substring(1, line.length() - 1);
            String[] split = substring.split(",");
            String name = split[0];
            int age = Integer.parseInt(split[1]);
            User user = new User(name, age);
            userConsumer.accept(user);
        }
    }
}
