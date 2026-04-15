import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService service = new UserService();

        int choice;

        do {
            System.out.println("\n============= USER SYSTEM ===============");
            System.out.println("1. Register User");
            System.out.println("2. Show Users");
            System.out.println("3. Update Users");
            System.out.println("4. Delete  Users");
            System.out.println("5. Exit");

            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); //clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        System.out.println("All fields are required!");
                        break;
                    }
                    service.registerUser(username, email, password);
                    break;
                case 2:
                    System.out.println("Registered Users: ");
                    service.showUsers();
                    break;
                case 3:
                    System.out.print("Enter current email: ");
                    String currentEmail = sc.nextLine();

                    System.out.print("Enter new username: ");
                    String newUsername = sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter new password: ");
                    String newPassword = sc.nextLine();

                    service.updateEmail(currentEmail, newEmail);
                    service.updateUser(currentEmail, newUsername, newPassword);
                    break;
                case 4:
                    System.out.print("Enter email to delete user: ");
                    String delEmail = sc.nextLine();

                    service.deleteUser(delEmail);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");

            }
        }while (choice != 5);
        sc.close();
    }
}
