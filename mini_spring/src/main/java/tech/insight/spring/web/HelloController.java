package tech.insight.spring.web;

import tech.insight.spring.Component;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/28 21:45:42
 */
@Controller
@RequestMapping("/hello")
@Component
public class HelloController {

    //localhost:port/hello/a
    @RequestMapping("/a")
    public String hello(@Param("name") String name, @Param("age") Integer age){
        return String.format("<h1> hello world</h1><br> name: %s  age: %s", name, age);
    }

    @RequestMapping("/json")
    @ResponseBody
    public User josn(@Param("name") String name, @Param("age") Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }

    @RequestMapping("/html")
    public ModelAndView html(@Param("name") String name, @Param("age") Integer age){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView("index.html");
        return modelAndView;
    }

}
