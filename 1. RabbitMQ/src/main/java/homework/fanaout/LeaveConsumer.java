package homework.fanaout;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class LeaveConsumer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String LEAVE_QUEUE = "leave_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(LEAVE_QUEUE, EXCHANGE_NAME, "");
            channel.basicQos(3);
            channel.basicConsume(LEAVE_QUEUE, true, (consumerTag, message) -> {
                Application leave = new Application(UUID.randomUUID().toString(), "Увольнение");
                Gson json = new Gson();
                User user = json.fromJson(new String(message.getBody()), User.class);
                PdfGenerator.generatePdf(user, leave);
            }, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}