package ATM;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Account> data = new HashMap<Integer, Account>();

    public void getLogin() throws IOException {
        boolean end = false;
        int customerNumber = 0;
        int pinNumber = 0;
        while (!end) {
            try {
                System.out.print("\nEnter your customer number: ");
                customerNumber = menuInput.nextInt();
                System.out.print("\nEnter your PIN number: ");
                pinNumber = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Account acc = (Account) pair.getValue();
                    if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                        getAccountType(acc);
                        end = true;
                        break;
                    }
                }
                if (!end) {
                    System.out.println("\nWrong Customer Number or Pin Number");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Character(s). Only Numbers.");
            }
        }
    }

    public void getAccountType(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSelect the account you want to access: ");
                System.out.println(" Type 1 - Checkings Account");
                System.out.println(" Type 2 - Savings Account");
                System.out.println(" Type 3 - Exit");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void getChecking(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCheckings Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds");
                System.out.println(" Type 5 - Exit");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
                        break;
                    case 2:
                        acc.getCheckingWithdrawInput();
                        break;
                    case 3:
                        acc.getCheckingDepositInput();
                        break;

                    case 4:
                        acc.getTransferInput("Checkings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void getSaving(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSavings Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds");
                System.out.println(" Type 5 - Exit");
                System.out.print("Choice: ");
                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        acc.getsavingWithdrawInput();
                        break;
                    case 3:
                        acc.getSavingDepositInput();
                        break;
                    case 4:
                        acc.getTransferInput("Savings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void createAccount() throws IOException {
        int cst_no = 0;
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nEnter your customer number ");
                cst_no = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (!data.containsKey(cst_no)) {
                        end = true;
                    }
                }
                if (!end) {
                    System.out.println("\nThis customer number is already registered");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nEnter PIN to be registered");
        int pin = menuInput.nextInt();
        data.put(cst_no, new Account(cst_no, pin));
        System.out.println("\nYour new account has been successfuly registered!");
        System.out.println("\nRedirecting to login.............");
        getLogin();
    }

    public void mainMenu() throws IOException {
        data.put(952141, new Account(952141, 191904, 1000, 5000));
        data.put(123, new Account(123, 123, 20000, 50000));
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\n Type 1 - Login");
                System.out.println(" Type 2 - Create Account");
                System.out.print("\nChoice: ");
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1:
                        getLogin();
                        end = true;
                        break;
                    case 2:
                        createAccount();
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nThank You for using this ATM.\n");
        menuInput.close();
        System.exit(0);
    }
}





























/*//import required classes and packages
import java.util.Scanner;

//create ATMExample class to implement the ATM functionality
public class OptionMenu
{
    //main method starts
    public static void main(String args[] )
    {
        //declare and initialize balance, withdraw, and deposit
        int balance = 100000, withdraw, deposit;

        //create scanner class object to get choice of user
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("Automated Teller Machine");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Check Balance");
            System.out.println("Choose 4 for EXIT");
            System.out.print("Choose the operation you want to perform:");

            //get choice from user
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.print("Enter money to be withdrawn:");

                    //get the withdrawl money from user
                    withdraw = sc.nextInt();

                    //check whether the balance is greater than or equal to the withdrawal amount
                    if(balance >= withdraw)
                    {
                        //remove the withdrawl amount from the total balance
                        balance = balance - withdraw;
                        System.out.println("-------Please collect your money--------");
                    }
                    else
                    {
                        //show custom error message
                        System.out.println("-----------Insufficient Balance-----------");
                    }
                    System.out.println("");
                    break;

                case 2:

                    System.out.print("Enter money to be deposited:");

                    //get deposite amount from te user
                    deposit = sc.nextInt();

                    //add the deposit amount to the total balanace
                    balance = balance + deposit;
                    System.out.println("----------Your Money has been successfully deposited-----------");
                    System.out.println("");
                    break;

                case 3:
                    //displaying the total balance of the user
                    System.out.println("Balance : "+balance);
                    System.out.println("");
                    break;

                case 4:
                    //exit from the menu
                    System.exit(0);
            }
        }
    }
}*/