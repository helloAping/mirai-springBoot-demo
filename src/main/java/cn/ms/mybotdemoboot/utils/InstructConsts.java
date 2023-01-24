package cn.ms.mybotdemoboot.utils;

import lombok.Getter;

public enum InstructConsts {
    HELP("/","help","菜单，如果您在群内请 @机器人 /help 如果是私聊则直接输入/help","",TypeEnum.GLOBAL,ChannelEnum.PREFIX),
    ;
    @Getter
    private String prefix = "/";
    @Getter
    private String key;
    @Getter
    private String disable;
    @Getter
    private String suffix = " ";
    @Getter
    private TypeEnum type;
    @Getter
    private ChannelEnum channel;

    InstructConsts(String prefix, String key, String disable, String suffix, TypeEnum type, ChannelEnum channel) {
        this.prefix = prefix;
        this.key = key;
        this.disable = disable;
        this.suffix = suffix;
        this.type = type;
        this.channel = channel;
    }

    /**
     * 根据途径过滤指令
     * @param message
     * @return
     */
    public Boolean filter(String message){
        return this.channel.filter(this.prefix+this.key+this.suffix,message);
    }
}

