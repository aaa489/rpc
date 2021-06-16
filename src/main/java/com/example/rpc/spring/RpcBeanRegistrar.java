package com.example.rpc.spring;

import com.example.rpc.annotation.EnableRpc;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Don
 * @date 2021/6/11.
 */
public class RpcBeanRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(EnableRpc.class.getName()));
        String[] basePackages = attributes.getStringArray("basePackages");
        if (basePackages.length == 0){
            basePackages = new String[]{ClassUtils.getPackageName(metadata.getClassName())};
        }

        registerServiceBean(basePackages, registry);
        registerReferenceBean(registry);
    }

    private void registerServiceBean(String[] basePackages, BeanDefinitionRegistry registry){
        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ServiceAnnotationBeanPostProcessor.class);
        builder.addConstructorArgValue(packagesToScan);
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
    }

    private void registerReferenceBean(BeanDefinitionRegistry registry){
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(ReferenceAnnotationBeanPostProcessor.class);
        rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(ReferenceAnnotationBeanPostProcessor.BEAN_NAME, rootBeanDefinition);

    }
}
