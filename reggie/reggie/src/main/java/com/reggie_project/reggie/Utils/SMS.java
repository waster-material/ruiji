package com.reggie_project.reggie.Utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

public class SMS {
    public static void sendMessage(String SignName,String templeateCode,String phoneNumber,String param)  {
        DefaultProfile profile =DefaultProfile.getProfile("cn-zhangjiakou"," LTAI5tFsdkZoSyd9TcSntg5e"," yo1wMsvpP7BxZ8hTtnKIdsrbiHlyS");
        IAcsClient client =new DefaultAcsClient(profile);
        SendSmsRequest request =new SendSmsRequest();
        request.setSysRegionId("cn-zhangjiakou"); //设置服务器的地区名
        request.setPhoneNumbers(phoneNumber); // 设置接收要发送的手机号码
        request.setSignName(SignName); //设置签名
        request.setTemplateCode(templeateCode);  //设置模板
        request.setTemplateParam("{\"code\":\""+param+"\"}"); //设置要发送的验证码的内容
        try {
            SendSmsResponse response =client.getAcsResponse(request);
            System.out.println("短信发送成功");
        } catch (ClientException e) {
             e.printStackTrace();
        }

    }


//    public static void main(String[] args) {
//        DefaultProfile profile =DefaultProfile.getProfile("cn-zhangjiakou","LTAI5tFsdkZoSyd9TcSntg5e","yo1wMsvpP7BxZ8hTtnKIdsrbiHlySh");
//        IAcsClient client =new DefaultAcsClient(profile);
//        SendSmsRequest request =new SendSmsRequest();
//        request.setSysRegionId("cn-zhangjiakou"); //设置服务器的地区名
//        request.setPhoneNumbers("13197106236"); // 设置接收要发送的手机号码
//        request.setSignName("瑞吉外卖"); //设置签名
//        request.setTemplateCode("SMS_275050205");  //设置模板
//        request.setTemplateParam("{\"code\":\"6666\"}"); //设置要发送的验证码的内容
//        try {
//            SendSmsResponse response =client.getAcsResponse(request);
////            System.out.println("短信发送成功");
//            System.out.println(new Gson().toJson(response));
//        } catch (ServerException e) {
//           e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println(e.getErrCode());
//            System.out.println(e.getErrMsg());
//            System.out.println(e.getRequestId());
//        }
//    }
}
