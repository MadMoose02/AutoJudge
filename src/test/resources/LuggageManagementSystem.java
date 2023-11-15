//STUDENT ID - 816031341
import java.time.LocalDateTime;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class LuggageManagementSystem{
    public static void main (String[] args){
        LocalDateTime d = LocalDateTime.of(2023,1, 23, 10, 00, 00);
        
        try{
            String flightNo, destination, origin;
            File flightFile = new File ("FlightList.txt");
            Scanner scanner = new Scanner (flightFile);
            ArrayList<Flight> flights = new ArrayList();
            
            System.out.println("FLIGHT DETAILS:");
            System.out.println("---------------");
            while (scanner.hasNextLine()){
                flightNo = scanner.next();
                destination = scanner.next();
                origin = scanner.next();
                Flight flight = new Flight(flightNo, destination, origin, d);
                flights.add(flight);
                System.out.println(flight);
            }
            System.out.println("\n");
            
            Passenger p;
            String flightNum;
            String pps;
            String firstName;
            String lastName;
            ArrayList<Passenger> passengers = new ArrayList();
            File passengerFile = new File ("PassengerList.txt");
            
            scanner =  new Scanner (passengerFile);
            
            System.out.println("PASSENGER & LUGGAGE DETAILS:");
            System.out.println("----------------------------");
            while (scanner.hasNextLine()){
                flightNum = scanner.next();
                pps = scanner.next();
                firstName = scanner.next();
                lastName = scanner.next();
                p = new Passenger(pps, firstName, lastName,flightNum);
                passengers.add(p);
                System.out.println("Passenger Information:");
                System.out.println(p);
                for (Flight f: flights){
                    if (f.getFlightNo().equals(p.getFlightNo())){
                        System.out.println("Luggage Information:");
                        System.out.println(f.checkInLuggage(p));
                        System.out.println("\n");
                        break;
                    }
                }
            }
            
            for (Flight f:flights){
                System.out.println("LUGGAGE MANIFEST:");
                System.out.println("-----------------");
                System.out.println(f.printLuggageManifest());
            }
        }
        catch (Exception e){
            
        }
    }
}
