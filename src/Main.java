import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final TradeDAO dao = new TradeDAO();

    public static void main(String[] args) {
        while (true) {
            menu();
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1": addTrade(); break;
                    case "2": viewAll(); break;
                    case "3": updateTrade(); break;
                    case "4": deleteTrade(); break;
                    case "5": search(); break;
                    case "0": return;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void menu() {
        System.out.println("\nEnergy Trading Management Menu");
        System.out.println("1) Add Trade");
        System.out.println("2) View All");
        System.out.println("3) Update");
        System.out.println("4) Delete");
        System.out.println("5) Search");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private static void addTrade() throws Exception {
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate d = LocalDate.parse(sc.nextLine());
        System.out.print("Counterparty: ");
        String cp = sc.nextLine();
        System.out.print("Commodity: ");
        String com = sc.nextLine();
        System.out.print("Volume: ");
        BigDecimal vol = new BigDecimal(sc.nextLine());
        System.out.print("Price: ");
        BigDecimal price = new BigDecimal(sc.nextLine());
        System.out.print("Type (BUY/SELL): ");
        String type = sc.nextLine();

        int id = dao.addTrade(new Trade(d, cp, com, vol, price, type));
        System.out.println("Trade added with ID: " + id);
    }

    private static void viewAll() throws Exception {
        dao.getAllTrades().forEach(System.out::println);
    }

    private static void updateTrade() throws Exception {
        System.out.print("TradeID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("New Price: ");
        BigDecimal price = new BigDecimal(sc.nextLine());
        System.out.print("New Volume: ");
        BigDecimal vol = new BigDecimal(sc.nextLine());
        System.out.println("Rows updated: " + dao.updateTrade(id, price, vol));
    }

    private static void deleteTrade() throws Exception {
        System.out.print("TradeID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Rows deleted: " + dao.deleteTrade(id));
    }

    private static void search() throws Exception {
        System.out.print("Counterparty contains: ");
        String cp = sc.nextLine();
        System.out.print("Commodity contains: ");
        String com = sc.nextLine();
        dao.search(cp, com).forEach(System.out::println);
    }
}
