package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Cabin[] _cabins = new Cabin[12];
    private static Queue[] _queue = new Queue[12];

    public Main() {
    }

    public static void main(String[] var0) {
        initialise();
        clearConsole();
        displayMenu();
    }

    private static void displayMenu() {
        Scanner var0 = new Scanner(System.in);
        StringBuilder var1 = new StringBuilder();
        var1.append("==============================================================\n");
        var1.append("                    Cruise Ship Boarding Menu\n");
        var1.append("==============================================================\n\n");
        var1.append("(A) Add new passenger\n");
        var1.append("(V) View all cabins\n");
        var1.append("(E) View empty cabin\n");
        var1.append("(D) Remove exsisting passenger\n");
        var1.append("(F) Find cabin from customer name\n");
        var1.append("(S) Save program data\n");
        var1.append("(L) Load program data\n");
        var1.append("(O) Sort passengers by name\n");
        var1.append("(T) View Expenses\n");
        var1.append("(B) Exit\n\n");
        var1.append("Enter your choise : ");
        System.out.print(var1.toString());
        String var2 = var0.next().toLowerCase();
        char var3 = var2.charAt(0);
        switch(var3) {
            case 'a':
                addNewCustomer();
                break;
            case 'b':
                return;
            case 'c':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'n':
            case 'p':
            case 'q':
            case 'r':
            case 'u':
            default:
                System.out.println("Invalid Input Try Again!");
                displayMenu();
                break;
            case 'd':
                deleteCustomer();
                break;
            case 'e':
                viewAllEmptyCabins();
                break;
            case 'f':
                findCabinNumber();
                break;
            case 'l':
                loadData();
                break;
            case 'o':
                viewCabinsByOrder();
                break;
            case 's':
                saveData();
                break;
            case 't':
                viewExpences();
                break;
            case 'v':
                viewAllCabins();
        }

    }

    private static void addNewCustomer() {
        while(true) {
            Scanner var0 = new Scanner(System.in);
            System.out.print("\nEnter Cabin Number : ");
            int var1 = var0.nextInt();
            if (var1 < 13 && var1 > 0) {
                int var2;
                Passenger[] var3;
                int var4;
                String var5;
                String var6;
                int var7;
                if (_cabins[var1 - 1].is_cabin_free()) {
                    System.out.print("Enter Passenger Count : ");
                    var2 = var0.nextInt();
                    if (var2 < 4 && var2 > 0) {
                        var3 = new Passenger[var2];

                        for(var4 = 0; var4 < var2; ++var4) {
                            var0.nextLine();
                            System.out.print("\nEnter Customer First Name : ");
                            var5 = var0.nextLine();
                            System.out.print("Enter Customer  Surname : ");
                            var6 = var0.nextLine();
                            System.out.print("Enter Customer Expenses : ");
                            var7 = var0.nextInt();
                            var3[var4] = new Passenger(var5, var6, var7);
                        }

                        _cabins[var1 - 1] = new Cabin(var1, false, var3);
                        waiting();
                    } else {
                        System.out.println("Count must be between 1 - 3");
                    }
                } else if (!_queue[var1 - 1].isFree()) {
                    System.out.println("Cabin is already occupied");
                } else {
                    System.out.print("Enter Passenger Count : ");
                    var2 = var0.nextInt();
                    if (var2 < 4 && var2 > 0) {
                        var3 = new Passenger[var2];

                        for(var4 = 0; var4 < var2; ++var4) {
                            var0.nextLine();
                            System.out.print("\nEnter Customer First Name : ");
                            var5 = var0.nextLine();
                            System.out.print("Enter Customer  Surname : ");
                            var6 = var0.nextLine();
                            System.out.print("Enter Customer Expenses : ");
                            var7 = var0.nextInt();
                            var3[var4] = new Passenger(var5, var6, var7);
                        }

                        _queue[var1 - 1] = new Queue(var1, false, var3);
                        waiting();
                    } else {
                        System.out.println("Count must be between 1 - 3");
                    }
                }
            } else {
                System.out.println("Invalid Cabin Number");
            }
        }
    }

    private static void viewAllCabins() {
        for(int var0 = 0; var0 < 12; ++var0) {
            if (_cabins[var0].getPassengers() != null) {
                System.out.println("Cabin " + (var0 + 1) + " is occupied by " + _cabins[var0].getPassengers().length + " passengers");
            } else {
                System.out.println("Cabin " + (var0 + 1) + " is occupied by 0 passengers");
            }
        }

        waiting();
    }

    private static void viewAllEmptyCabins() {
        for(int var0 = 0; var0 < 12; ++var0) {
            if (_cabins[var0].getPassengers() == null) {
                System.out.println("Cabin " + (var0 + 1) + " is empty");
            }
        }

        waiting();
    }

    private static void deleteCustomer() {
        while(true) {
            Scanner var0 = new Scanner(System.in);
            System.out.print("Enter Cabin Number : ");
            int var1 = var0.nextInt();
            if (var1 < 13 && var1 > 0) {
                if (_cabins[var1 - 1].is_cabin_free()) {
                    System.out.println("Cabin is already empty");
                } else {
                    _cabins[var1 - 1] = new Cabin(var1, true, (Passenger[])null);
                    processQueue(var1);
                    waiting();
                }
            } else {
                System.out.println("Invalid Cabin Number");
            }
        }
    }

    private static void processQueue(int var0) {
        _cabins[var0 - 1] = new Cabin(var0, true, _queue[var0 - 1].getPassengers());
    }

    private static void findCabinNumber() {
        while(true) {
            Scanner var0 = new Scanner(System.in);
            System.out.print("\nEnter Customer Full Name : ");
            String var1 = var0.nextLine();
            boolean var2 = false;

            for(int var3 = 0; var3 < 12; ++var3) {
                if (_cabins[var3].getPassengers() != null) {
                    for(int var4 = 0; var4 < _cabins[var3].getPassengers().length; ++var4) {
                        if (_cabins[var3].getPassengers()[var4].getFirst_name().equals(var1)) {
                            System.out.println(var1 + " has occupied the cabin no " + (var3 + 1));
                            var2 = true;
                        }
                    }
                }
            }

            if (var2) {
                waiting();
            } else {
                System.out.println(var1 + " haven't occupied any cabin");
                waiting();
            }
        }
    }

    private static void saveData() {
        StringBuilder var1 = new StringBuilder();

        int var2;
        for(var2 = 0; var2 < 12; ++var2) {
            if (_cabins[var2].getPassengers() != null) {
                var1.append(_cabins[var2].getPassengers().length).append("\n");
            } else {
                var1.append("0").append("\n");
            }
        }

        for(var2 = 0; var2 < 12; ++var2) {
            if (_cabins[var2].getPassengers() != null) {
                for(int var3 = 0; var3 < _cabins[var2].getPassengers().length; ++var3) {
                    var1.append(_cabins[var2].getPassengers()[var3].getFirst_name()).append("\n");
                    var1.append(_cabins[var2].getPassengers()[var3].getSurname()).append("\n");
                    var1.append(_cabins[var2].getPassengers()[var3].getExpenses()).append("\n");
                }
            }
        }

        try {
            FileWriter var0 = new FileWriter("savedData.dat");
            var0.write(var1.toString());
            var0.close();
        } catch (IOException var4) {
            System.err.println(var4.getMessage());
        }

        System.out.println("\nData saved successfully\n");
        waiting();
    }

    private static void loadData() {
        try {
            File var1 = new File("savedData.dat");
            FileReader var0 = new FileReader(var1);
            BufferedReader var2 = new BufferedReader(var0);
            int[] var4 = new int[12];

            int var5;
            for(var5 = 0; var5 < 12; ++var5) {
                String var3 = var2.readLine();
                var4[var5] = Integer.parseInt(var3);
            }

            for(var5 = 0; var5 < 12; ++var5) {
                Passenger[] var6 = new Passenger[var4[var5]];
                if (var4[var5] == 0) {
                    _cabins[var5] = new Cabin(var5 + 1, true, (Passenger[])null);
                } else {
                    for(int var7 = 0; var7 < var4[var5]; ++var7) {
                        String var8 = var2.readLine();
                        String var9 = var2.readLine();
                        int var10 = Integer.parseInt(var2.readLine());
                        var6[var7] = new Passenger(var8, var9, var10);
                    }

                    _cabins[var5] = new Cabin(var5 + 1, false, var6);
                }
            }

            var0.close();
        } catch (FileNotFoundException var11) {
            System.out.println(var11.getMessage());
            waiting();
        } catch (IOException var12) {
            System.out.println(var12.getMessage());
            waiting();
        }

        System.out.println("Data loaded successfully\n");
        waiting();
    }

    private static void viewCabinsByOrder() {
        int var0 = 0;

        int var2;
        for(int var1 = 0; var1 < 12; ++var1) {
            if (_cabins[var1].getPassengers() != null) {
                for(var2 = 0; var2 < _cabins[var1].getPassengers().length; ++var2) {
                    ++var0;
                }
            }
        }

        String[] var6 = new String[var0];
        var2 = 0;

        int var3;
        int var4;
        for(var3 = 0; var3 < 12; ++var3) {
            if (_cabins[var3].getPassengers() != null) {
                for(var4 = 0; var4 < _cabins[var3].getPassengers().length; ++var4) {
                    String var10002 = _cabins[var3].getPassengers()[var4].getSurname();
                    var6[var2] = var10002 + " " + _cabins[var3].getPassengers()[var4].getFirst_name();
                    ++var2;
                }
            }
        }

        Arrays.sort(var6);

        for(var3 = 0; var3 < var6.length; ++var3) {
            System.out.print(var6[var3] + " has occupied the cabin no ");

            for(var4 = 0; var4 < 12; ++var4) {
                if (_cabins[var4].getPassengers() != null) {
                    for(int var5 = 0; var5 < _cabins[var4].getPassengers().length; ++var5) {
                        if ((_cabins[var4].getPassengers()[var5].getSurname() + " " + _cabins[var4].getPassengers()[var5].getFirst_name()).equals(var6[var3])) {
                            System.out.println(var4 + 1);
                        }
                    }
                }
            }
        }

        waiting();
    }

    private static void viewExpences() {
        Scanner var0 = new Scanner(System.in);
        StringBuilder var1 = new StringBuilder();
        var1.append("(1) View All Expenses\n");
        var1.append("(2) View Total Expenses\n");
        var1.append("Enter your choise : ");
        System.out.print(var1.toString());
        String var2 = var0.next().toLowerCase();
        char var3 = var2.charAt(0);
        switch(var3) {
            case '1':
                viewExpenses(1);
                break;
            case '2':
                viewExpenses(2);
                break;
            default:
                System.out.println("Invalid Input Try Again!");
                viewExpences();
        }

    }

    private static void viewExpenses(int var0) {
        int var1;
        int var2;
        if (var0 == 1) {
            for(var1 = 0; var1 < 12; ++var1) {
                if (_cabins[var1].getPassengers() != null) {
                    for(var2 = 0; var2 < _cabins[var1].getPassengers().length; ++var2) {
                        System.out.println("In cabin no " + (var1 + 1) + " " + _cabins[var1].getPassengers()[var2].getFirst_name() + "'s expenses are " + _cabins[var1].getPassengers()[var2].getExpenses());
                    }
                }
            }
        } else {
            for(var1 = 0; var1 < 12; ++var1) {
                if (_cabins[var1].getPassengers() != null) {
                    var2 = 0;

                    for(int var3 = 0; var3 < _cabins[var1].getPassengers().length; ++var3) {
                        var2 += _cabins[var1].getPassengers()[var3].getExpenses();
                    }

                    System.out.println("In cabin no " + (var1 + 1) + " total expenses are " + var2);
                }
            }
        }

        waiting();
    }

    private static void initialise() {
        for(int var0 = 0; var0 < 12; ++var0) {
            _cabins[var0] = new Cabin(var0 + 1, true, (Passenger[])null);
            _queue[var0] = new Queue(var0 + 1, true, (Passenger[])null);
        }

    }

    private static void clearConsole() {
        try {
            ProcessBuilder var0 = new ProcessBuilder(new String[]{"cmd", "/c", "cls"});
            Process var1 = var0.inheritIO().start();
            var1.waitFor();
        } catch (InterruptedException | IOException var2) {
        }

    }

    private static void waiting() {
        System.out.print("Press \"M\" key to go to menu or Press any other key to exit : ");
        Scanner var0 = new Scanner(System.in);
        String var1 = var0.next().toLowerCase();
        char var2 = var1.charAt(0);
        if (var2 == 'm') {
            clearConsole();
            displayMenu();
        } else {
            System.exit(0);
        }

    }
}
