# Unimi Distributed and Pervasive Systems Final Project

This is my final project for the Distributed and Pervasive Systems course at Unimi.
It emulates a distributed network of drones in charge of delivering goods in a city (grid). They communicate with each other through gRPC while updating a remote server over REST.
A master drone is responsible for taking orders from an MQTT client and delegating them to the closest available drone for delivery.
It features ring-based election as well as mutual exclusion (Ricart Agrawala) distributed algorithms.

Mains:

- `drones/DroneMain.java`
- `dronazon/DronazonPub.java`
- `admin/server/ServerAdmin.java`
- `admin/client/ClientAdmin.java`

# Project Setup

* You can import the project directly from a *Version Control System*, by providing the following URL:
  TODO

<img src = './assets/img_1.png'>

* Otherwise, take care to import the project as a Gradle Project

* If required, trust the project and accept the Gradle auto-import

* Wait until the Gradle indexing process ends (it may take a few seconds)

## ENJOY THE PROJECT!

<img src = './assets/meme.PNG' width="350" height="500">


