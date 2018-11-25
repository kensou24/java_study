import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            IntStream.range(0, 100).forEach( (m) -> {
                IntStream.range(0, 100).forEach((i) ->
                {
                    String message = "Hello World index !" + i + ",at times " + m;
                    try {
                        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(" [x] Sent '" + message + "'");
                });

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
