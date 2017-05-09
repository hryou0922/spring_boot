package com.hry.spring.grpc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用cmd模式，执行protoc根据proto生成java类
 * 
 * @author hry
 *
 */
public class ProtoGenerateClass {
	// protoc的目录
	private static final String PROTOC_FILE = "D:/tool/protoc/protoc-3.0.0-win32/bin/protoc.exe";
	// specifies a directory in which to look for .proto files when resolving import directives
	private static final String IMPOR_TPROTO = "D:/project/git/spring_boot/grpc";
	// 生成java类输出目录
	private static final String JAVA_OUT = "d:/tmp";
	// 指定proto文件
	private static final String protos = "D:/project/git/spring_boot/grpc/src/main/resources/com/hry/spring/grpc/*/*.proto";
			
	public static void main(String[] args) throws IOException {
		List<String> lCommand = new ArrayList<String>();
		lCommand.add(PROTOC_FILE);
		lCommand.add("-I=" + IMPOR_TPROTO );
		lCommand.add("--java_out=" + JAVA_OUT);
		lCommand.add(protos);
		
		Cmd cmd = new Cmd();
		cmd.execute(lCommand);
	}	
	
}
