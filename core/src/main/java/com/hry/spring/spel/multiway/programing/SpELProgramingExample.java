package com.hry.spring.spel.multiway.programing;

import java.util.Arrays;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hry.spring.spel.multiway.People;

/**
 * 通过程序代码实现调用Spring EL
 * 
 * @author hry
 *
 */
@Component
public class SpELProgramingExample {
//	private static final Logger log = LoggerFactory.getLogger(SpELProgramingExample.class);
	
	private String stringMethod; // 调用String的concat方法
	
	private String javaBeanProperties; // 调用JavaBean的属性，如这里实际是调用getBytes()方法
	
	private long publicAttr; // 访问对象公共属性
	
	private String objName; // 获取People的name值，比较name值是不是hry
	
	private boolean objNamecmp;  // 比较name值是不是hry人结果
	
	// 初始化值
	public void init(){
		// 调用String的concat方法
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!')");
		stringMethod = (String) exp.getValue();
		
		// 调用JavaBean的属性，如这里实际是调用getBytes()方法
		// SpEL also supports nested properties using standard dot notation, i.e. prop1.prop2.prop3 and the setting of property values
		exp = parser.parseExpression("'Hello World'.bytes"); // invokes 'getBytes()'
		byte[] bytes = (byte[]) exp.getValue();
		javaBeanProperties = Arrays.toString(bytes);
		
		// 访问对象公共属性
		// Public fields may also be accessed.
		exp = parser.parseExpression("'Hello World'.bytes.length"); // invokes 'getBytes().length'
		publicAttr = exp.getValue(Integer.class); // 指定返回的类型
		
		// 获取People的name值，比较name值是不是hry
		People people = new People();
		people.setName("hry");
		EvaluationContext context = new StandardEvaluationContext(people);
		exp = parser.parseExpression("name"); 
		objName = exp.getValue(context, String.class); // 
		exp = parser.parseExpression("name == 'hry'"); // 不带''表示变量， 带''表示字符串
		objNamecmp = exp.getValue(context, Boolean.class); // 
	}
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
	
	public String getStringMethod() {
		return stringMethod;
	}

	public void setStringMethod(String stringMethod) {
		this.stringMethod = stringMethod;
	}

	public String getJavaBeanProperties() {
		return javaBeanProperties;
	}

	public void setJavaBeanProperties(String javaBeanProperties) {
		this.javaBeanProperties = javaBeanProperties;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public long getPublicAttr() {
		return publicAttr;
	}

	public void setPublicAttr(long publicAttr) {
		this.publicAttr = publicAttr;
	}

	public boolean isObjNamecmp() {
		return objNamecmp;
	}

	public void setObjNamecmp(boolean objNamecmp) {
		this.objNamecmp = objNamecmp;
	}
}


