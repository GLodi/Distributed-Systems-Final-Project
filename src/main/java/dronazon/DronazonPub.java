package dronazon;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DronazonPub {
    public static void main(String[] args) {
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "dronazon/smarticity/orders";
        int qos = 2;
        int deliveryId = 0;

        //brew services start mosquitto

        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            //connOpts.setUserName(username); // optional
            //connOpts.setPassword(password.toCharArray()); // optional
            //connOpts.setWill("this/is/a/topic","will message".getBytes(),1,false);  // optional
            //connOpts.setKeepAliveInterval(60);  // optional

            // Connect the client
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected");

            while (true) {
                int pickupX, pickupY, dropX, dropY;
                do {
                    pickupX = (int) (Math.random() * 10);
                    pickupY = (int) (Math.random() * 10);
                    dropX = (int) (Math.random() * 10);
                    dropY = (int) (Math.random() * 10);
                } while (pickupX != dropX && pickupY != dropY);
                String payload = String.valueOf(deliveryId) + ',' + pickupX + ',' + pickupY + ',' + dropX + ',' + dropY;
                MqttMessage message = new MqttMessage(payload.getBytes());

                // Set the QoS on the Message
                message.setQos(qos);
                String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
                System.out.println(clientId + " Publishing message: " + payload + " " + timeStamp);
                client.publish(topic, message);
                System.out.println(clientId + " Message published");
                deliveryId += 1;
                Thread.sleep(5000);
            }

            //if (client.isConnected())
            //    client.disconnect();
            //System.out.println("Publisher " + clientId + " disconnected");

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("interrupt reason " + e.getMessage());
        }
    }
}

