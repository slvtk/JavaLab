package homework.fanaout;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class HolidayConsumer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String HOLIDAY_QUEUE = "holiday_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(HOLIDAY_QUEUE, EXCHANGE_NAME, "");
            channel.basicQos(3);
            channel.basicConsume(HOLIDAY_QUEUE, true, (consumerTag, message) -> {
                Application holiday = new Application(UUID.randomUUID().toString(),"Отпуск");
                Gson json = new Gson();
                User user = json.fromJson(new String(message.getBody()),User.class);
                PdfGenerator.generatePdf(user,holiday);
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
