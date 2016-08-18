package com.yakovchuk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AnimalClassloader extends ClassLoader {

    public AnimalClassloader(ClassLoader parent) {
        super(parent);
    }

    public AnimalClassloader() {
        this(AnimalClassloader.class.getClassLoader());
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes;
        InputStream is = getClass().getClassLoader().getResourceAsStream(name.replace(".", "/") + " .class");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int c = 0;
        try {
            while((c = is.read()) != -1){
                stream.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = stream.toByteArray();
        return defineClass(name, bytes, 0, bytes.length);
    }
}
