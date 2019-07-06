package cn.myweb01.money01.singelService.sendEmail;

import cn.myweb01.money01.mapper.UserMapper;
import cn.myweb01.money01.utils.MailUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

public class SendEmail implements ChannelAwareMessageListener {
    @Autowired
    private UserMapper userMapper;

    private final static Logger log= LoggerFactory.getLogger(SendEmail.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void onMessage(Message message, Channel channel)  {
        log.info("rabbitMq接受者");
        try {
            //msg就是rabbitmq传来的消息
            // 使用jackson解析
            JsonNode jsonData = MAPPER.readTree(message.getBody());
            if("Y".equals(jsonData.get("sendEmail").textValue())){//发送短信
                log.info("开始发送邮件");
                MailUtil.sendMail(jsonData.get("email").textValue(),"<h1>激活测试</h1><a href='http://120.55.56.15/user/active?code="+jsonData.get("code").textValue()+"'>用户激活</a>");
                //手动ack确认
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //将数据库的send_email_flag设置为1
                userMapper.updateUserEmailFlagByUserName(jsonData.get("userName").textValue());
                log.info("发送邮件结束");

            }
           
        } catch (Exception e) {
            log.info("发送邮件异常");
            e.printStackTrace();

        }


    }
    }

