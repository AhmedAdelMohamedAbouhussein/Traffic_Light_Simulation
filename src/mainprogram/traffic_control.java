package mainprogram;

import java.util.Scanner;
import java.util.InputMismatchException;

public class traffic_control extends abs_traffic_control
{

    private static Scanner scanner = new Scanner(System.in);

    public int count = 0;
    public Intersection[] intersection = new Intersection[100];
    public TrafficLightSimulation[] simulation = new TrafficLightSimulation[100];
    public int intersectionnum = 0;

    public void showMenu()
    {
        while (true) {
            System.out.println("\n###########################################\n");
            System.out.println("Welcome to Traffic Controller System!");
            System.out.println("1. Manage Four-way Intersection");
            System.out.println("2. Manage T-Junction");
            System.out.println("3. Adjust Signal Timings");
            System.out.println("4. Display Traffic Status");
            System.out.println("5. Delete intersection");
            System.out.println("6. Exit and run simulator (GUI)\n");
            int choice;
            while(true)
            {
                try
                {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    break;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. Please enter a valid integer.\n");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                }
            }

            switch (choice) {
                case 1:
                case 2:
                    controlTraffic(choice);
                    break;
                case 3:
                    adjustSignalTimings();
                    break;
                case 4:
                    displayTrafficStatus();
                    break;
                case 5:
                    deleteIntersection();
                    break;
                case 6:
                    askforsim();
                    System.out.println("Exiting Traffic Controller System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }


    public void controlTraffic(int choice)
    {
        System.out.println("\n###########################################\n");
        if (choice == 1) {
            System.out.println("Managing Four-way Intersection...\n ");
        } else if (choice == 2) {
            System.out.println("Managing T Junction Intersection...\n");
        }
        System.out.print("Enter intersection ID: ");
        String intersectionID = scanner.nextLine();

        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            if (intersection[i].getIntersectionID().equals(intersectionID)) {
                System.out.println("intersectionID already exists\n");
                return;
            }
        }

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        if (choice == 1) {
            intersection[count] = new four_way_Intersection(intersectionID, location, "4-way-intersection");
            System.out.println("\nfour way intersection \nIntersection ID: " + intersectionID + "\nIntersection Location: " + location);
            System.out.println("\n###########################################\n");

        }
        else if (choice == 2)
        {
            intersection[count] = new T_junction_intersection(intersectionID, location, "T Junction intersection");
            System.out.println("\nT Junction intersection \nIntersection ID: " + intersectionID + "\nIntersection Location: " + location);
            System.out.println("\n###########################################\n");
        }

        // Start simulation thread
        this.simulation[count] = new TrafficLightSimulation(intersection[count]);
        Thread simulationThread = new Thread(simulation[count]);
        simulationThread.start();
        count++;

    }


    public void adjustSignalTimings()
    {
        System.out.println("\n###########################################\n");
        System.out.println("Adjusting Signal Timings...\n");
        System.out.println("Enter intersection ID: ");
        String intersectionID = scanner.nextLine();

        boolean check = false;
        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            if (intersection[i].getIntersectionID().equals(intersectionID)) {
                check = true;
                break;
            }
        }
        if(!check)
        {
            System.out.println("Intersection ID does not exist\n");
            return;
        }

        int greenSignal;
        int yellowSignal;
        int redSignal;

        while(true)
        {
            try
            {
                System.out.print("Enter new timing for Green Signal (seconds): ");
                greenSignal = scanner.nextInt();
                System.out.print("Enter new timing for Yellow Signal (seconds): ");
                yellowSignal = scanner.nextInt();
                System.out.print("Enter new timing for Red Signal (seconds): ");
                redSignal = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if((greenSignal >= 0 ) && (yellowSignal >= 0 )&& (redSignal >= 0))
                {
                    break;
                }
                System.out.println("Invalid input. Please enter a valid integer.\n");
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid integer.\n");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }

        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            if(intersection[i].getIntersectionID().equals(intersectionID))
            {
                intersection[i].manageTraffic(greenSignal, yellowSignal, redSignal);
            }
        }

        System.out.println("Signal timings adjusted for Intersection " + intersectionID + " successfully!");
    }


    public void displayTrafficStatus()
    {
        System.out.println("\n###########################################\n");
        System.out.println("Displaying Traffic Status...\n");



        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            System.out.println("Intersection ID: " + intersection[i].getIntersectionID());
            System.out.println("Location: " + intersection[i].getLoctaion());

            if(simulation[i].isGreen())
            {
                System.out.println("Green Signal");
                System.out.println("Traffic Flow: no traffic" );
            }
            if(simulation[i].isYellow())
            {
                System.out.println("Yellow Signal");
                System.out.println("Traffic Flow: Normal" );
            }
            if(simulation[i].isRed())
            {
                System.out.println("Red Signal");
                System.out.println("Traffic Flow: Busy" );
            }

            System.out.println("\n");

        }

    }

    public void deleteIntersection()
    {
        System.out.println("\n###########################################\n");
        System.out.println("Deleting Intersection...\n");
        System.out.println("Enter intersection ID: ");
        String intersectionID = scanner.nextLine();

        boolean check = false;
        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            if (intersection[i].getIntersectionID().equals(intersectionID))
            {
                intersection[i] = null;
                simulation[i] = null;
                check = true;
                System.out.println("Intersection Deleted Successfully");
                break;
            }
        }
        if(!check)
        {
            System.out.println("Intersection ID does not exist\n");
        }
    }


    public void askforsim()
    {
        int count = 0;
        System.out.println("\n#########################################\n");
        System.out.println("Which intersection do you want to simulate:");

        for(int i = 0; i < intersection.length && intersection[i] != null ; i++)
        {
            System.out.println( i+1 +") Intersection ID: " + intersection[i].getIntersectionID());
            count++;
        }
        while(true)
        {
            while (true)
            {
                try
                {
                    System.out.print("Enter your choice: ");
                    intersectionnum = scanner.nextInt();

                    scanner.nextLine();  // Consume newline
                    break;

                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid input. Please enter a valid integer.\n");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                }
            }
            if(intersectionnum > 0)
            {
                if (intersection[intersectionnum - 1] != null)
                {
                    intersectionnum--;
                    break;
                }
                else
                {
                    System.out.println("Invalid input. Please enter a valid integer.\n");
                }
            }
        }
    }
}