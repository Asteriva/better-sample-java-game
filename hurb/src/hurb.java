import java.util.Scanner;
import java.util.Arrays;

public class hurb {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean allowedToPlay = false;


        // Player selecting the main options
        System.out.println("Please enter how many rounds you want to play. ");
        int totalDays = Integer.parseInt(myObj.nextLine());
        System.out.println("Please enter the minimum stock price that you want ");
        int minPrice = Integer.parseInt(myObj.nextLine());
        System.out.println("Please enter the maximum stock price that you want. ");
        int maxPrice = Integer.parseInt(myObj.nextLine());

        // Controling the player input settings
        if (totalDays <= 0) {
            System.out.println("[ERROR] The total rounds can't be 0 or lower than zero ");
            return;
        } else if (minPrice < 0 || maxPrice < 0) {
            System.out.println("[ERROR] The minimum or maximum stock price can't be lower than zero ");
            return;
        } else {
            allowedToPlay = true;
        }
        // Non changeable by the player
        int profit = 0;
        int dayNumber = 1;
        int selectedDay = 1;

        int[] boughtPrices = new int[totalDays];


        // Starting the game
        if (allowedToPlay) {
            while (dayNumber <= totalDays) {
                int randomPrice = (int) Math.floor(Math.random() * (maxPrice - minPrice + 1) + minPrice);
                boolean sellOption = true;
                boolean buyOption = true;
                boolean stayOption = true;
                boolean bought = false;
                boolean selectedValidDay = false;
                String decision = "";


                // Day Loop
                if (dayNumber == 1) {
                    sellOption = false;
                    System.out.println(" ");
                    System.out.println("Day " + dayNumber + ": You checked the price of the stock. The price is: " + randomPrice);
                    System.out.println(" What would you like to do? (buy or stay?)");

                } else if (dayNumber == totalDays) {
                    System.out.println(" ");
                    System.out.println(Arrays.toString(boughtPrices));
                    System.out.println("Day " + dayNumber + ": You checked the price of the stock. The price is: " + randomPrice);
                    System.out.println(" What would you like to do? (buy, stay or sell?)");

                }
                else {
                    System.out.println(" ");
                    System.out.println(Arrays.toString(boughtPrices));
                    System.out.println("Day " + dayNumber + ": You checked the price of the stock. The price is: " + randomPrice);
                    System.out.println(" What would you like to do? (buy, stay or sell?)");

                }

                decision = myObj.nextLine();

                switch (decision) {
                    case ("buy") -> {
                        System.out.println(" ");
                        System.out.println("You bought 1 stock priced " + randomPrice + ".");
                        bought = true;
                        boughtPrices[dayNumber - 1] = randomPrice;
                    }
                    case ("stay") -> {
                        System.out.println(" ");
                        System.out.println("You did nothing. ");
                    }
                    case ("sell") -> {
                        if (!sellOption) {
                            System.out.println(" ");
                            System.out.println("You don't have any stock");
                        } else {
                            System.out.println(" ");
                            System.out.println("Please select the selling method. (Write 'all' to sell all or write 'day' to select a day) ");
                            String decision2 = myObj.nextLine();

                            switch (decision2) {
                                case ("all") -> {
                                    for (int count = 1; count <= totalDays; count++) {
                                        if (boughtPrices[count - 1] != 0) {
                                            profit = profit + randomPrice - boughtPrices[count - 1];
                                            boughtPrices[count - 1] = 0;
                                            System.out.println(" ");
                                        }
                                    }
                                    System.out.println("Your profit is: " + profit);
                                }
                                case ("day") -> {
                                    System.out.println("Please select a day to sell its stock ");
                                    selectedDay = Integer.parseInt(myObj.nextLine());
                                    while (!selectedValidDay) {
                                        if (selectedDay == 0 || selectedDay > totalDays || boughtPrices[selectedDay - 1] == 0) {
                                            System.out.println("You didn't select a valid day, please select again ");
                                            selectedDay = Integer.parseInt(myObj.nextLine());
                                        } else {
                                            selectedValidDay = true;
                                        }
                                    }
                                    if (selectedValidDay) {
                                        profit = profit + randomPrice - boughtPrices[selectedDay - 1];
                                        boughtPrices[selectedDay - 1] = 0;
                                        System.out.println("Your profit is: " + profit);
                                    }
                                }
                            }
                        }
                    }
                }

                if (dayNumber < totalDays) {
                    dayNumber++;
                    System.out.println(" Moving to day " + dayNumber + ".");
                } else {
                    System.out.println(" ");
                    System.out.println(" ----------------------------------------- ");
                    System.out.println(" ");
                    System.out.println("The game has ended. Here is your stats.");
                    System.out.println("Your total profit is: " + profit);
                    if (profit > 0 ) {
                        System.out.println("Awesome job! Your profit is not lower than zero.");
                        return;
                    } else if (profit == 0 ) {
                        System.out.println("Hmmm, at least it's not lower than zero great job i guess...");
                        return;
                    } else {
                        System.out.println("Ahhhh, bad job! Your profit is lower than zero.");
                        return;
                    }
                }


            }

        }
    }
}
