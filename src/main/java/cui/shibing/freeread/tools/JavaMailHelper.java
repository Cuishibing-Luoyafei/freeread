package cui.shibing.freeread.tools;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailHelper {
    private static final String EMAIL_NAME = "shibing.cui@wooread.com";

    private static final String EMAIL_PASS = "MyPassword3+";

    /**
     * 发送邮件
     *
     * @param toEmail 收件人
     * @param title 主题
     * @param content 内容
     *
     * @return 是否成功
     */
    public static boolean sendEmail(String toEmail, String title,
                                    String content) {
        try {
            sendMail(EMAIL_NAME, toEmail, EMAIL_NAME, EMAIL_PASS, title, content);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @param toEmails 收件人(多个)
     * @param title 主题
     * @param content 内容
     *
     * @return 是否成功
     */
    public static boolean sendEmail(String[] toEmails, String title, String content) {
        try {
            sendMail(EMAIL_NAME, toEmails, EMAIL_NAME, EMAIL_PASS, title, content);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送邮件 (阿里云邮箱企业版)
     *
     * @param fromEmail 发送邮箱
     * @param toEmail 接收邮箱
     * @param emailName 阿里云邮箱登录名
     * @param emailPassword 密码
     * @param title 发送主题
     * @param content 发送内容
     *
     * @throws Exception
     */
    private static void sendMail(String fromEmail, String toEmail, String emailName, String emailPassword, String title,
                                 String content) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.mxhichina.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect(emailName, emailPassword);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(title);
        message.setContent(content, "text/html;charset=utf-8");
        ts.sendMessage(message, message.getAllRecipients());
    }

    /**
     * 发送邮件 (阿里云邮箱企业版)
     *
     * @param fromEmail 发送邮箱
     * @param toEmails 接收邮箱
     * @param emailName 阿里云邮箱登录名
     * @param emailPassword 密码
     * @param title 发送主题
     * @param content 发送内容
     *
     * @throws Exception
     */
    private static void sendMail(String fromEmail, String[] toEmails, String emailName, String emailPassword, String title,
                                 String content) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.mxhichina.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect(emailName, emailPassword);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, getInternetAddresses(toEmails));
        message.setSubject(title);
        message.setContent(content, "text/html;charset=utf-8");
        ts.sendMessage(message, message.getAllRecipients());
    }

    private static Address[] getInternetAddresses(String[] toEmails) throws AddressException {
        Address[] addresses = new Address[toEmails.length];
        for (int i = 0; i < toEmails.length; i++) {
            addresses[i] = new InternetAddress(toEmails[i]);
        }
        return addresses;
    }
}
