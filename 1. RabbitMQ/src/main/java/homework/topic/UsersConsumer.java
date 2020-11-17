package homework.topic;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import homework.fanaout.User;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UsersConsumer {
    private final static String USERS_ROUTING_KEY = "users.#";
    private final static String USERS_EXCHANGE = "users";
    private final static String USERS_QUEUE = "users_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.queueBind(USERS_QUEUE, USERS_EXCHANGE, USERS_ROUTING_KEY);
            channel.basicConsume(USERS_QUEUE, false, (consumerTag, message) -> {
                Gson gson = new Gson();
                User user = gson.fromJson(new String(message.getBody()), User.class);
                System.out.println("Hey, my name is " + user.getName() + " " + user.getSurname() + " and i'm just user!");
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
