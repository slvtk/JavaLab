package homework;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DocumentProducer {
    // есть EXCHANGE - images НЕ ОЧЕРЕДЬ
    private final static String EXCHANGE_NAME = "documents";
    // тип FANOUT
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {

            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // создаем exchange
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            User user1 = new User("Ivan","Ivanov",12);
            User user2 = new User( "Sergey","Sergeev",123123);
            Gson json = new Gson();
            channel.basicPublish(EXCHANGE_NAME, "", null, json.toJson(user1).getBytes());
            channel.basicPublish(EXCHANGE_NAME, "", null, json.toJson(user2).getBytes());

        } catch (IOException | TimeoutException e) {
           throw new IllegalArgumentException(e);
        }
    }
}
