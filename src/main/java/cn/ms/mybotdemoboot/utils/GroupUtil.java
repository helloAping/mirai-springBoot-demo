package cn.ms.mybotdemoboot.utils;

import com.alibaba.fastjson2.JSONArray;
import net.mamoe.mirai.contact.Group;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroupUtil extends BotUtil{
    public void distributeKey(Group group, JSONArray originalMessage) {

    }
}
