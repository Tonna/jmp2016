package com.yakovchuk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClassloading {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        CustomClassLoader classloader = new CustomClassLoader(args[0]);

        Class<?> animalClass = classloader.loadClass("com.yakovchuk.Animal");
        Class<?> catClass = classloader.loadClass("com.yakovchuk.Cat");
        Class<?> dogClass = classloader.loadClass("com.yakovchuk.Dog");

        List<Object> animals = new ArrayList<Object>();

        for(int i = 0; i < 2; i++) {
            animals.add(catClass.newInstance());
            animals.add(dogClass.newInstance());
        }
        Method methodVoice = animalClass.getMethod("voice");
        Method methodPlay = animalClass.getMethod("play");

        Collections.shuffle(animals);
        for (Object animal : animals) {
            System.out.println(methodPlay.invoke(animal));
            System.out.println(methodVoice.invoke(animal));
        }

        System.out.println(catClass.getClassLoader().getClass());
    }
}
