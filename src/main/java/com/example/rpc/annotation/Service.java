package com.example.rpc.annotation;

import java.lang.annotation.*;

/**
 * 提供者注解
 * @author Don
 * @date 2021/6/11.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Service {
}
