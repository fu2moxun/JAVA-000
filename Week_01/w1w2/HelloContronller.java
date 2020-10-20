package com.example.geektime.classloaderw1.hello;

import com.example.geektime.classloaderw1.classloader.HelloClassLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * @author zhangjianxun@meituan.com
 * @date 2020-10-21 00:13
 */
@RestController
public class HelloContronller {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring!";
    }

    @RequestMapping("/test/loader")
    public String testLoader() {
        try {
            HelloClassLoader myClassLoader = new HelloClassLoader("Hello.xlass");
            Class<?> clazz = myClassLoader.loadClass("Hello");
            Method method = clazz.getMethod("hello");
            Object obj = clazz.newInstance();
            method.invoke(obj);
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
            return "error helloClassloader";
        }
        return "success";
    }

}
