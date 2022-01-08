package drones;

import java.util.Scanner;

import static drones.register.DroneRegisterThread.connectToServer;

public class DroneMain {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Enter ID (empty = 1):");
        String id = keyboard.nextLine();
        if (id.isEmpty()) {
            id = "1";
        }

        int defaultPort = 8888 + 1111 * (Integer.valueOf(id) - 1);

        System.out.println("--------------");
        System.out.println("Enter port for drone communication (empty = " + defaultPort + "):");
        String port = keyboard.nextLine();
        if (port.isEmpty()) {
            port = String.valueOf(defaultPort);
        }

        System.out.println("--------------");
        System.out.println("Enter admin server address (empty = localhost:1337):");
        String address = keyboard.nextLine();
        if (address.isEmpty()) {
            address = "localhost:1337";
        }

        DroneModel droneModel = connectToServer(id, address, port);
        if (droneModel == null) {
            System.out.println("Unable to connect. Closing.");
            return;
        }

        DroneSingleton instance = DroneSingleton.getInstance();
        instance.init(droneModel);

        // TODO: PER LA COMUNICAZIONE TRA THREAD USA UN EVENT BUS PUB/SUB
        //      sviluppalo come producer/consumer ma i consumer si sottoscrivono a specifici messaggi

        // TODO: check su anello.
        //  Se unico:
        //      si elegge master. METTI WAIT/NOTIFY SUL DIVENTARE MASTER COSI' I THREAD SI SVEGLIANO
        //  else:
        //      insertion process

        // TODO: thread election. capisce se non c'e' + master. wait/notify appena se ne accorge

        // TODO: thread avvia sensore per rilevamento inquinamento

        // TODO: thread per inviare statistiche a master (GRPC) SE NON MASTER

        // TODO: thread recharge

        // TODO: thread di checkalive

        // SE SEI MASTER USA ESEMPIO CHATSERVICEIMPL PER GESTIRE LA COMUNICAZIONE CON TUTTI I DRONI IN GRPC
        // TODO: SE MASTER. TUTTI IN WAIT/NOTIFY SU isMaster VARIABLE
        //      thread per ricevere ordini da MQTT (se drone e' master)
        //      thread per invio stats / collegamento con server (REST) (sempre master)

        int a = 1;

        while (true) {
            System.out.println("Press q to exit:");
            String command = keyboard.nextLine();
            if (command == "q") {
                // JOIN, INTERRUPT VARI THREAD DA SINGLETON E CHIUDI
                DroneSingleton.getInstance().interruptAll();
                return;
            }
        }

    }

}
