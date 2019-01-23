package im.yixin.sp;

import org.springframework.boot.SpringApplication;

/**
 * spring boot 入口类
 * @Author: huangrongyou@yixin.im
 * @Date: 2019/1/23 16:54:49
 */
public class SPBusinessApplication {
    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            args = new String[1];
            args[0] = "--spring.profiles.active=local";
        }
        SpringApplication.run(SPBusinessApplication.class, args);
    }
}
