package com.webank.wsdaw.gateway.manager;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.webank.wsdaw.gateway.config.EmailConfig;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailManager {

    @Autowired private EmailConfig emailConfig;
    private MailAccount account;

    String verificationCodeTitle = "这是您的BeanBag账户的验证码";
    String verificationCodeContent = "验证码 {}，使用此6位验证码登录您的BeanBag账户。此验证码将在5分钟后过期，不要与任何人分享您的登录详细信息。";

    @PostConstruct
    private void init() {
        account = new MailAccount();
        account.setHost(emailConfig.getHost()); // SMTP服务器地址
        account.setPort(emailConfig.getPort()); // SMTP服务器端口
        account.setSslEnable(emailConfig.isSslEnable()); // 使用SSL
        account.setFrom(emailConfig.getFrom()); // 发件人
        account.setUser(emailConfig.getUser()); // 用户名
        account.setPass(emailConfig.getPass()); // 密码
    }

    public void sendVerificationCode(String toAddress, String code) {
        log.info("send account info is {}", account.toString());
        String content = StrUtil.format(verificationCodeContent, code);
        log.info("verification {} {} {}", toAddress, verificationCodeTitle, content);
        MailUtil.send(account, toAddress, verificationCodeTitle, content, false);
        log.info("sendEmail success to {}, code {}", toAddress, code);
    }
}
