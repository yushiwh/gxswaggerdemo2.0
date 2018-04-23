/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: FastJsonConfiguration
 * Author:   yushi
 * Date:     2018/4/23 15:50
 * Description: 增加阿里的FastJson
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jztey.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈增加阿里的FastJson〉
 *
 * @author yushi
 * @create 2018/4/23
 * @since 1.0.0
 */
@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 修改自定义消息转换器
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        /**
         * FastJson SerializerFeatures
         * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * WriteMapNullValue：是否输出值为null的字段,默认为false。
         */
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }
}