import java.util.Scanner;

class Restaurant {

    static String restaurantName = "Spice Garden Restaurant";

    String[] items = new String[20];
    int[] prices = new int[20];
    int[] qty = new int[20];
    int count = 0;
    int total = 0;

    void showMenu() {
        System.out.println("\n------ MENU ------");
        System.out.println("1. Veg Burger  - ₹80");
        System.out.println("2. Pizza       - ₹150");
        System.out.println("3. Pasta       - ₹120");
        System.out.println("4. Sandwich    - ₹70");
        System.out.println("5. Cold Coffee - ₹90");
    }

    void addItem(int choice, int quantity) {

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        if (count >= 20) {
            System.out.println("Order limit reached!");
            return;
        }

        int price = 0;
        String itemName = "";

        switch (choice) {
            case 1:
                price = 80;
                itemName = "Veg Burger";
                break;
            case 2:
                price = 150;
                itemName = "Pizza";
                break;
            case 3:
                price = 120;
                itemName = "Pasta";
                break;
            case 4:
                price = 70;
                itemName = "Sandwich";
                break;
            case 5:
                price = 90;
                itemName = "Cold Coffee";
                break;
            default:
                System.out.println("Invalid Choice!");
                return;
        }

        // Check if item already exists → update quantity
        for (int i = 0; i < count; i++) {
            if (items[i].equals(itemName)) {
                qty[i] += quantity;
                total += price * quantity;
                System.out.println("Updated quantity of " + itemName);
                return;
            }
        }

        // Add new item
        items[count] = itemName;
        prices[count] = price;
        qty[count] = quantity;

        total += price * quantity;
        count++;

        System.out.println(itemName + " added to order.");
    }

    void printBill() {

        System.out.println("\n========== BILL RECEIPT ==========");
        System.out.println("Restaurant: " + restaurantName);
        System.out.println("----------------------------------");

        for (int i = 0; i < count; i++) {
            int cost = prices[i] * qty[i];
            System.out.println(items[i] + " x " + qty[i] + " = ₹" + cost);
        }

        double gst = total * 0.05;
        double finalBill = total + gst;

        System.out.println("----------------------------------");
        System.out.println("Total: ₹" + total);
        System.out.printf("GST (5%%): ₹%.2f\n", gst);
        System.out.printf("Final Bill: ₹%.2f\n", finalBill);
        System.out.println("Thank You! Visit Again!!");
    }
}

public class RestaurantBillingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Restaurant r = new Restaurant();

        char order;

        System.out.println("Welcome to " + Restaurant.restaurantName);

        do {
            r.showMenu();

            System.out.print("\nEnter item number: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            int item = sc.nextInt();

            System.out.print("Enter quantity: ");
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid quantity: ");
                sc.next();
            }
            int quantity = sc.nextInt();

            r.addItem(item, quantity);

            System.out.print("\nDo you want to order more? (Y/N): ");
            order = sc.next().charAt(0);

        } while (order == 'y' || order == 'Y');

        r.printBill();
        sc.close();
    }
}