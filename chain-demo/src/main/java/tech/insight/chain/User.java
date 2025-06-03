package tech.insight.chain;

import tech.insight.chain.annotation.Length;
import tech.insight.chain.annotation.Max;
import tech.insight.chain.annotation.Min;

/**
 * @ClassName: User
 * @Description: TODO
 * @Auth: lxiaobin@gmail
 * @Date: 2025/6/3 10:25
 * @Version: 1.0
 **/
public class User {
    @Length(3)
    private final String name;

    @Max(17)
    @Min(15)
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
