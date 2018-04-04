package com.hry.spring.proto.complex;

import org.junit.Test;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hry.spring.proto.complex.MyComplexObjectEntity.ComplexObject;
import com.hry.spring.proto.complex.MyComplexObjectEntity.Gender;
import com.hry.spring.proto.complex.MyComplexObjectEntity.MapVaule;
import com.hry.spring.proto.complex.MyComplexObjectEntity.Result;

public class ComplexObjectTest {

	@Test
	public void testComplexObject() throws InvalidProtocolBufferException{
		
		ComplexObject.Builder builder = ComplexObject.newBuilder();
		// 对应proto里： int32 id = 1;
		builder.setId(100);  
		// 对应proto里：string name = 2;//
		builder.setName("name"); 
		// 对应proto里：string email = 3;
		builder.setEmail("email");
		// 对应proto里：repeated string sons = 4; 列表
		builder.addSons("Son1");
		builder.addSons("Son2");
		// 对应proto里：Gender gender = 5; // Enum值
	//	builder.setGenderValue(Gender.MAN_VALUE);
		builder.setGender(Gender.MAN);
		// 对应proto里：repeated Result result = 6; // 新的Result对象List
		Result.Builder r = Result.newBuilder();
		r.setTitle("title");
		builder.addResult(r.build());
		// 对应proto里：repeated google.protobuf.Any any = 7; // Any对象
		Any.Builder any = Any.newBuilder();
		any.setTypeUrl("typeUrl");
		builder.addAny(any.build());
		// 对应proto里：map<string, MapVaule> map_field = 8;
		MapVaule.Builder mapValue = MapVaule.newBuilder();
		mapValue.setMapValue("mapValue");
		builder.putMap("mapKey", mapValue.build());
		
		// 生成最终的对象
		ComplexObject cob = builder.build();
        System.out.println("before :"+ cob.toString());

        System.out.println("===========ComplexObject Byte==========");
        for(byte b : cob.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(cob.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =cob.toByteArray();
        ComplexObject cob2 = ComplexObject.parseFrom(byteArray);
        System.out.println("after :" + cob2.toString());
	}
	
}
