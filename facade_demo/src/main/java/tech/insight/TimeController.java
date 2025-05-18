package tech.insight;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.error.MarkedYAMLException;
import tech.insight.plugin.MyPlugin;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/17 21:26:20
 */
@RestController
public class TimeController {

    private MyPlugin myPlugin;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/time")
    public String getTime() {
        if(myPlugin != null) {
            myPlugin.beforeGetTime();
        }
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    //  实现了我们插件的jar包。必须必须拥有一个文件，这个文件名叫 shengsheng.plugin 这个文件的内容就是实现 myPlugin 的全类名
    @GetMapping("/loadPlugin/{path}")
    public String loadPlugin(@PathVariable("path") String path) {
        //  facade_demo.count_plugin-1.0-SNAPSHOT.jar
        path = "facade_demo" + File.separator + path;
        File file = new File(path);
        try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toPath().toUri().toURL()});
             InputStream shengshengFile = urlClassLoader.getResourceAsStream("shengsheng.plugin");){
            String myPluginFullClassName = new String(shengshengFile.readAllBytes());
            Class<?> aClass = urlClassLoader.loadClass(myPluginFullClassName.trim());
            Constructor<?> constructor = aClass.getConstructor();
            myPlugin = (MyPlugin)constructor.newInstance();
            return "加载成功" + aClass.getName();
        }catch (Exception e){
            return "加载失败";
        }
    }
}
