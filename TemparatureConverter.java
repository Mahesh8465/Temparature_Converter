
import java.util.Scanner;

public class Task3 {

    // Conversion constants
    private static final double KELVIN_OFFSET = 273.15;
    private static final double FAHRENHEIT_CONVERT_FACTOR = 1.8;
    private static final double FAHRENHEIT_OFFSET = 32.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.println("Enter the temperature value:");
                double temperature = Double.parseDouble(scanner.nextLine());

                System.out.println("Enter the source scale (C for Celsius, F for Fahrenheit, K for Kelvin):");
                char sourceScale = scanner.nextLine().toUpperCase().charAt(0);

                System.out.println("Enter the target scale (C for Celsius, F for Fahrenheit, K for Kelvin):");
                char targetScale = scanner.nextLine().toUpperCase().charAt(0);

                if (isValidScale(sourceScale) && isValidScale(targetScale)) {
                    double convertedTemperature = convertTemperature(temperature, sourceScale, targetScale);
                    System.out.printf("Converted Temperature: %.2f %s%n", convertedTemperature, targetScale);
                } else {
                    System.out.println("Invalid scale entered. Please enter C, F, or K.");
                }

                System.out.println("Do you want to convert another temperature? (yes/no):");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid temperature value. Please enter a valid number.");
            }
        }

        scanner.close();
    }

    // Check if the scale is valid
    private static boolean isValidScale(char scale) {
        return scale == 'C' || scale == 'F' || scale == 'K';
    }

    // Convert temperature from source scale to target scale
    private static double convertTemperature(double temperature, char sourceScale, char targetScale) {
        // Convert the source temperature to Celsius
        double temperatureInCelsius;
        switch (sourceScale) {
            case 'C':
                temperatureInCelsius = temperature;
                break;
            case 'F':
                temperatureInCelsius = (temperature - FAHRENHEIT_OFFSET) / FAHRENHEIT_CONVERT_FACTOR;
                break;
            case 'K':
                temperatureInCelsius = temperature - KELVIN_OFFSET;
                break;
            default:
                throw new IllegalArgumentException("Invalid source scale");
        }

        // Convert Celsius to the target scale
        switch (targetScale) {
            case 'C':
                return temperatureInCelsius;
            case 'F':
                return temperatureInCelsius * FAHRENHEIT_CONVERT_FACTOR + FAHRENHEIT_OFFSET;
            case 'K':
                return temperatureInCelsius + KELVIN_OFFSET;
            default:
                throw new IllegalArgumentException("Invalid target scale");
        }
    }
}