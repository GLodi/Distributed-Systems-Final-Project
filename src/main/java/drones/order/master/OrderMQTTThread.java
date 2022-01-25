package drones.order.master;

import drones.eventbus.EventBus;
import drones.eventbus.messages.NewOrderMessage;
import drones.order.Order;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.Timestamp;

public class OrderMQTTThread extends Thread {
    @Override
    public void run() {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smarticity/orders";
        int qos = 2;

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // Connect the client
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected - Thread PID: " + Thread.currentThread().getId());

            // Callback
            client.setCallback(new MqttCallback() {

                public void messageArrived(String topic, MqttMessage message) {
                    // Called when a message arrives from the server that matches any subscription made by the client
                    String time = new Timestamp(System.currentTimeMillis()).toString();
                    String receivedMessage = new String(message.getPayload());
                    System.out.println(clientId + " Received a Message! - Callback - Thread PID: " + Thread.currentThread().getId() +
                            "\n\tTime:    " + time +
                            "\n\tTopic:   " + topic +
                            "\n\tMessage: " + receivedMessage +
                            "\n\tQoS:     " + message.getQos() + "\n");
                    String[] split = receivedMessage.split(",");
                    int id = Integer.parseInt(split[0]);
                    int pickupX = Integer.parseInt(split[1]);
                    int pickupY = Integer.parseInt(split[2]);
                    int dropX = Integer.parseInt(split[3]);
                    int dropY = Integer.parseInt(split[4]);
                    OrderQueue.getInstance().put(new Order(id, pickupX, pickupY, dropX, dropY));
                    EventBus.getInstance().put(new NewOrderMessage());
                }

                public void connectionLost(Throwable cause) {
                    System.out.println(clientId + " Connectionlost! cause:" + cause.getMessage() + "-  Thread PID: " + Thread.currentThread().getId());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used here
                }

            });
            System.out.println(clientId + " Subscribing ... - Thread PID: " + Thread.currentThread().getId());
            client.subscribe(topic, qos);
            System.out.println(clientId + " Subscribed to topics : " + topic);


            //System.out.println("\n ***  Press a random key to exit *** \n");
            //Scanner command = new Scanner(System.in);
            //command.nextLine();
            //client.disconnect();

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }


    }
}
