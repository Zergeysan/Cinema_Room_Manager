package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Write your code here
        int numOfRows;
        int numOfSeatsInRow;
        int totalNumOfSeats;
        int choice = 99;
        
        System.out.println ("Enter the number of rows:");
        numOfRows = scanner.nextInt();
        System.out.println ("Enter the number of seats in each row:");
        numOfSeatsInRow = scanner.nextInt();
    	System.out.println();
        
        totalNumOfSeats = numOfRows * numOfSeatsInRow;       
    	char[][] cinemaArray = new char[numOfRows][numOfSeatsInRow];
    	for (int i = 0; i < cinemaArray.length; i++) {
    		for (int j = 0; j < cinemaArray[i].length; j++) {
    			cinemaArray[i][j] = 'S';
    		}
    	}
    	
    	do {
    		System.out.println("1. Show the seats");
    		System.out.println("2. Buy a ticket");
			System.out.println("3. Statistics");
    		System.out.println("0. Exit");
    		choice = scanner.nextInt();
    		System.out.println("");
    		
    		switch(choice) {
    		case 1:
    			showTheSeats(cinemaArray, numOfSeatsInRow);
            	System.out.println();
    			break;
    		case 2:
    			seatChoice(totalNumOfSeats, numOfRows, cinemaArray);
            	System.out.println();
    			break;
			case 3:
				statistics(cinemaArray);
				System.out.println();
				break;
    		}
    	} while (choice != 0);
    }

	private static void statistics(char[][] cinemaArray) {
		int numberOrPurchasedTickets = 0;
		int numberOfSeats = cinemaArray.length * cinemaArray[0].length;
		int currentIncome;
		int firstHalf = cinemaArray.length / 2;
		int numberOrPurchasedTicketsInFirstHalf = 0;
		int numberOrPurchasedTicketsInSecondHalf = 0;
		int totalIncome;


		for (int i = 0; i < cinemaArray.length; i++) {
			for (int j = 0; j < cinemaArray[i].length; j++) {
				if (cinemaArray[i][j] == 'B') {
					numberOrPurchasedTickets++;
				}
			}
		}
		float percent = ((float)numberOrPurchasedTickets/numberOfSeats) * 100;
		System.out.println("Number of purchased tickets: " + numberOrPurchasedTickets);
		System.out.printf("Percentage: %.2f%%\n", percent);
		if (numberOfSeats < 60) {
			currentIncome = numberOrPurchasedTickets * 10;
		} else {
			for (int i = 0; i < firstHalf; i++) {
				for (int j = 0; j < cinemaArray[i].length; j++) {
					if (cinemaArray[i][j] == 'B')
						numberOrPurchasedTicketsInFirstHalf++;
				}
			}
			for (int i = cinemaArray.length - 1; i >= firstHalf; i--) {
				for (int j = 0; j < cinemaArray[i].length; j++) {
					if (cinemaArray[i][j] == 'B')
						numberOrPurchasedTicketsInSecondHalf++;
				}

			}
			currentIncome = numberOrPurchasedTicketsInFirstHalf * 10 + numberOrPurchasedTicketsInSecondHalf * 8;
		}
		System.out.println("Current income: $" + currentIncome);
		if (numberOfSeats < 60) {
			totalIncome = numberOfSeats * 10;
		} else {
			totalIncome = firstHalf * cinemaArray[0].length * 10 +
					(cinemaArray.length - firstHalf) * cinemaArray[0].length * 8;
		}
		System.out.println("Total income: $" + totalIncome);
	}

	private static void seatChoice(int totalNumOfSeats, int numOfRows, char[][] cinemaArray) {
		Scanner scn = new Scanner(System.in);
		int seatRow;
		int seatNumber;
		int ticketPrice;
		boolean circle = true;

		while (true) {
			System.out.println ("Enter a row number:");
			seatRow = scn.nextInt();
			System.out.println ("Enter a seat number in that row:");
			seatNumber = scn.nextInt();

			if (seatRow < 0 || seatRow > cinemaArray.length ||
					seatNumber < 0 || seatNumber > cinemaArray[0].length) {
				System.out.println("Wrong input!\n");
			} else if (cinemaArray[seatRow - 1][seatNumber - 1] == 'B') {
				System.out.println("That ticket has already been purchased!\n");
			} else {
				break;
			}
		}

		cinemaArray[seatRow - 1][seatNumber - 1] = 'B';
		if (totalNumOfSeats < 60) {
			ticketPrice = 10;
		} else if (seatRow <= (numOfRows / 2)) {
			ticketPrice = 10;
		} else {
			ticketPrice = 8;
		}
		System.out.println("Ticket price: $" + ticketPrice);
	}

	private static void showTheSeats(char[][] cinemaArray, int numOfSeatsInRow) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= numOfSeatsInRow; i++) {
        	System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaArray.length; i++) {
        	System.out.print(i+1);
        	for (int j = 0; j < cinemaArray[i].length; j++) {
        		System.out.print(" " + cinemaArray[i][j]);
        	}
        	System.out.println();
        }  
	}
}