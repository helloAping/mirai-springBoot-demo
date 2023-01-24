package cn.ms.mybotdemoboot.controller;

import cn.ms.mybotdemoboot.config.model.BotModel;
import cn.ms.mybotdemoboot.utils.FriendUtil;
import cn.ms.mybotdemoboot.utils.GroupUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageKey;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.SingleMessage;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public class BotRun {
    //设置bot实例方便访问
    private Bot bot;
    @Resource
    BotModel botModel;
    //启动bot的方法
    @PostConstruct
    public void init() {
        log.info("初始化消息:{}",botModel);
        bot = BotFactory.INSTANCE.newBot(botModel.getAccount(),botModel.getPassword() , new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.IPAD); // 切换协议
        }});
        bot.login();
        EventChannel<BotEvent> eventChannel = bot.getEventChannel();
        //监听群消息
        eventChannel.subscribeAlways(GroupMessageEvent.class, this::groupDistribute);
        //监听好友消息
        eventChannel.subscribeAlways(FriendMessageEvent.class, this::friendDistribute);
    }
    @Resource
    FriendUtil friendUtil;
    private void friendDistribute(FriendMessageEvent event){
        log.info("好友消息");
        Friend friend = event.getFriend();
        //是否开启白名单那
        if (botModel.getOpenWhite()) {
            //判断是否存在于白名单列表内，不存在则退出
            if (!botModel.getWhiteListFriend().contains(friend.getId())) {
                return;
            }
        }
        String json = MessageChain.serializeToJsonString(event.getMessage());
        System.out.println(json);
        JSONArray objects = JSONArray.parseArray(json);
        JSONObject jsonObject = objects.getJSONObject(0);
        JSONArray originalMessage = jsonObject.getJSONArray("originalMessage");
        System.out.println("好友组消息切分");
        System.out.println(originalMessage);
        friendUtil.distributeKey(friend,originalMessage);
    }
    @Resource
    GroupUtil groupUtil;
    private void groupDistribute(GroupMessageEvent event){
        log.info("群消息");
        Group group = event.getGroup();
        if (botModel.getOpenWhite()) {
            //判断是否存在于白名单列表内，不存在则退出
            if (!botModel.getWhiteListGroup().contains(group.getId())) {
                return;
            }
        }
        //message.get()
        String json = MessageChain.serializeToJsonString(event.getMessage());
        System.out.println(json);
        JSONArray objects = JSONArray.parseArray(json);
        JSONObject jsonObject = objects.getJSONObject(0);
        JSONArray originalMessage = jsonObject.getJSONArray("originalMessage");
        System.out.println("群组消息切分");
        System.out.println(originalMessage);

    }
}
