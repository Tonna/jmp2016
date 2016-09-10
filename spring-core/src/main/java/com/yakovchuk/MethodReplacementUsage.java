package com.yakovchuk;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class MethodReplacementUsage implements MethodReplacer{

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        return 42;
    }
}
