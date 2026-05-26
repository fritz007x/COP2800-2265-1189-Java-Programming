/*
Exercise 1 — Trip Cost & Fuel Analyzer
Focus: variables, primitive types, operators, input, if/else, formatted output

Compile/Run:
  javac TripCostAnalyzer_Starter.java
  java TripCostAnalyzer_Starter
*/

import java.util.Scanner;

public class TripCostAnalyzer {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Named constant: minimum realistic MPG, values below this likely indicate
        // bad user input (e.g., 0.5 mpg for a normal vehicle makes no sense).
        final double MIN_MPG = 1.0;

        System.out.println("=== Trip Cost & Fuel Analyzer ===");

        // double is chosen for distance, mpg, and price because real-world
        // measurements have fractional values.
        System.out.print("Distance (miles): ");
        double distance = input.nextDouble();

        System.out.print("Fuel efficiency (mpg): ");
        double mpg = input.nextDouble();

        System.out.print("Gas price per gallon ($): ");
        double pricePerGallon = input.nextDouble();

        // int is used for passengers because a person is a whole, indivisible unit.
        System.out.print("Passengers (whole number): ");
        int passengers = input.nextInt();

        // Validate inputs using if/else:
        //        // - distance > 0
        //        // - mpg > 0
        //        // - pricePerGallon >= 0
        //        // - passengers >= 0
        //        // If invalid, print a clear message and exit (return).
        if (distance <= 0) {
            System.out.println("Error: Distance must be greater than 0.");
            input.close();
            return;
        }
        if (mpg < MIN_MPG) {
            System.out.println("Error: MPG must be at least " + MIN_MPG + ".");
            input.close();
            return;
        }
        if (pricePerGallon < 0) {
            System.out.println("Error: Price per gallon cannot be negative.");
            input.close();
            return;
        }
        if (passengers < 0) {
            System.out.println("Error: Number of passengers cannot be negative.");
            input.close();
            return;
        }

        // Gallons needed: divide total distance by vehicle efficiency.
        double gallonsNeeded = distance / mpg;

        // Total fuel cost: multiply gallons consumed by the price per gallon.
        double totalCost = gallonsNeeded * pricePerGallon;

        // Cost per mile: spread the total fuel expense across every mile driven.
        double costPerMile = totalCost / distance;

        // boolean tracks whether the per-passenger calculation is valid to display.
        // Assumption: 0 passengers means solo travel; skip the split to avoid division by zero.
        boolean showPerPassenger = passengers > 0;
        double costPerPassenger = 0.0;
        if (showPerPassenger) {
            // Divide total fuel cost evenly among all passengers.
            costPerPassenger = totalCost / passengers;
        }

        // Print all results formatted to two decimal places for currency/precision.
        System.out.println("\n--- Results ---");
        System.out.printf("Distance:            %.2f miles%n",      distance);
        System.out.printf("Fuel efficiency:     %.2f mpg%n",        mpg);
        System.out.printf("Gas price:           $%.2f / gallon%n",  pricePerGallon);
        System.out.printf("Gallons needed:      %.2f gal%n",        gallonsNeeded);
        System.out.printf("Total fuel cost:     $%.2f%n",           totalCost);
        System.out.printf("Cost per mile:       $%.2f%n",           costPerMile);
        if (showPerPassenger) {
            System.out.printf("Cost per passenger:  $%.2f  (%d passengers)%n",
                    costPerPassenger, passengers);
        } else {
            System.out.println("Cost per passenger:  N/A (no passengers entered)");
        }

        input.close();
    }
}

