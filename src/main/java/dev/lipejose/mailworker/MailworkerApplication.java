package dev.lipejose.mailworker;

import dev.lipejose.mailworker.workers.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailworkerApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MailworkerApplication.class, args);
		Receiver.receiveMessages();
	}
}
