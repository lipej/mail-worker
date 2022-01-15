package dev.lipejose.mailworker.workers;

import java.util.concurrent.CountDownLatch;

import com.azure.messaging.servicebus.*;
import com.google.gson.Gson;

import dev.lipejose.mailworker.model.entity.MailMessage;


public class Receiver {
    static String connectionString = System.getenv("AZURE_CONNECTION_STRING");
    static String queueName = System.getenv("MAIL_WORKER_QUEUE_NAME");

    public static void receiveMessages() {
        CountDownLatch countdownLatch = new CountDownLatch(1);
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(Receiver::processMessage)
                .processError(context -> processError(context, countdownLatch))
                .buildProcessorClient();

        System.out.println("[Receiver]: Starting to consume messages from queue " + queueName);
        processorClient.start();
    }

    private static void processMessage(ServiceBusReceivedMessageContext context)  {
        ServiceBusReceivedMessage message = context.getMessage();

        System.out.printf("[Incoming Message]: Session: %s, Sequence #: %s.", message.getMessageId(), message.getSequenceNumber());                         

        MailMessage mail = new Gson().fromJson(message.getBody().toString(), MailMessage.class);

        Sender.send(mail);
    }

    private static void processError(ServiceBusErrorContext context, CountDownLatch countdownLatch) {
        if (!(context.getException() instanceof ServiceBusException exception)) {
            System.out.printf("[Non-ServiceBusException]: %s%n", context.getException());
            return;
        }

        System.out.printf("[ServiceBusException]: source %s, reason %s, message: %s%n",  context.getErrorSource(), exception.getReason(), exception);

        countdownLatch.countDown();

    }

}