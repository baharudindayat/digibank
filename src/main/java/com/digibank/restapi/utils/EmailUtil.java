package com.digibank.restapi.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailUtil {
    private final JavaMailSender javaMailSender;

    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOtpEmail(String email, String otp) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,  true, "UTF-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");

        String htmlContent = """
                <div style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;">  \s
                    <div style="background-color: #6C63FF; text-align: center; padding: 5px;">
                        <h2 style="color: #FFFFFF;">Verifikasi Akun 🔐</h2>
                    </div>
                    <p style="font-size:1.1em">Hi Sobat Digi👋,</p>
                    <p style="color: #666;">
                    Untuk memverifikasi alamat email kamu %s,\s
                    gunakan Kode OTP di bawah ini . Mohon untuk\s
                    tidak memberitahukan kode ini kepada orang lain.</p>
                   
                    <hr>
                    <div style="text-align: center;">
                        <h2 style="background: #6C63FF;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;">%s</h2>                  \s
                    </div>
                    <hr>
       
                    <p style="color: #666;">Konfirmasi email hanya dilakukan 1x dan\s
                    tidak bisa diulang. Mohon dipastikan alamat email sudah benar.😊</p>
                    
                       
                </div>
                """.formatted(email, otp);
        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);
    }
}
