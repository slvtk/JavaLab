package homework.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ImagesProducer {

    private final static String PNG_QUEUE = "png_queue";

    private final static String JPG_QUEUE = "jpg_queue";

    private final static String PNG_ROUTING_KEY = "png";

    private final static String JPG_ROUTING_KEY = "jpg";

    private final static String IMAGES_EXCHANGE = "images";

    private final static String EXCHANGE_TYPE = "direct";

    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // создали Exchange
            channel.exchangeDeclare(IMAGES_EXCHANGE, EXCHANGE_TYPE);
            // привязали очереди под определенным routingKey
            channel.queueBind(PNG_QUEUE, IMAGES_EXCHANGE, PNG_ROUTING_KEY);
            channel.queueBind(JPG_QUEUE, IMAGES_EXCHANGE, JPG_ROUTING_KEY);

            File file = new File("images.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentFile;
            while ((currentFile = reader.readLine()) != null) {
                // в зависимости от типа файла отправляем его в конкретную очередь
                String currentRouting = currentFile.substring(currentFile.lastIndexOf(".") + 1);

                if (currentRouting.equals("jpg")) {
                    currentRouting = "jpg";
                }else if (currentRouting.equals("png")){
                    currentRouting = "png";
                }
                channel.basicPublish(IMAGES_EXCHANGE, currentRouting, null, currentFile.getBytes());
            }

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
