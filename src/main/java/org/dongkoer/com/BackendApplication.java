package org.dongkoer.com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@EnableWebMvc
//@MapperScan("org.dongkoer.com.mapper")
public class BackendApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(BackendApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String name = env.getProperty("spring.application.name");
        String path = property == null ? "" : property;
        System.out.println(
                "\n\t" +
                        "----------------------------------------------------------\n\t" +
                        "Application Sailrui-Boot is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                        "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                        "Doc:\thttp://" + ip + ":" + port +"/"+ name + "/swagger-ui/index.html" +"\n\t"+
                        "------------------------------------------------------------");
    }


}
