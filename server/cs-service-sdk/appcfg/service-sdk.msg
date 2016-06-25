#消息提示配置文件, 消息的属性命名规则:
#空字段提示语:  m+"."+字段名+".null"=错误提示语句
#字段格式错误:  m+"."+字段名+".notmatch"=错误提示语句


sdk.client.send_tosina.sms=尊敬的用户，你已在%1游戏支付%2元。客服电话：020-38811170。

sdk.client.bind_phone.sms=欢迎使用乐游游戏平台，您绑定号码的验证码为:%1，有效期为：30分钟。【掌上乐游】

sdk.client.retrive_account.sms=欢迎使用乐游游戏平台，您找回账户的验证码为: %1，有效期为：30分钟。【掌上乐游】

sdk.client.bind_phone.vcode.invalid=无效的验证码

sdk.client.send_message.error=验证码发送失败

sdk.client.bind_phone.samephone_code=101
sdk.client.bind_phone.samephone_msg=该号码是否之前已经绑定过这个帐号，是否继续绑定，继续绑定会解除之前的绑定？

sdk.client.bind_phone.hasphone_code=102
sdk.client.bind_phone.hasphone_msg=该用户已经有其他手机号绑定，是否继续绑定，继续绑定会解除之前的绑定？

sdk.client.bind_phone.hasuser_code=103
sdk.client.bind_phone.hasuser_msg=该手机号已经有其他账号绑定，是否继续绑定，继续绑定会解除之前的绑定？