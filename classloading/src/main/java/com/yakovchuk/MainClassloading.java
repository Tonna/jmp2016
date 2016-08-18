package com.yakovchuk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainClassloading {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AnimalClassloader classloader = new AnimalClassloader();
        Class<?> catClass = classloader.loadClass("D:\\home\\yakovcha\\working\\jmp\\sources\\entity\\target\\entity.jar");
        //Class<?> catClass = classloader.loadClass("com.yakovchuk.Cat");
        Object instance = catClass.newInstance();
        Method method = catClass.getMethod("voice");
        System.out.println(method.invoke(instance));
        System.out.println(catClass.getClassLoader().getClass());
    }
}
