package drones.sensors;

import drones.sensors.sim.PM10Simulator;

public class SensorsLogic extends Thread {
    @Override
    public void run() {
        runSimulator();
    }

    private void runSimulator() {
        try {
            PM10Simulator pm10Simulator = new PM10Simulator(SensorsBuffer.getInstance());
            pm10Simulator.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sensors SensorsLogic loop ERROR: " + e.getLocalizedMessage());
        }
    }
}
