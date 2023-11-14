/**
 * KESHAN MOOSAI
 * 816031326
 * COMP 2603 - Object-Oriented Programming I
 * Assignment 1
 * Flight.java
 */

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private final String flightNo;
    private final String destination;
    private final String origin;
    private final LocalDateTime flightDate;
    private final LuggageManifest manifest;

    /**
     * Default Flight constructor
     * @param flightNo Six character code for a Flight
     * @param destination The airport code for the Flight’s arrival
     * @param origin The airport code for the Flight’s departure
     * @param flightDate The date and time of the Flight
     */
    Flight(String flightNo, String destination, String origin,
           LocalDateTime flightDate) {
        this.flightNo = flightNo;
        this.destination = destination;
        this.origin = origin;
        this.flightDate = flightDate;
        this.manifest = new LuggageManifest();
    }


    // Accessors

    public final String getFlightNo() { return this.flightNo; }
    public final String getDestination() { return this.destination; }
    public final String getOrigin() { return this.origin; }
    public final LocalDateTime getFlightDate() { return this.flightDate; }
    public final LuggageManifest getManifest() { return this.manifest; }


    // Methods

    /**
     * Validates whether a Passenger can check in luggage for the flight (same flight
     * number as the Passenger’s). This method updates the flight's LuggageManifest
     * and returns a String outcome.
     * @param p The Passenger object
     * @return A method result string
     */
    public String checkInLuggage (Passenger p) {
        return (Objects.equals(p.getFlightNo(), this.flightNo)) ?
                this.manifest.addLuggage(p, this) : "Invalid flight";
    }

    /**
     * Returns a string representation of the Flight object's LuggageManifest
     * @return LuggageManifest details string
     */
    public String printLuggageManifest() { return this.manifest.toString(); }

    /**
     * Returns the number of allowed luggage pieces for a given cabin class, after which
     * a cost is incurred.
     * @return The number of allowed luggage pieces
     */
    public static int getAllowedLuggage (char cabinClass) {
        return switch (cabinClass) {
            case 'F' -> 3;
            case 'B' -> 2;
            case 'P' -> 1;
            default  -> 0;
        };
    }

    /**
     * Returns a string-format of the Flight object's details
     * @return Flight details string
     */
    @Override
    public String toString() {
        return (this.flightNo + " DESTINATION: " + this.destination + " ORIGIN: " +
                this.origin + " " + this.flightDate.toString());
    }
}
