package ru.miroshka.rabbitmq.server;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class server {
    private final static String EXCHANGE_NAME = "hw8";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Scanner scanner = new Scanner(System.in);
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);
            while (true) {
                String[] strMass = scanner.nextLine().split(" ", 2);
                if (strMass != null && strMass.length > 0) {
                    if ("exit".equals(strMass[0])) {
                        break;
                    } else if (strMass.length == 2) {
                        System.out.println("[*] Send '" + strMass[1] + "' " + "to " + strMass[0]);
                        channel.basicPublish(EXCHANGE_NAME, strMass[0], null, strMass[1].getBytes(StandardCharsets.UTF_8));
                    }


                }
            }
        }
    }

}
