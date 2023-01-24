package cn.ms.mybotdemoboot;

import cn.ms.mybotdemoboot.config.model.BotModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BotModel.class})
public class MyBotDemoBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBotDemoBootApplication.class, args);
    }

}
