package cn.ms.mybotdemoboot.utils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class FriendUtil extends BotUtil{
    /**
     * 解析朋友聊天框指令
     * @param message
     * @return
     */
    public InstructConsts parseMessageToKey(String message) {
        System.out.println("parseMessageToKey");
        System.out.println(message);
        if (StringUtils.isEmpty(message)) return null;
        return super.parseMessageToKey(message,TypeEnum.FRIEND,TypeEnum.GLOBAL);
    }

    /**
     * 消息第一时间进入这
     * @param friend
     * @param originalMessage
     */
    public void distributeKey(Friend friend, JSONArray originalMessage) {
        //获取朋友以及全局的指令
        JSONObject jsonObject = originalMessage.getJSONObject(0);
        InstructConsts content = parseMessageToKey(jsonObject.getString("content"));
        if (Objects.isNull(content)) return;//表示不是指令
        //解析消息是否符合全局指令
        MessageChain chain;
        switch (content){
            case HELP:
                chain = seedHelpContent();
                break;
            default:
                return;
        }
        friend.sendMessage(chain);
    }

    protected MessageChain seedHelpContent() {
        return super.seedHelpContent(TypeEnum.GLOBAL,TypeEnum.FRIEND);
    }
}
