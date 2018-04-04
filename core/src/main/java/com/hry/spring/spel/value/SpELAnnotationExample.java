package com.hry.spring.spel.value;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hry.spring.spel.support.PlaceOfBirth;

/**
 * 通过@Value使用Sp EL
 * 	Sp EL表达式使用 #{}包围
 * 
 * @author hry
 *
 */
@Component
public class SpELAnnotationExample {
	/**
	 * Literal expressions(文字表达式)分类：
	 * 	strings:
	 * 	numeric: 
	 * 	boolean:
	 *  null:
	 * 
	 */
	// string: 输出 --> Hello World
	@Value("#{ 'Hello World' }")
	private String stringHello;
	
	// numeric - double: 输出 --> 6.0221415e+23
	@Value("#{ 6.0221415E+23 }")
	private double numbericDouble;
	
	// numeric - hex: 输出 --> 2147483647
	@Value("#{ 0x7FFFFFFF }")
	private int numbericInt;
	
	// boolean: 输出 --> true
	@Value("#{ true }")
	private boolean numberBoolean;
	
	// null: 输出 --> null值
	@Value("#{ null }") 
	private Integer numberNull;
	
	
	/**
	 * Types expressions
	 *  使用特殊操作符T指定java.lang.Class实例
	 *  对于java.lang下面的类， T()可以不指定全限定名称
	 *  T也可以用于调用静态方法
	 */
	// T指定java.lang.Class实例: 输出 --> java.util.Date
	@Value("#{ T(java.util.Date)}")
	private Class<?> tDate;
	
	// 对于java.lang下面的类， T()可以不指定全限定名称: 输出 --> java.lang.String
	@Value("#{ T(String)}")
	private Class<?> tLangString;

	// 调用类的静态方法: 输出 --> true
	@Value("#{ T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR }")
	private boolean tStaticMethod;
	
	/**
	 * Accessing properties
	 * 	访问对象的属性，调用对象的方法
	 */
	// 访问对象inventor的属性name: 输出 --> name
	@Value("#{ inventor.name }") 
	private String propertiesGetObjectMethod;
	
	// 调用对象inventor的成员变量birthdate的getYear()方法: 输出 --> 2017
	@Value("#{ inventor.birthdate.Year + 1900}")
	private int propertiesGetObjectMemberMethod;
	
	/**
	 * arrays
	 * 	访问tesla的数组inverntions[3]的值
	 */
	// 通过索引访问数组对象: 输出 --> inventions_3
	@Value("#{ inventor.inventions[2] }")
	private String arraysIndex;
	
	// 初始化数组: 输出 -->  [ 1, 2, 3]
	@Value("#{ new int[]{1,2,3} }")
	private int[] inlineArray;
	
	/**
	 *  lists
	 *  	通过索引获取List的值
	 *  	注入初始化List对象
	 */
	// 通过List获取列表的第三个对象的country值: 输出 -->country_list_2
	@Value("#{ inventor.placeOfBirthList[1].country}")
	private String listIndexMember;
	
	// 注入初始化List对象. {}表示空List : 输出 -->  [ 1,  2,  3, 4]
	@Value("#{ {1,2,3,4} }")
	private List<Integer> inlineList;
	
	/**
	 * maps
	 */
	// 获取inventor对象的成员变量map的mapKey_2的值: 输出 --> 2
	@Value("#{ inventor.map[mapKey_2]}")
	private int mapKeyValue;
	
	// 注入初始化map对象. {}表示空map: 输出 --> {"name": "hry","sex": "man"}
	@Value("#{ {name:'hry',sex:'man'} }")
	private Map<String,String> inlineMap;
	
	/**
	 * Method invocation: 方法调用
	 */
	// 调用string的方法: 输出 --> c
	@Value("#{ 'abc'.substring(2, 3) }")
	private String methodInvoke;
	
	// 调用自定义对象的方法: 输出 --> false
	@Value("#{ inventor.nameIsSame('name_1') }")
	private String myObjectmethodInvoke;
	
	/**
	 * Relational operators(关系操作符)
	 * 	equal: (==)
	 * 	not equal: 
	 * 	less than:(<)
	 * 	less than or equal:(<=)
	 * 	greater than: (>)
	 * 	greater than or equal: (>=)
	 * 
	 */
	// 相同判断: 输出 --> true
	@Value("#{ 2 == 2 }") 
	private boolean relationEqual;
	
	// 大小比较: 输出 --> false
	@Value("#{ 2 < -5.0 }")
	private boolean relationLessThan;
	
	/**
	 * Logical operators(逻辑操作符)
	 * 	and, 
	 * 	or, 
	 *  not  (!).
	 */
	// and操作符: 输出 --> false
	@Value("#{ true and false }")
	private boolean logicalAnd;
	
