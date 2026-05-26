/*
Exercise 2 — Paycheck & Overtime Calculator
Focus: variables, primitives, operators, type conversion, branching, formatted output

Compile/Run:
  javac PaycheckOvertime_Starter.java
  java PaycheckOvertime_Starter
*/

import java.util.Scanner;

public class PaycheckOvertime {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Named constants eliminate magic numbers and make the overtime rules explicit.
        final double OVERTIME_THRESHOLD  = 40.0;  // hours before overtime kicks in
        final double OVERTIME_MULTIPLIER = 1.5;   // overtime pay rate multiplier

        System.out.println("=== Paycheck & Overtime Calculator ===");

        // String stores the employee name (non-primitive, but required by the spec).
        System.out.print("Employee name: ");
        String name = input.nextLine();

        // double is used for hours and rate because both can have fractional values
        // (e.g., 42.5 hours worked, $18.75/hr).
        System.out.print("Hours worked: ");
        double hours = input.nextDouble();

        System.out.print("Hourly rate ($): ");
        double rate = input.nextDouble();

        // int stores the whole-number retirement percentage entered by the user
        // (e.g., the user types 5, meaning 5%).
        System.out.print("Retirement contribution percent (0-100): ");
        int percent = input.nextInt();

        // char holds a single Y or N character to decide the output format.
        // input.next() skips whitespace, then we normalize to uppercase for comparison.
        System.out.print("Print detailed pay stub? (Y/N): ");
        char printFlag = input.next().toUpperCase().charAt(0);

        // Validate all inputs before computing anything.
        if (hours < 0) {
            System.out.println("Error: Hours worked cannot be negative.");
            input.close();
            return;
        }
        if (rate < 0) {
            System.out.println("Error: Hourly rate cannot be negative.");
            input.close();
            return;
        }
        if (percent < 0 || percent > 100) {
            System.out.println("Error: Retirement percentage must be between 0 and 100.");
            input.close();
            return;
        }

        // Regular hours are capped at the overtime threshold.
        double regularHours  = Math.min(hours, OVERTIME_THRESHOLD);

        // Hours beyond the threshold are overtime; Math.max prevents a negative result
        // when hours <= 40.
        double overtimeHours = Math.max(hours - OVERTIME_THRESHOLD, 0.0);

        // Gross pay: regular pay at the base rate + overtime pay at the multiplied rate.
        double grossPay = (regularHours * rate) + (overtimeHours * rate * OVERTIME_MULTIPLIER);

        // Type conversion: dividing the int percent by 100.0 forces floating-point division
        // so that, e.g., 5 / 100.0 = 0.05 rather than the integer result 0.
        double retirementRate      = percent / 100.0;
        double retirementDeduction = grossPay * retirementRate;

        // Net pay: subtract the pre-tax retirement contribution from gross pay.
        double netPay = grossPay - retirementDeduction;

        System.out.println("\n--- Pay Results ---");

        if (printFlag == 'Y') {
            // Detailed pay stub with every line item broken out.
            System.out.println("===========================================");
            System.out.printf("  Employee:           %-20s%n", name);
            System.out.println("-------------------------------------------");
            System.out.printf("  Regular hours:      %.2f hrs @ $%.2f/hr%n",
                    regularHours, rate);
            System.out.printf("  Overtime hours:     %.2f hrs @ $%.2f/hr%n",
                    overtimeHours, rate * OVERTIME_MULTIPLIER);
            System.out.println("-------------------------------------------");
            System.out.printf("  Gross pay:          $%,.2f%n", grossPay);
            System.out.printf("  Retirement (%d%%):  -$%,.2f%n", percent, retirementDeduction);
            System.out.println("-------------------------------------------");
            System.out.printf("  Net pay:            $%,.2f%n", netPay);
            System.out.println("===========================================");
        } else {
            // Summary-only output when the user declines a full stub.
            System.out.printf("Employee: %s%n", name);
            System.out.printf("Net pay:  $%,.2f%n", netPay);
        }

        input.close();
    }
}

