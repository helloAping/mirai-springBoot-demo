package cn.ms.mybotdemoboot.utils;

import lombok.*;
import net.mamoe.mirai.internal.deps.io.ktor.util.collections.ConcurrentMap;
import org.springframework.lang.NonNullFields;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
@Component
public class  InstructUtil {

    private ConcurrentMap<TypeEnum,List<InstructConsts>> constsMap = new ConcurrentMap<>();
    private ConcurrentMap<String,InstructConsts> keysMap = new ConcurrentMap<>();
    @PostConstruct
    private void init(){
        for (InstructConsts value : InstructConsts.values()) {
            putConsts(value.getType(),value);
            putKeys(value.getKey(),value);
        }
    }


    /**
     * 插入指令常量类的实体进入map，实现分组
     * @param type
     * @param consts
     */
    public void putConsts(TypeEnum type,InstructConsts consts){
        List<InstructConsts> instructConsts = constsMap.get(type);
        if (Objects.isNull(instructConsts)){
            instructConsts = new ArrayList<>();
            constsMap.put(type,instructConsts);
        }
        instructConsts.add(consts);
        System.out.println("插入"+consts);
    }
    public List<InstructConsts> getListConsts(TypeEnum type){
        return constsMap.get(type);
    }

    public void putKeys(String key,InstructConsts consts){
        keysMap.put(key,consts);
    }

    public InstructConsts getKeys(String key){
        return keysMap.get(key);
    }
    private InstructUtil() {
    }
}