	// or操作符: 输出 --> true
	@Value("#{ inventor.nameIsSame('name_1') or inventor.nameIsSame('name') }")
	private boolean logicalOr;
	
	// not操作符: 输出 --> false
	@Value("#{ !true }")
	private boolean logicalNot;
	
	/**
	 * Mathematical operators：数学运算符
	 * 	Addition
	 * 	Subtraction: 
	 *  multiplication:  
	 *  division:  (/)
	 *  modulus: (%) 
	 *  exponential power: (^)
	 * @return
	 */
	// : 输出 --> 2
	@Value("#{ 1 + 1 }")
	private int mathematicalAddition;
	
	// 加法: 输出 --> 4
	@Value("#{ 1 - -3 }")
	private int mathematicalSubtraction;
	
	// 减法: 输出 --> 6
	@Value("#{ -2 * -3 }")
	private int mathematicalMultiplication;
	
	// 除法: 输出 --> -2
	@Value("#{ 6 / -3 }")
	private int mathematicalDivision;
	
	// 取余: 输出 --> 1
	@Value("#{ 8 / 5 % 2  }")
	private int mathematicalModulus;
	
	// 指数幂: 输出 --> 8
	@Value("#{ 2 ^ 3 }")
	private int mathematicalExponentialPower;
	
	/**
	 * Ternary operator(三元运算符)
	 * 
	 */
	// 三元运算符: 输出 --> falseExp
	@Value("#{false ? 'trueExp' : 'falseExp'}")
	private String ternary;
	
	/**
	 * Safe Navigation operator
	 * 	
	 */
	// 如果systemProperties['pop3.port']没有值，通过?指定默认值，避免抛出NullPointerException: 输出 --> 25
	@Value("#{ systemProperties['pop3.port'] ?: 25 }")
	private String safeNavigation;
	
	/**
	 * Collection
	 * 	Collection selection(集合选择): 语法.?[selectionExpression]
	 * 	Collection projection(集合投影)：语法 
	 */
	// 获取集合中对象placeOfBirth的city等于city_list_2的对象的集合: 输出 --> [{"city": "city_list_2", "country": "country_list_2"}]
	@Value("#{ inventor.placeOfBirthList.?[city=='city_list_2'] }")
	private List<PlaceOfBirth> collectionListSelection;
	
	// 获取对象Invertor中map的value<2的对象，并生成新的map: 输出 --> {"mapKey_1": "1"}
	@Value("#{ inventor.map.?[value<2] }")
	private Map<String, String> collectionMapSelection;
	
	// 获取inventor.placeOfBirthList的所有的属性city的值，并组成一个对象: 输出 --> "collectionProjection": ["city_list_1","city_list_2", "city_list_3"]
	@Value("#{ inventor.placeOfBirthList.![city] }")
	private List<String> collectionProjection;
	
	public String toString(){
	//	System.out.println(mapKeyValue);
		return JSON.toJSONString(this);
	}

	public String getStringHello() {
		return stringHello;
	}

	public void setStringHello(String stringHello) {
		this.stringHello = stringHello;
	}

	public double getNumbericDouble() {
		return numbericDouble;
	}

	public void setNumbericDouble(double numbericDouble) {
		this.numbericDouble = numbericDouble;
	}

	public int getNumbericInt() {
		return numbericInt;
	}

	public void setNumbericInt(int numbericInt) {
		this.numbericInt = numbericInt;
	}

	public boolean isNumberBoolean() {
		return numberBoolean;
	}

	public void setNumberBoolean(boolean numberBoolean) {
		this.numberBoolean = numberBoolean;
	}

	public Integer getNumberNull() {
		return numberNull;
	}

	public void setNumberNull(Integer numberNull) {
		this.numberNull = numberNull;
	}

	public Class<?> gettDate() {
		return tDate;
	}

	public void settDate(Class<?> tDate) {
		this.tDate = tDate;
	}

	public Class<?> gettLangString() {
		return tLangString;
	}

	public void settLangString(Class<?> tLangString) {
		this.tLangString = tLangString;
	}

	public boolean gettStaticMethod() {
		return tStaticMethod;
	}

	public void settStaticMethod(boolean tStaticMethod) {
		this.tStaticMethod = tStaticMethod;
	}

	public String getPropertiesGetObjectMethod() {
		return propertiesGetObjectMethod;
	}

	public void setPropertiesGetObjectMethod(String propertiesGetObjectMethod) {
		this.propertiesGetObjectMethod = propertiesGetObjectMethod;
	}

	public int getPropertiesGetObjectMemberMethod() {
		return propertiesGetObjectMemberMethod;
	}

	public void setPropertiesGetObjectMemberMethod(int propertiesGetObjectMemberMethod) {
		this.propertiesGetObjectMemberMethod = propertiesGetObjectMemberMethod;
	}

