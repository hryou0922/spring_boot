package com.hry.spring.grpc.stream.custom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import com.google.protobuf.util.JsonFormat;
import com.hry.spring.grpc.stream.Feature;
import com.hry.spring.grpc.stream.FeatureDatabase;
import com.hry.spring.grpc.stream.Point;


/**
 * 工作类
 *
 */
public class RouteGuideUtil {
  private static final double COORD_FACTOR = 1e7;

  public static double getLatitude(Point location) {
    return location.getLatitude() / COORD_FACTOR;
  }

  public static double getLongitude(Point location) {
    return location.getLongitude() / COORD_FACTOR;
  }

  /**
   * 获取json的url
   */
  public static URL getDefaultFeaturesFile() {
    return RouteGuideServer.class.getResource("route_guide_db.json");
  }

  /**
   * 解析json文件，返回List<Feature>
   * 
   */
  public static List<Feature> parseFeatures(URL file) throws IOException {
    InputStream input = file.openStream();
    try {
      Reader reader = new InputStreamReader(input);
      try {
        FeatureDatabase.Builder database = FeatureDatabase.newBuilder();
        JsonFormat.parser().merge(reader, database);
        return database.getFeatureList();
      } finally {
        reader.close();
      }
    } finally {
      input.close();
    }
  }

  public static boolean exists(Feature feature) {
    return feature != null && !feature.getName().isEmpty();
  }
}