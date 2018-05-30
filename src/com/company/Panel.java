package com.company;

import com.company.Exceptions.LoginException;

import java.util.Scanner;

public class Panel {

    private UserStore userStore;

    Scanner in = new Scanner(System.in);

    public Panel(UserStore userStore) {
        this.userStore = userStore;
    }

    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        int choice = -1;

        while(choice != 0) {
            System.out.println("==========>>> Main Menu <<<==========");
            System.out.println("Choose option: ");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Show all users");
            System.out.println("0 - Close the app");

            choice = in.nextInt();
            in.nextLine();

            try {
                switch(choice) {
                    case 1:
                        loginUser();
                        break;
                    case 2:
                        registerNewUser();
                        break;
                    case 3:
                        userStore.printUserStoreMap();
                        break;
                }
            } catch(LoginException loginException) {
                System.out.println(loginException.getMessage());
            }
        }
    }

    private void loginUser() throws LoginException {
        String loginName;
        String loginPassword;

        System.out.println("==========>>> Login <<<==========");
        System.out.print("Name:  ");
        loginName = in.nextLine();

        System.out.print("Password:  ");
        loginPassword = in.nextLine();

        User loggedUser = userStore.login(loginName, loginPassword);

        if(loggedUser != null) {
            System.out.println("Logged in!");
            userMenu(loggedUser);
        } else {
            throw new LoginException("Invalid combination of password and name.");
        }
    }

    private void userMenu(User user) {
        int choice = -1;

        while(choice != 0) {
            System.out.println("==========>>> User Menu <<<==========");
            System.out.println("Choose option: ");
            System.out.println("1 - Print your data");
            System.out.println("2 - change your email");
            System.out.println("3 - change your phone number");
            System.out.println("0 - Log out");

            choice = in.nextInt();
            in.nextLine();

            switch(choice) {
                case 1:
                    printUser(user);
                    break;
                case 2:
                    changeEmail(user);
                    System.out.println("Changing email done.");
                    break;
                case 3:
                    changePhone(user);
                    System.out.println("Changing phone number done.");
                    break;
            }
        }
    }

    private void changeEmail(User user) {
        isValidMail(user);
    }
    private void changePhone(User user) {
        isValidPhone(user);
    }

    private void registerNewUser() {
        User newUser = new User();

        System.out.println("==========>>> Register <<<==========");

        // name input & validation
        System.out.print("Name:  ");
        String userName = in.nextLine();

        while(!Validator.validateName(userName, userStore)) {
            System.out.println("Name too short or name is already taken. Try again: ");
            userName = in.nextLine();
        }
        newUser.setName(userName);

        // password input & validate
        isValidPassword(newUser);

        // email input & validate
        isValidMail(newUser);

        // phone input & validate
        isValidPhone(newUser);

        // Sending new user to database
        userStore.registerUser(newUser);
        System.out.println("Registration successful.");
        onSuccess(newUser);
    }

    private void isValidPassword(User user) {
        System.out.print("Password(optional): ");
        String userPassword ="";
        boolean isOk = false;

        while(!isOk) {
            userPassword = in.nextLine();
            if(userPassword.length() == 0){
                userPassword = PasswordGenerator.generatePassword(8);
                isOk = true;
            } else {
                String errorMessage = Validator.validatePassword(userPassword);
                if(errorMessage.equals("")) {
                    isOk = true;
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        user.setPassword(userPassword);
    }

    private void isValidMail(User user) {
        System.out.print("E-mail address:  ");
        String userEmail = in.nextLine();
        while(!Validator.validateMail(userEmail)){
            System.out.print("Invalid email. Try again:  ");
            userEmail = in.nextLine();
        }
        user.setEmail(userEmail);
    }

    private void isValidPhone(User user) {
        System.out.print("Telephone number:  ");
        String userPhoneNumber = in.nextLine();
        while(!Validator.validatePhoneNumber(userPhoneNumber)){
            System.out.println("Invalid phone number. Try again: ");
            userPhoneNumber = in.nextLine();
        }
        user.setTelNumber(userPhoneNumber);
    }

    private void onSuccess(User user) {
        System.out.println("Logged as --> " + user.getName());
        userMenu(user);
    }


    private void printUser(User user) {
        System.out.println(user.toString());
    }

}
