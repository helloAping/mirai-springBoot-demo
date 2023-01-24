package cn.ms.mybotdemoboot.config.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "bot.init")
public class BotModel {
    private Long account;
    private String password;
    //测试
    private Boolean openWhite;
    private List<Long>  whiteListFriend;
    private List<Long> whiteListGroup;
}
