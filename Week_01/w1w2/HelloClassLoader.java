package com.example.geektime.classloaderw1.classloader;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @author zhangjianxun@meituan.com
 * @date 2020-10-21 00:34
 */
public class HelloClassLoader extends ClassLoader {
    private String path;

    public HelloClassLoader(String path) {
        this.path = path;
    }

    /**
     * 重写findClass方法
     *
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classDate = this.getClassData();
        for (int i = 0; i < classDate.length; i++) {
            classDate[i] = (byte) (255 - classDate[i]);
        }

        return defineClass(name, classDate, 0, classDate.length);
    }

    /**
     * 从文件中加载字节码 使用文件流的方式
     */
    private byte[] getClassData() {
        ClassPathResource classPathResource = new ClassPathResource(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            InputStream in = classPathResource.getInputStream();
            byte[] buffer = new byte[1024];
            int size = 0;
            while (((size = in.read(buffer)) != -1)) {
                out.write(buffer, 0, size);
            }
        } catch (IOException ioe) {
            System.out.println("error " + ioe.getMessage());
        }
        return out.toByteArray();
    }

}
