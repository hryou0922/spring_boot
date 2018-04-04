package com.hry.spring.redis.cache;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hry.spring.redis.cache.support.Book;
import com.hry.spring.redis.cache.support.BookQry;

/**
 * 缓存的基本用法
 * @author hry
 *
 */
@Component
public class BookService extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	// ==================== @Cacheable ========================
	/**
	 * cacheNames 设置缓存的值 
	 * 	key：指定缓存的key，这是指参数id值。 key可以使用spEl表达式
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book1", key="#id")
	public Book queryBookCacheable(String id){
		logger.info("queryBookCacheable,id={}",id);
		return repositoryBook.get(id);
	}
	
	/**
	 * 这里使用另一个缓存存储缓存
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book2", key="#id")
	public Book queryBookCacheable_2(String id){
		logger.info("queryBookCacheable_2,id={}",id);
		return repositoryBook.get(id);
	}
	
	/**
	 * 缓存的key也可以指定对象的成员变量
	 * @param qry
	 * @return
	 */
	@Cacheable(cacheNames="book1", key="#qry.id")
	public Book queryBookCacheableByBookQry(BookQry qry){
		logger.info("queryBookCacheableByBookQry,qry={}",qry);
		String id = qry.getId();
		Assert.notNull(id, "id can't be null!");
		String name = qry.getName();
		Book book = null;
		if(id != null){
			book = repositoryBook.get(id);
			if(book != null && !(name != null && book.getName().equals(name))){
				book = null;
			}
		}
		return book;
	}
	
	/**
	 * 以上我们使用默认的keyGenerator，对应spring的SimpleKeyGenerator 
	 * 	如果你的使用很复杂，我们也可以自定义myKeyGenerator的生成key
	 * 
	 *  key和keyGenerator是互斥，如果同时制定会出异常
	 * 	The key and keyGenerator parameters are mutually exclusive and an operation specifying both will result in an exception.
	 * 
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book3",  keyGenerator="myKeyGenerator")
	public Book queryBookCacheableUseMyKeyGenerator(String id){
		logger.info("queryBookCacheableUseMyKeyGenerator,id={}",id);
		return repositoryBook.get(id);
	}
	
	/***
	 * 如果设置sync=true，
	 * 	如果缓存中没有数据，多个线程同时访问这个方法，则只有一个方法会执行到方法，其它方法需要等待
	 * 	如果缓存中已经有数据，则多个线程可以同时从缓存中获取数据
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book3", sync=true)
	public Book queryBookCacheableWithSync(String id) {
		logger.info("begin ... queryBookCacheableByBookQry,id={}",id);
		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
		}
		logger.info("end ... queryBookCacheableByBookQry,id={}",id);
		return repositoryBook.get(id);
	}
	
	/**
	 * 条件缓存：
	 * 只有满足condition的请求才可以进行缓存，如果不满足条件，则跟方法没有@Cacheable注解的方法一样
	 * 	如下面只有id < 3才进行缓存
	 * 
	 */
	@Cacheable(cacheNames="book11", condition="T(java.lang.Integer).parseInt(#id) < 3 ")
	public Book queryBookCacheableWithCondition(String id) {
		logger.info("queryBookCacheableByBookQry,id={}",id);
		return repositoryBook.get(id);
	}
	
	/**
	 * 条件缓存：
	 * 对不满足unless的记录，才进行缓存
	 * 	"unless expressions" are evaluated after the method has been called
	 * 	如下面：只对不满足返回 'T(java.lang.Integer).parseInt(#result.id) <3 ' 的记录进行缓存
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book22", unless = "T(java.lang.Integer).parseInt(#result.id) <3 ")
	public Book queryBookCacheableWithUnless(String id) {
		logger.info("queryBookCacheableByBookQry,id={}",id);
		return repositoryBook.get(id);
	}
	
	
	// ==================== @CacheEvict ========================
	/**
	 * allEntries = true: 清空book1里的所有缓存
	 */
	@CacheEvict(cacheNames="book1", allEntries=true)
	public void clearBook1All(){
		logger.info("clearAll");
	}
	/**
	 * 对符合key条件的记录从缓存中book1移除
	 */
	@CacheEvict(cacheNames="book1", key="#id")
	public void updateBook(String id, String name){
		logger.info("updateBook");
		Book book = repositoryBook.get(id);
		if(book != null){
			book.setName(name);
			book.setUpdate(new Date());
		}
	}
	
	// ==================== @CachePut ========================
	/**
	 * 每次执行都会执行方法，无论缓存里是否有值，同时使用新的返回值的替换缓存中的值
	 * 	这里不同于@Cacheable：@Cacheable如果缓存没有值，从则执行方法并缓存数据，如果缓存有值，则从缓存中获取值
	 * @param id
	 * @return
	 */
	@CachePut(cacheNames="book1", key="#id")
	public Book queryBookCachePut(String id){
		logger.info("queryBookCachePut,id={}",id);
		return repositoryBook.get(id);
	}
	
}
