package com.seal.common.annotation;

import com.seal.common.ElasticJobAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description 开启tplhk job
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ElasticJobAutoConfiguration.class})
public @interface EnableTplhkJob {
}
