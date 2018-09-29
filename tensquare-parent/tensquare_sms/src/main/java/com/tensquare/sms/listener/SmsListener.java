package com.tensquare.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: yangfei
 * @Date: 2018/9/29 19:48
 * @Description:    监听sms空间
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired  //注入阿里云通讯提供的短信业务工具类
    private SmsUtil smsUtil;
    @Value("${aliyun.sms.tempCode}")   //模板
    private String tempCode;
    @Value("${aliyun.sms.signName}")   //签名
    private String signName;
    @RabbitHandler
    public void sendSms(Map<String,String> map){
        try {
            String mobile = map.get("mobile");
            String code = map.get("code");
            System.out.println(mobile);
            System.out.println(code);                                                            //{"code":"+xxx"}
            SendSmsResponse sendSmsResponse = smsUtil.sendSms(mobile, tempCode, signName, "{\"code\":\""+code+"\"}");
            if(sendSmsResponse.getCode().equals("OK")){
                System.out.println("短信发送成功...");
            }else{
                System.out.println("发送失败...");
            }
        } catch (Exception e) {
            System.out.println("发送失败"+e.getMessage());
        }
    }
}
