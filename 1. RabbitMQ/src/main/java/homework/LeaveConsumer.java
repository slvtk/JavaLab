package homework;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.FileOutputStream;
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
                String DEST = "results/leave/"+UUID.randomUUID().toString()+".pdf";
                String userStr = new String(message.getBody());
                File file = new File(DEST);
                file.getParentFile().mkdirs();
                System.out.println("Распечатал "+userStr + " "+ DEST);
                FileOutputStream fos = new FileOutputStream(DEST);
                PdfWriter writer = new PdfWriter(fos);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);
                document.add(new Paragraph("APPLICATION FOR LEAVE"));
                document.add(new Paragraph(userStr));
                document.close();
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}