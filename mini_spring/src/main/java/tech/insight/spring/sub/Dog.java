package tech.insight.spring.sub;

import tech.insight.spring.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/26 23:40:30
 */
@Component(name = "mydog")
public class Dog {

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;

    @PostConstruct
    public void init() {
        System.out.println("dog 创建完成 里面有一只猫" + cat);
        System.out.println("dog 创建完成 里面有一只狗" + dog);
    }
}
