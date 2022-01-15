package dev.lipejose.mailworker.workers;

import java.io.IOException;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

import dev.lipejose.mailworker.model.entity.MailMessage;

public class Sender {
    private static Mail build(MailMessage mail) {
        Email from = new Email(mail.from);
        Email to = new Email(mail.to);
        String subject = mail.subject;
        Content content = new Content("text/html", mail.content);

        return new Mail(from, subject, to, content);
    }

    public static void send(MailMessage mailMessage) {
        try {
            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

            Mail mail = build(mailMessage);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() == 202) {
                System.out.println("[SENDER] Email sent successfully");
            } else {
                throw new IOException("[SENDER] ERROR, message: " + response.getBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
