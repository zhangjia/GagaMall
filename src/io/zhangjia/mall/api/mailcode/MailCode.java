package io.zhangjia.mall.api.mailcode;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import com.aliyuncs.exceptions.ServerException;

import java.util.Random;


public class MailCode {
    public static void main(String[] args) {
        new MailCode().sample("zhangjia@188.com");
    }


    public String sample(String mail) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "keyid", "secret");
        //使用https加密连接
        //profile.getHttpClientConfig().setProtocolType(com.aliyuncs.http.ProtocolType.HTTPS);
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("jia@mail.zhangjia.io");
            request.setFromAlias("张甲");
            request.setAddressType(1);
            request.setTagName("zhangjia");
            request.setReplyToAddress(true);
            request.setToAddress(mail);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("嘎嘎商城验证码");
            String random = String.valueOf(new Random().nextInt(9999) + 100);
            request.setHtmlBody("您的验证码是" + random + ",不能告诉其他人鸭！");
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            return random;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;
    }
}
