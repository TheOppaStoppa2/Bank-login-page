import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to 43th Street Bank.");
        System.out.println("Please enter your Username and Password.");
        System.out.println("If you are new here and would like to create an account, please type 'Create Account'.");
        System.out.println("If you already have an account, please type 'Login'.");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toLowerCase().trim();
        String s = "create account";
        String g = "login";
        
        if (userInput.equals(s)) {
            try {
                System.out.println("Welcome to 43th Street Bank");
                System.out.println("Please enter a Username. Note: A username can only include numbers and should not be more than 9 characters.");
                
                String newUsername = scanner.nextLine();
                
                if (newUsername.matches("\\d+") && newUsername.length() <= 9) {
                    System.out.println("Please retype your username to confirm");
                    String tempUsername = scanner.nextLine();
                    
                    if (newUsername.equals(tempUsername)) {
                        System.out.println("Please enter a Password. Note: A password can only include characters and should not be more than 10 characters.");
                        String newPassword = scanner.nextLine();
                        
                        if (newPassword.length() <= 10) {
                            System.out.println("Please retype your password to confirm");
                            String tempPassword = scanner.nextLine();
                            
                            if (newPassword.equals(tempPassword)) {
                                System.out.println("Congrats on creating your new account with 43th Street Bank. Please reload the page to login again.");
                                saveCredentials(newUsername, newPassword);
                            } else {
                                System.out.println("Passwords did not match. Please reload the page and try again.");
                            }
                        } else {
                            System.out.println("Your password exceeded 10 characters. Please reload the page and try again.");
                        }
                    } else {
                        System.out.println("Usernames did not match. Please reload the page and try again.");
                    }
                } else {
                    System.out.println("Invalid username. It must be a number and not exceed 9 characters. Please reload the page and try again.");
                }
            } catch (Exception e) {
                System.out.println("Your input was wrong somewhere. Please reload the page and try again. Error: " + e);
            }
        }
        
        if (userInput.equals(g)) {
            // Login flow
            System.out.println("Please enter your Username:");
            String username = scanner.nextLine();
            
            System.out.println("Please enter your Password:");
            String password = scanner.nextLine();
            
            // Validate login
            boolean isValid = validateLogin(username, password);
            
            if (isValid) {
                System.out.println("Login successful! Welcome to 43th Street Bank.");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
        
        scanner.close();
    }

    // Save user credentials into a file
    public static void saveCredentials(String newUsername, String newPassword) {
        try {
            FileWriter writer = new FileWriter("users.txt", true); // Append mode
            writer.write(newUsername + ":" + newPassword + "\n"); // Write username and password
            writer.close();
            System.out.println("Account created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving your credentials.");
            e.printStackTrace();
        }
    }

    // Validate user login by reading the credentials from the file
    public static boolean validateLogin(String username, String password) {
        try {
            File file = new File("users.txt"); // Open the users file
            Scanner fileScanner = new Scanner(file);
            
            while (fileScanner.hasNextLine()) { // Read each line
                String line = fileScanner.nextLine();
                String[] parts = line.split(":"); // Split by colon to get username and password
                
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    fileScanner.close();
                    return true; // Login successful
                }
            }
            fileScanner.close(); // Close file scanner
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found.");
            e.printStackTrace();
        }
        return false; // If no match is found
    }
}
