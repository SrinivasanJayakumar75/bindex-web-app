// package bindex.bindex.features.authentication.utils;

// import java.io.UnsupportedEncodingException;
// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.MimeMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;


// @Service
// public class EmailService {
//     private final JavaMailSender mailSender;
    

//     public EmailService(JavaMailSender mailSender){
//         this.mailSender = mailSender;
//     }

//     public void sendEmail(String email, String Subject, String content) throws MessagingException, UnsupportedEncodingException {
//         MimeMessage message = mailSender.createMimeMessage();
//         MimeMessageHelper helper = new MimeMessageHelper(message);

//         helper.setFrom("no-reply@bindex.com", "Bindex");
//         helper.setTo(email);

//         helper.setSubject(Subject);
//         helper.setText(content, true);

//         mailSender.send(message);
//     }
// }
