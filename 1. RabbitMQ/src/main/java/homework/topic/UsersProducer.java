package homework.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UsersProducer {

    private final static String USERS_EXCHANGE = "users";

    private final static String EXCHANGE_TYPE = "topic";

    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(USERS_EXCHANGE, EXCHANGE_TYPE);
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

