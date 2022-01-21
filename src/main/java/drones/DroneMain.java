package drones;

import java.util.Scanner;

public class DroneMain {

    private static int id;
    private static String address;
    private static int port;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Enter ID (empty = 1):");
        String ids = keyboard.nextLine();
        if (ids.isEmpty()) {
            id = 1;
        } else {
            id = Integer.parseInt(ids);
        }

        int defaultPort = 8888 + 1111 * (id - 1);

        System.out.println("--------------");
        System.out.println("Enter port for drone communication (empty = " + defaultPort + "):");
        String ports = keyboard.nextLine();
        if (ports.isEmpty()) {
            port = defaultPort;
        } else {
            port = Integer.valueOf(ports);
        }

        System.out.println("--------------");
        System.out.println("Enter admin server address (empty = localhost:1337):");
        String address = keyboard.nextLine();
        if (address.isEmpty()) {
            address = "localhost:1337";
        }

        // START
        //DroneSingleton.getInstance().listenForErrors();

        DroneSingleton.getInstance().startRegisterService(id, address, port);
        if (!DroneSingleton.getInstance().initiated()) {
            System.out.println("Unable to connect. Closing.");
            return;
        }

        DroneSingleton.getInstance().startGRPCServers();

        DroneSingleton.getInstance().startGreetingsService();

        DroneSingleton.getInstance().startCheckAliveService();

        DroneSingleton.getInstance().startElectionService();

        // TODO: thread avvia sensore per rilevamento inquinamento

        // TODO: thread per inviare statistiche a master (GRPC) SE NON MASTER

        // TODO: thread recharge

        // SE SEI MASTER USA ESEMPIO CHATSERVICEIMPL PER GESTIRE LA COMUNICAZIONE CON TUTTI I DRONI IN GRPC
        // TODO: SE MASTER. TUTTI IN WAIT/NOTIFY SU isMaster VARIABLE
        //      thread per ricevere ordini da MQTT (se drone e' master)
        //      thread per invio stats / collegamento con server (REST) (sempre master)

        while (true) {
            System.out.println("Press q to exit:");
            String command = keyboard.nextLine();
            if (command.equals("q")) {
                DroneSingleton.getInstance().interruptAll();
                return;
            }
        }
    }
}
