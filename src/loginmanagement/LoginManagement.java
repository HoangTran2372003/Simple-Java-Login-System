/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginmanagement;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranh
 */
public class LoginManagement {

    private static List<AccountModel> accountList = new ArrayList<>();
    Validate validate = new Validate();

    //Ham MD5Encryption ma hoa chuoi String pasword thanh mot chuoi hexa hásh theo thuat toan MD5
    public String MD5Encryption(String password) {
        //Dung try-catch xu lý ngoai le khi thuat toan MD5 khong ton tai trong Java
        try {
            //Khoi tao doi tuong MesageDigest su dung thuat toan MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            //Khoi tao hashBytes lay du lieu nhi phan tu password chuyen thanh cac Bytes va duoc ma hoa theo dang MD5
            byte[] hashBytes = md.digest(password.getBytes()); 
            
            //Chuyen sang dang BigInteger de xu ly dang so nguyen cuc lon, tham so 1 nhan gia tri duong, tham so 2 nhan gia tri gop vao cua hashBytes
            BigInteger bigInt = new BigInteger(1, hashBytes);
            
            //Chuyen doi so nguyen bigInt sang dang String hexadecimal
            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5 algorithm not found!");
            return null;
        }
    }

    public void addUser() {
        System.out.println("--------------- Add User ---------------");
        String username;
        while (true) {
            boolean exist = false;
            username = validate.checkStringInput("Account: ");
            for (AccountModel account : accountList) {
                if (account.getUsername().equalsIgnoreCase(username)) {
                    exist = true;
                    break;
                }
            }
            if (exist == true) {
                System.out.println("There already an account with this username exist, please try again");
            } else {
                break;
            }
        }
        String password = validate.checkInputPassword("Password: ");
        String name = validate.checkStringInput("Name: ");
        String phone = validate.checkPhoneInput("Phone: ");
        String email = validate.checkEmailInput("Email: ");
        String address = validate.checkStringInput("Address: ");
        String dateOfBirth = validate.checkInputDate("Date Of Birth: ");
        
        System.out.println(MD5Encryption(password));
        accountList.add(new AccountModel(username, MD5Encryption(password), name, phone, email, address, dateOfBirth));
        System.out.println("New Account created successfully");
    }

    public void login() {
        if (accountList.isEmpty()) {
            System.out.println("The account list is empty");
            return;
        }
        System.out.println("--------------- Login ---------------");
        String username = validate.checkStringInput("Account: ");
        String password = validate.checkInputPassword("Password: ");
        AccountModel accountLogin = findAccount(accountList, username, password);
        if (accountLogin != null) {
            System.out.println("Welcome");
            System.out.print("Hi " + accountLogin.getName() + ", do you want to change the password now? ");
            changePassword(accountLogin);
        } else {
            System.out.println("Invalid username or password");
        }

    }

    public AccountModel findAccount(List<AccountModel> accountList, String username, String password) {
        for (AccountModel account : accountList) {
            if (account.getUsername().equalsIgnoreCase(username) && account.getPassword().equalsIgnoreCase(MD5Encryption(password))) {
                return account;
            }
        }
        return null;
    }

    public void changePassword(AccountModel accountLogin) {
        String choice;
        do {
            choice = validate.checkStringInput("Y/N: ");
            if (choice.length() == 1 && choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Invalid input, you can only choose Y (yes) or N (no)");
            }
        } while (true);
        
        if (choice.equalsIgnoreCase("Y")) {
            String oldPassword = validate.checkInputPassword("Old Password: ");
            String newPassword = validate.checkInputPassword("New Password: ");
            String reNewPassword = validate.checkInputPassword("Re New Password: ");
            if(!MD5Encryption(oldPassword).equalsIgnoreCase(accountLogin.getPassword())){
                System.out.println("Invalid, old password is incorrect");
            } 
            if(!newPassword.equalsIgnoreCase(reNewPassword)){
                System.out.println("Invalid, the re new password must be the same as the new password");
            }
            if(MD5Encryption(oldPassword).equalsIgnoreCase(accountLogin.getPassword()) && newPassword.equalsIgnoreCase(reNewPassword)){
                accountLogin.setPassword(MD5Encryption(newPassword));
                System.out.println("The password changed successfully");
            }
        }
    }
}

//MD5 la thuat toan ma hoa, no co kha nang chuyen dang cac input String thanh thanh cac byted duoc hashed (bam nho) va roi chuyen thanh mot String cac ky tu dang so hexadecimal 