package com.yakovchuk;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {

    private String pathToClasses;

    public CustomClassLoader(ClassLoader parent, String pathToClasses) {
        super(parent);
        this.pathToClasses = pathToClasses;
    }

    public CustomClassLoader(String pathToClasses) {
        this(CustomClassLoader.class.getClassLoader(), pathToClasses);
    }

    @Override
    protected URL findResource(String name) {
        return super.findResource(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Class clazz = null;

        try {
            clazz = getParent().loadClass(name);
        } catch (Exception e) {
            System.out.println("Class not loaded by parent classloader: " + name);
        }

        if (clazz == null) {
            String fileStub = name.replace('.', '/');
            String classFilename = fileStub + ".class";
            try {
                byte raw[] = Files.readAllBytes(Paths.get(pathToClasses + classFilename));
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            if (resolve && clazz != null)
                resolveClass(clazz);
        }

        if (clazz == null)
            throw new ClassNotFoundException(name);

        return clazz;
    }
}