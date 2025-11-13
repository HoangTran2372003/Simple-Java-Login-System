/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginmanagement;

/**
 *
 * @author tranh
 */
public class LoginMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginManagement manage = new LoginManagement();
        Validate validate = new Validate();

        while (true) {
            System.out.println("=============== Login Program");
            System.out.println("========= ");
            System.out.println("1. Add User ");
            System.out.println("2. Login ");
            System.out.println("3. Exit ");
            int choice = validate.checkIntInput("Enter Your Choice: ", 0);
            switch (choice) {
                case 1:
                    manage.addUser();
                    break;
                case 2:
                    manage.login();
                    break;
                case 3:
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

}
