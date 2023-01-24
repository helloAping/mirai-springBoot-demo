package cn.ms.mybotdemoboot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

public class BotUtil {
    @Resource
    InstructUtil instructUtil;
    //解析属于全局的key
    public InstructConsts parseMessageToKey(String message,TypeEnum... types){
        System.out.println("parseMessageToKey");
        for (TypeEnum type : types) {
            List<InstructConsts> listConsts = instructUtil.getListConsts(type);
            if (Objects.isNull(listConsts)) continue;
            System.out.println(type);
            for (InstructConsts listConst : listConsts) {
                System.out.println(listConst.getKey());
                if (listConst.filter(message)) {
                    return listConst;
                }
            }
        }
        return null;
    }

    /**
     * 根据类型获取所有所有key以及描述
     * @param typeEnums
     * @return
     */
    protected MessageChain seedHelpContent(TypeEnum... typeEnums) {
        System.out.println("seedHelpContent");
        MessageChainBuilder singleMessages = new MessageChainBuilder();
        for (TypeEnum typeEnum : typeEnums) {
            System.out.println(typeEnum);
            List<InstructConsts> listConsts = instructUtil.getListConsts(typeEnum);
            if (Objects.isNull(listConsts)) continue;
            System.out.println("进入listConsts循环");
            for (InstructConsts listConst : listConsts) {
                System.out.println(listConst);
                String key = listConst.getPrefix() + listConst.getKey() + listConst.getSuffix();
                singleMessages.append(new PlainText(String.format("%s 描述:%s",key,listConst.getDisable())));
            }
        }
        return singleMessages.build();
    }
}
