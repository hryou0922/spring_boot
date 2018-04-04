package com.hry.spring.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hry.spring.redis.cache.support.Book;

/**
 * @CacheConfig: 类级别的注解
 * 	如果我们在此注解中定义cacheNames，则此类中的所有方法上@Cacheable的cacheNames默认都是此值。当然@Cacheable也可以重定义cacheNames的值
 * @author hry
 *
 */
@Component
@CacheConfig(cacheNames="booksAll") 
public class BookService2 extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(BookService2.class);
	
	/**
	 * 此方法的@Cacheable没有定义cacheNames，则使用类上的注解@CacheConfig里的值 cacheNames
	 * @param id
	 * @return
	 */
	@Cacheable(key="#id")
	public Book queryBookCacheable(String id){
		logger.info("queryBookCacheable,id={}",id);
		return repositoryBook.get(id);
	}

	/**
	 * 此方法的@Cacheable有定义cacheNames，则使用此值覆盖类注解@CacheConfig里的值cacheNames
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="books_custom", key="#id")
	public Book queryBookCacheable2(String id){
		logger.info("queryBookCacheable2,id={}",id);
		return repositoryBook.get(id);
	}
}
