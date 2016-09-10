package com.yakovchuk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.net.URLClassLoader;

public class SpringCoreMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        System.out.println(context.getBean("beanA"));
        System.out.println(context.getBean("beanB"));

        C beanC = (C) context.getBean("beanC");
        System.out.println(beanC);
        D son = beanC.getSon();
        son.setName("Johny");
        System.out.println(son);
        System.out.println(beanC);

        E beanEStandard = (E) context.getBean("beanEStandard");
        System.out.println("Get random -" + beanEStandard.getRandom());
        E beanEAmended = (E) context.getBean("beanEAmended");
        System.out.println("Get kind of random -" + beanEAmended.getRandom());
    }
}
