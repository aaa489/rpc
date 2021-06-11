package com.example.rpc.annotation;

import com.example.rpc.spring.RpcBeanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用rpc框架
 * @author Don
 * @date 2021/6/11.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RpcBeanRegistrar.class)
public @interface EnableRpc {
     /**
       * 扫描的包，默认当前注解对应的类的包下所有
       **/
    String[] basePackages() default {};
}
