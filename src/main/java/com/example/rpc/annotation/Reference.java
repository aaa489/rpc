package com.example.rpc.annotation;

import java.lang.annotation.*;

/**
 * 消费者注解
 * @author Don
 * @date 2021/6/11.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Reference {
}
