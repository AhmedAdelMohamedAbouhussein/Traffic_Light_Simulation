
Traffic Control Simulation System

A Java-based traffic management simulator that models intersections with configurable traffic lights, real-time vehicle movement, and a graphical interface. This project demonstrates object-oriented programming (OOP), multithreading, and Swing-based GUI development.


📌 Features
1. Intersection Management
Create & customize intersections (4-way and T-junctions)

Adjust traffic light timings (green, yellow, red) dynamically

Delete intersections from the system

Real-time status monitoring for all intersections

2. Traffic Simulation
Multi-threaded traffic light control (each intersection runs independently)

Vehicle movement that responds to traffic signals

Graphical visualization of traffic flow (using Java Swing)

3. User Interface
Console-based menu for managing intersections

Interactive GUI showing live traffic simulation

Dynamic updates when signal timings change

🛠️ Technologies Used
Java SE (Core Java, OOP concepts)

Java Swing (for GUI rendering)

Multithreading (for concurrent traffic light control)

File I/O (for loading map layouts)

🚀 How to Run
Compile the project:
javac mainprogram/originalmain.java

Run the application:
java mainprogram.originalmain

Use the console menu to:
Add intersections (4-way or T-junction)
Adjust traffic light timings
View traffic status
Launch the GUI simulation

📂 Project Structure
src/
├── mainprogram/            # Core traffic control logic
│   ├── abs_traffic_control.java   # Abstract traffic control class
│   ├── Intersection.java          # Base intersection class
│   ├── four_way_Intersection.java # 4-way intersection logic
│   ├── T_junction_intersection.java # T-junction logic
│   ├── traffic_control.java       # Main controller
│   ├── TrafficLightSimulation.java # Traffic light timing thread
│   └── originalmain.java          # Entry point (GUI setup)
│
└── mainGUI/                # Graphical components
    ├── Entity/
    │   └── cars.java       # Vehicle movement logic
    ├── Gamepanel/
    │   └── game2.java      # Main simulation panel
    └── Tile_Manager/
        └── tilemanager.java # Tile-based map rendering

🎯 Key Concepts Demonstrated
✔ OOP Principles (Abstraction, Inheritance, Polymorphism)
✔ Multithreading (Independent traffic light control)
✔ Swing GUI (Interactive visualization)
✔ File I/O (Loading map layouts from text files)
>>>>>>> 69723bb (Add TrafficController.java)
