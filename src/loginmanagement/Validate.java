/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author tranh
 */
public class Validate {

    Scanner scanner = new Scanner(System.in);
    private static final String PHONE_VALID = "^\\d{10,11}$";
    private static final String EMAIL_VALID = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_VALID = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public int checkIntInput(String message, int min) {
        do {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                int result = Integer.parseInt(input);
                if (result < min) {
                    System.out.println("Invalid input, the input number must be larger than" + min);
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, the input must be an integer number");
            }
        } while (true);
    }

    public String checkStringInput(String message) {
        do {
            System.out.print(message);
            String result = scanner.nextLine();
            if (result.trim().isEmpty()) {
                System.out.println("Invalid input, the input must not be blank");
                continue;
            }
            return result;
        } while (true);
    }

    public String checkPhoneInput(String message) {
        do {
            System.out.print(message);
            String result = scanner.nextLine().trim();
            if (result.length() != 0 && result.matches(PHONE_VALID)) {
                return result;
            } else {
                System.out.println("Invalid input, the input must follow correct phone number format (10 to 11 numbers without special characters)");
            }
        } while (true);
    }

    public String checkEmailInput(String message) {
        do {
            System.out.print(message);
            String result = scanner.nextLine().trim();
            if (result.length() != 0 && result.matches(EMAIL_VALID)) {
                return result;
            } else {
                System.out.println("Invalid input, the input must follow correct email format");
            }
        } while (true);
    }

    public String checkInputDate(String message) {
        do {
            try {
                System.out.println(message);
                String date = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date result = dateFormat.parse(date);
                if (date.equals(dateFormat.format(result))) {
                    return date;
                } else {
                    System.out.println("Please enter the date that follow the format dd/MM/yyyy");
                }

            } catch (ParseException e) {
                System.out.println("Date must be in format dd/MM/yyyy");
            }
        } while (true);
    }

    public String checkInputPassword(String message) {
        do {            
            System.out.print(message);
            String result = scanner.nextLine();
            if(result.length() != 0 && result.matches(PASSWORD_VALID)){
                return result;
            } else{
                System.out.println("Invalid input, the password must not be blank and contain at least 1 number, uppercase, lowercase and special character");
            }
        } while (true);
    }
}
