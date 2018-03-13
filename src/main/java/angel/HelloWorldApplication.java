package angel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Author hongql
 *
 * @Date Created by blm on 26/10/17.
 * @Description 描述
 */
@SpringBootApplication
public class HelloWorldApplication extends SpringBootServletInitializer {

    //如果我们想要将这个JAR包转换成可以在Servlet容器中部署的WAR的话，
    // 就不能依赖于Application的main函数了，而是要以类似于web.xml文件配置的方式来启动Spring应用上下文，
    // 此时我们需要声明这样一个类：
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HelloWorldApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class,args);
    }

}
