package com.nightingale;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
//	@Bean
//	public CacheManager cacheManager() {
//		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("nightingale-cache");
//		return cacheManager;
//	}
	
	@Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass()
                           .getName());
                sb.append(method.getName());
                for (Object param : params) {
                    if (param != null)
                        sb.append(param.toString());
                }
                return sb.toString();
            }
        };

    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
//
//        RedisCacheManager cM = new RedisCacheManager(template);
//        cM.setUsePrefix(true);
//        cM.setDefaultExpiration(600);
//        return cM;
//    }

	// @Bean
	// JedisConnectionFactory jedisConnectionFactory() {
	// return new JedisConnectionFactory();
	// }
	//
	// @Bean
	// RedisTemplate<String, Object> redisTemplate() {
	// final RedisTemplate<String, Object> template = new RedisTemplate<String,
	// Object>();
	// template.setConnectionFactory(jedisConnectionFactory());
	// template.setKeySerializer(new StringRedisSerializer());
	// template.setHashValueSerializer(new
	// GenericToStringSerializer<Object>(Object.class));
	// template.setValueSerializer(new
	// GenericToStringSerializer<Object>(Object.class));
	// return template;
	// }

}
