package tech.insight.spring.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.bridge.SLF4JBridgeHandler;
import tech.insight.spring.Component;
import tech.insight.spring.sub.Autowired;
import tech.insight.spring.sub.PostConstruct;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/28 21:32:19
 */
@Component
public class TomcatServer {

    @Autowired
    private DispatcherServlet dispatcherServlet;
    @PostConstruct
    public void star() throws LifecycleException {
        // 重置Java默认的日志管理器配置
        LogManager.getLogManager().reset();
        // 移除根日志记录器上的所有处理器，防止日志重复输出
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        // 安装SLF4J桥接处理器，将JUL日志重定向到SLF4J
        SLF4JBridgeHandler.install();
        int port = 8080;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        tomcat.addServlet(contextPath, "dispatcherServlet", dispatcherServlet);

        context.addServletMappingDecoded("/*", "dispatcherServlet");
        tomcat.start();
        System.out.println("tomcat start ... " +
                "prot = " +
                port);

    }
}
