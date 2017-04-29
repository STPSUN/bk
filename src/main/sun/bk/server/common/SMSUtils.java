package main.sun.bk.server.common;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

/**
 * Created by SUN on 2017/3/2.
 */
public class SMSUtils {
    public static String sendCheckCodeSMS(String code, String mobile)
    {
        String sms_url = "http://gw.api.taobao.com/router/rest";
        String sms_app_key = "23778836";
        String sms_app_secret = "1a3ccb1e580a9516c937b721aadb4132";
        TaobaoClient client = new DefaultTaobaoClient(sms_url, sms_app_key, sms_app_secret);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend(mobile);
        request.setSmsType("normal");
        request.setSmsFreeSignName("高程辉的博客");
        request.setSmsParamString("{\"code\":\"" + code + "\"}");
        request.setRecNum(mobile);
        request.setSmsTemplateCode("SMS_63845191");
        try
        {
            client.execute(request);
            return "";
        }catch (ApiException e)
        {
            e.printStackTrace();
            return e.getErrMsg();
        }
    }
}
