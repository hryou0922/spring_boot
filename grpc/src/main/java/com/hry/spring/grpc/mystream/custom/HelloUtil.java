package com.hry.spring.grpc.mystream.custom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import com.google.protobuf.util.JsonFormat;
import com.hry.spring.grpc.mystream.SimpleFeature;
import com.hry.spring.grpc.mystream.SimpleFeatureDatabase;

/**
 * 工具类
 *
 */
public class HelloUtil {
	/**
	 * 解析json文件，返回List<Feature>
	 * 
	 */
	public static List<SimpleFeature> parseFeatures() throws IOException {
		URL file = HelloUtil.class.getResource("hello_simple.json");
		InputStream input = file.openStream();
		try {
			Reader reader = new InputStreamReader(input);
			try {
				SimpleFeatureDatabase.Builder database = SimpleFeatureDatabase.newBuilder();
				// json转化为对象
				JsonFormat.parser().merge(reader, database);
				return database.getFeatureList();
			} finally {
				reader.close();
			}
		} finally {
			input.close();
		}
	}

}