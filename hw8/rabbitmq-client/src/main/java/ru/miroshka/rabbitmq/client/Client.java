package ru.miroshka.rabbitmq.client;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;


public class Client {
    private final static String EXCHANGE_NAME = "hw8";
    private static Channel channel;
    private static String queueName;

    public static void main(String[] args) throws Exception {
        logic();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] strMass = scanner.nextLine().split(" ", 2);
            if (strMass != null && strMass.length > 0) {
                if ("set_topic".equals(strMass[0]) && strMass.length == 2) {
                    System.out.println("[*] Waiting for message from " + strMass[1]);
                    channel.queueBind(queueName, EXCHANGE_NAME, strMass[1]);
                } else if ("del_topic".equals(strMass[0]) && strMass.length == 2) {
                    System.out.println("[*] Delete subscription from " + strMass[1]);
                    channel.queueUnbind(queueName, EXCHANGE_NAME, strMass[1]);
                } else if ("exit".equals(strMass[0])) {
                    break;
                }
            }
        }


    }

    private static void logic() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
        queueName = channel.queueDeclare().getQueue();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[*] Received '" + message + "'");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }


    private void podpiska(String queueName, String titleChannel) throws IOException {
        channel.queueBind(queueName, EXCHANGE_NAME, titleChannel);
    }
}
