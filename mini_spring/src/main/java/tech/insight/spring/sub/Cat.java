package tech.insight.spring.sub;

import tech.insight.spring.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/26 22:21:16
 */
@Component
public class Cat {
    @Autowired
    private Dog dog;

    @PostConstruct
    public void init() {
        System.out.println("cat init cat里面有一个属性" + dog);
    }

}
