package com.hry.spring.spel;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * 通过程序实现Sp EL
 * @author hry
 *
 */
@Component
public class SpELProgramingExample {
	private static final Logger log = LoggerFactory.getLogger(SpELProgramingExample.class);
	
	public void spElPro(){
		// As an example of method invocation, we call the concat method on the string literal.
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		String message = (String) exp.getValue();
		log.info("SpEl调用string的concat方法, 'Hello World'.concat('!') 值={}", message );
		
		// As an example of calling a JavaBean property, the String property Bytes can be called as shown below.
		// SpEL also supports nested properties using standard dot notation, i.e. prop1.prop2.prop3 and the setting of property values
		exp = parser.parseExpression("'Hello World'.bytes"); // invokes 'getBytes()'
		byte[] bytes = (byte[]) exp.getValue();
		log.info("SpEl调用string的属性方法getBytes(), 'Hello World'.bytes 值={}", Arrays.toString(bytes) );
		
		// Public fields may also be accessed.
		exp = parser.parseExpression("'Hello World'.bytes.length"); // invokes 'getBytes().length'
		int length = exp.getValue(Integer.class); // 指定返回的类型
		log.info("SpEl调用byte[]的公共属性length, 'Hello World'.bytes.length 值={}", length );
		
		// 获取People的name值，比较name值是不是hry
		People people = new People();
		people.setName("hry");
		EvaluationContext context = new StandardEvaluationContext(people);
		exp = parser.parseExpression("name"); 
		String resultName = exp.getValue(context, String.class); // 
		log.info("SpEl从bean中获取属性值, name 值={}", resultName );
		exp = parser.parseExpression("name == 'hry'"); // 不带''表示变量， 带''表示字符串
		boolean result = exp.getValue(context, Boolean.class); // 
		log.info("SpEl执行bean中属性值比较, name == 'hry' 值={}", result );
	}
	
	public static class People{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}


