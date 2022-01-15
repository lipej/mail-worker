package dev.lipejose.mailworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.lipejose.mailworker.workers.Receiver;

@SpringBootApplication
public class MailworkerApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MailworkerApplication.class, args);
		Receiver.receiveMessages();
	}
}
