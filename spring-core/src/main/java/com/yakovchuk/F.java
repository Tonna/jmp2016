package com.yakovchuk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.LifecycleProcessor;

public class F implements LifecycleProcessor, InitializingBean, DisposableBean,
        ApplicationContextAware, BeanNameAware, BeanFactoryAware {

    private static final Log logger = LogFactory.getLog(F.class);

    public F() {
        logger.info("constructor called");
    }

    @Override
    public void onRefresh() {
        logger.info("onRefresh");
    }

    @Override
    public void onClose() {
        logger.info("onClose");
    }

    @Override
    public void start() {
        logger.info("start");
    }

    @Override
    public void stop() {
        logger.info("stop");
    }

    @Override
    public boolean isRunning() {
        logger.info("is running");
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("setApplicationContext");
    }

    @Override
    public void setBeanName(String s) {
        logger.info("setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("setBeanFactory");
    }

    public void customInit() {
        logger.info("custom init called");
    }

    public void customDestroy() {
        logger.info("custom destroy called");
    }

    public void doStuff() {
        logger.info("I'm very important because I implement A LOT of interfaces.");
    }
}
