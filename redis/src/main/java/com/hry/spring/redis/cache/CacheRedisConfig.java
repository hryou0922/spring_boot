package com.hry.spring.redis.cache;

/**
 * spring 通过 CacheRedisConfig 配置redis
 */
//@Configuration  
//@EnableCaching  
//public class CacheRedisConfig extends CachingConfigurerSupport{  
//  
//    @Bean  
//    public KeyGenerator wiselyKeyGenerator(){  
//        return new KeyGenerator() {  
//            @Override  
//            public Object generate(Object target, Method method, Object... params) {  
//                StringBuilder sb = new StringBuilder();  
//                sb.append(target.getClass().getName());  
//                sb.append(method.getName());  
//                for (Object obj : params) {  
//                    sb.append(obj.toString());  
//                }  
//                return sb.toString();  
//            }  
//        };  
//  
//    }  
//  
//    @Bean  
//    public CacheManager cacheManager(  
//            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {  
//        return new RedisCacheManager(redisTemplate);  
//    }  
//  
//    @Bean  
//    public RedisTemplate<String, String> redisTemplate(  
//            RedisConnectionFactory factory) {  
//        StringRedisTemplate template = new StringRedisTemplate(factory);  
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
////        ObjectMapper om = new ObjectMapper();  
////        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
////        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
////        jackson2JsonRedisSerializer.setObjectMapper(om);  
////        template.setValueSerializer(jackson2JsonRedisSerializer);  
//        template.afterPropertiesSet();  
//        return template;  
//    }  
//}  