	public String getArraysIndex() {
		return arraysIndex;
	}

	public void setArraysIndex(String arraysIndex) {
		this.arraysIndex = arraysIndex;
	}

	public int[] getInlineArray() {
		return inlineArray;
	}

	public void setInlineArray(int[] inlineArray) {
		this.inlineArray = inlineArray;
	}

	public String getListIndexMember() {
		return listIndexMember;
	}

	public void setListIndexMember(String listIndexMember) {
		this.listIndexMember = listIndexMember;
	}

	public List<Integer> getInlineList() {
		return inlineList;
	}

	public void setInlineList(List<Integer> inlineList) {
		this.inlineList = inlineList;
	}

	public int getMapKeyValue() {
		return mapKeyValue;
	}

	public void setMapKeyValue(int mapKeyValue) {
		this.mapKeyValue = mapKeyValue;
	}

	public Map<String, String> getInlineMap() {
		return inlineMap;
	}

	public void setInlineMap(Map<String, String> inlineMap) {
		this.inlineMap = inlineMap;
	}

	public String getMethodInvoke() {
		return methodInvoke;
	}

	public void setMethodInvoke(String methodInvoke) {
		this.methodInvoke = methodInvoke;
	}

	public String getMyObjectmethodInvoke() {
		return myObjectmethodInvoke;
	}

	public void setMyObjectmethodInvoke(String myObjectmethodInvoke) {
		this.myObjectmethodInvoke = myObjectmethodInvoke;
	}

	public boolean isRelationEqual() {
		return relationEqual;
	}

	public void setRelationEqual(boolean relationEqual) {
		this.relationEqual = relationEqual;
	}

	public boolean isRelationLessThan() {
		return relationLessThan;
	}

	public void setRelationLessThan(boolean relationLessThan) {
		this.relationLessThan = relationLessThan;
	}

	public boolean isLogicalAnd() {
		return logicalAnd;
	}

	public void setLogicalAnd(boolean logicalAnd) {
		this.logicalAnd = logicalAnd;
	}

	public boolean isLogicalOr() {
		return logicalOr;
	}

	public void setLogicalOr(boolean logicalOr) {
		this.logicalOr = logicalOr;
	}

	public boolean isLogicalNot() {
		return logicalNot;
	}

	public void setLogicalNot(boolean logicalNot) {
		this.logicalNot = logicalNot;
	}

	public int getMathematicalAddition() {
		return mathematicalAddition;
	}

	public void setMathematicalAddition(int mathematicalAddition) {
		this.mathematicalAddition = mathematicalAddition;
	}

	public int getMathematicalSubtraction() {
		return mathematicalSubtraction;
	}

	public void setMathematicalSubtraction(int mathematicalSubtraction) {
		this.mathematicalSubtraction = mathematicalSubtraction;
	}

	public int getMathematicalMultiplication() {
		return mathematicalMultiplication;
	}

	public void setMathematicalMultiplication(int mathematicalMultiplication) {
		this.mathematicalMultiplication = mathematicalMultiplication;
	}

	public int getMathematicalDivision() {
		return mathematicalDivision;
	}

	public void setMathematicalDivision(int mathematicalDivision) {
		this.mathematicalDivision = mathematicalDivision;
	}

	public int getMathematicalModulus() {
		return mathematicalModulus;
	}

	public void setMathematicalModulus(int mathematicalModulus) {
		this.mathematicalModulus = mathematicalModulus;
	}

	public int getMathematicalExponentialPower() {
		return mathematicalExponentialPower;
	}

	public void setMathematicalExponentialPower(int mathematicalExponentialPower) {
		this.mathematicalExponentialPower = mathematicalExponentialPower;
	}

	public String getTernary() {
		return ternary;
	}

	public void setTernary(String ternary) {
		this.ternary = ternary;
	}

	public String getSafeNavigation() {
		return safeNavigation;
	}

	public void setSafeNavigation(String safeNavigation) {
		this.safeNavigation = safeNavigation;
	}

	public List<PlaceOfBirth> getCollectionListSelection() {
		return collectionListSelection;
	}

	public void setCollectionListSelection(List<PlaceOfBirth> collectionListSelection) {
		this.collectionListSelection = collectionListSelection;
	}

	public Map<String, String> getCollectionMapSelection() {
		return collectionMapSelection;
	}

	public void setCollectionMapSelection(Map<String, String> collectionMapSelection) {
		this.collectionMapSelection = collectionMapSelection;
	}

	public List<String> getCollectionProjection() {
		return collectionProjection;
	}

	public void setCollectionProjection(List<String> collectionProjection) {
		this.collectionProjection = collectionProjection;
	}
	
}
