package com.hry.spring.proto.simple;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.hry.spring.proto.simple.FirstDemo.Demo;
import com.hry.spring.proto.simple.MyPersonEntity.MyPerson;

public class FirstDemoTest {

	@Test
	public void testMyPerson() throws InvalidProtocolBufferException{
		 //模拟将对象转成byte[]，方便传输
        Demo.Builder builder = Demo.newBuilder();
        builder.setId(1);
        builder.setName("name");
        builder.setEmail("hryou0922@126.com");
        Demo person = builder.build();
        System.out.println("before :"+ person.toString());

        System.out.println("===========Person Byte==========");
        for(byte b : person.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        MyPerson p2 = MyPerson.parseFrom(byteArray);
        System.out.println("after :" +p2.toString());
	}
	
}
