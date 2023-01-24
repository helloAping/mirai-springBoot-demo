package cn.ms.mybotdemoboot.utils;

import net.mamoe.mirai.message.data.At;

enum ChannelEnum {
    PREFIX {
        /**
         * 判断是否开头就为指令
         * @param key
         * @param message
         * @return
         */
        @Override
        public Boolean filter(String key, String message) {
            int i = message.indexOf(key);
            return i == 0;
        }
    }, LIKE {
        /**
         * 类似于模糊匹配 %key%
         * @param key
         * @param message
         * @return
         */
        @Override
        public Boolean filter(String key, String message) {
            return message.contains(key);
        }
    }, SUFFIX {
        @Override
        public Boolean filter(String key, String message) {
            int i = message.lastIndexOf(key);
            int length = key.length();
            return i >= 0 && (i + length == message.length());
        }
    };

    ChannelEnum() {
    }

    /**
     * 过滤消息中是否包含指定key
     *
     * @param key
     * @param message
     * @return
     */
    public abstract Boolean filter(String key, String message);
}
