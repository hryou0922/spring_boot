package com.hry.spring.grpc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.util.Assert;

 
/**
 * CMD的命令值
 * @author hry
 *
 */
public class Cmd {
	private void writeInputStream2OutputStream(InputStream in, OutputStream out) throws IOException {
		LineNumberReader lnr = new LineNumberReader(new InputStreamReader(in, "UTF-8"));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
		String s;
		while ((s = lnr.readLine()) != null) {
			// 打印信息
			pw.println(s);
		}
		// 关闭输出流
		lnr.close();
		pw.close();
	}
	
	public void execute(List<String> commandList){
		Assert.notNull(commandList, "commandList can't be null!");
		// 如：protoc.exe -I=D:/project/git/spring_boot/protobuf --java_out=d:/tmp D:/project/git\spring_boot/protobuf/src/main/resources/com/hry/spring/proto/simple2/person-entity.proto
		ProcessBuilder pb = new ProcessBuilder(commandList);
		// 错误流和输出流合并到一起输出，否则可能因为2个流的某个缓存区满了而导致现场阻塞
		//错误和正确信息合并输出，因为输出有2个，一个是正确，一个错误，着个只要其中有1个无法被实时读取，则可能造成进程阻塞
		pb.redirectErrorStream(true);
		Process p;
		try {
			p = pb.start();
			writeInputStream2OutputStream(p.getInputStream(), System.err);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		int iResult = p.exitValue();
		if(iResult == 0){
			System.out.println(" result = " +p.exitValue() + " execute command success! Command = " + commandList);
		
		}else{
			System.out.println(" result = " +p.exitValue() + " execute command failure! Command = " + commandList);
		}

	}
}
