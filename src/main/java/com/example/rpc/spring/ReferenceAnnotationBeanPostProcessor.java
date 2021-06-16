package com.example.rpc.spring;

import com.example.rpc.annotation.Reference;
import com.example.rpc.consumer.CityServiceWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Don
 * @date 2021/6/15.
 */
public class ReferenceAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanClassLoaderAware {
    public static final String BEAN_NAME = "ReferenceAnnotationBeanPostProcessor";

    private ClassLoader classLoader;

    //进行动态代理
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field: fields) {
            if (null != AnnotationUtils.getAnnotation(field, Reference.class)){
                if (!field.isAccessible()){
                    field.setAccessible(true);
                }
                try{
                    field.set(bean, createProxy(field.getType()));
                }
                catch (Exception ex){
                    //log
                }
            }
        }
        return pvs;
    }

    private Object createProxy(Class<?> injectedType){
        ReferenceBeanInvocationHandler handler = new ReferenceBeanInvocationHandler(new CityServiceWrapper());
        return Proxy.newProxyInstance(classLoader, new Class[]{injectedType}, handler);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public static class ReferenceBeanInvocationHandler implements InvocationHandler {

        //TODO：临时模拟代理
        private CityServiceWrapper cityServiceWrapper;

        public ReferenceBeanInvocationHandler(CityServiceWrapper cityServiceWrapper){
            this.cityServiceWrapper = cityServiceWrapper;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(cityServiceWrapper, args);
        }
    }
}
