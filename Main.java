import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ExpenseManager expenseManager = new ExpenseManager();

        // Sample data
        expenseManager.addExpense(
            new Expense(
                15.50,
                Category.FOOD,
                "Lunch",
                LocalDate.of(2026, 8, 31)
            )
        );

        expenseManager.addExpense(
            new Expense(
                12.00,
                Category.TRANSPORT,
                "Taxi",
                LocalDate.of(2026, 8, 31)
            )
        );

        expenseManager.addExpense(
            new Expense(
                9.00,
                Category.ENTERTAINMENT,
                "Cinema",
                LocalDate.of(2026, 8, 30)
            )
        );

        System.out.println("========================================");
        System.out.println("        SMART EXPENSE TRACKER");
        System.out.println("========================================");

        while (true) {

            System.out.println();
            System.out.println("1. View Expenses");
            System.out.println("2. Add Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. Search Expenses");
            System.out.println("5. View Total");
            System.out.println("6. View Spending by Category");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid option. Please enter a number."
                );

                continue;
            }

            switch (choice) {

                // ========================================
                // VIEW EXPENSES
                // ========================================

                case 1:

                    System.out.println();
                    System.out.println("YOUR EXPENSES");
                    System.out.println("----------------------------------------");

                    List<Expense> expenses = expenseManager.getExpenses();

                    if (expenses.isEmpty()) {

                        System.out.println("No expenses found.");

                    } else {

                        for (int i = 0; i < expenses.size(); i++) {

                            System.out.println(
                                (i + 1) + ". " + expenses.get(i)
                            );
                        }
                    }

                    break;


                // ========================================
                // ADD EXPENSE
                // ========================================

                case 2:

                    System.out.println();
                    System.out.println("ADD EXPENSE");
                    System.out.println("----------------------------------------");

                    System.out.print("Enter amount: ");

                    String amountInput = scanner.nextLine();

                    double amount;

                    try {

                        amount = Double.parseDouble(
                            amountInput.replace(",", ".")
                        );

                    } catch (NumberFormatException e) {

                        System.out.println(
                            "Invalid amount. Please enter a valid number."
                        );

                        break;
                    }

                    if (!InputValidator.isValidAmount(amount)) {

                        System.out.println(
                            "Amount must be greater than zero."
                        );

                        break;
                    }


                    System.out.println();
                    System.out.println("Available categories:");

                    Category[] categories = Category.values();

                    for (int i = 0; i < categories.length; i++) {

                        System.out.println(
                            (i + 1) + ". " + categories[i]
                        );
                    }

                   System.out.print("Choose a category: ");

                   String categoryInput = scanner.nextLine();

                   Category category = null;

                   try {
                     int categoryChoice = Integer.parseInt(categoryInput);

                     if (categoryChoice >= 1 && categoryChoice <= categories.length) {
                      category = categories[categoryChoice - 1];
                      }

                  } catch (NumberFormatException e) {
                    for (Category cat : categories) {

                      if (cat.name().equalsIgnoreCase(categoryInput)) {
                      category = cat;
                       break;
                       }
                    }
                  }


                  if (category == null) {
                  System.out.println("Invalid category.");
                  break;
}


                    System.out.print("Enter description: ");

                    String description =
                        scanner.nextLine();

                    if (
                        !InputValidator
                            .isValidDescription(description)
                    ) {

                        System.out.println(
                            "Description cannot be empty."
                        );

                        break;
                    }


                    System.out.print(
                        "Enter date (YYYY-MM-DD): "
                    );

                    String dateInput =
                        scanner.nextLine();

                    if (
                        !InputValidator
                            .isValidDate(dateInput)
                    ) {

                        System.out.println(
                            "Invalid date. Use YYYY-MM-DD."
                        );

                        break;
                    }

                    LocalDate date =
                        LocalDate.parse(dateInput);


                    Expense newExpense =
                        new Expense(
                            amount,
                            category,
                            description,
                            date
                        );

                    expenseManager.addExpense(newExpense);

                    System.out.println();
                    System.out.println(
                        "Expense added successfully!"
                    );

                    break;


                // ========================================
                // DELETE EXPENSE
                // ========================================

                case 3:

                    System.out.println();
                    System.out.println("DELETE EXPENSE");
                    System.out.println("----------------------------------------");

                    List<Expense> currentExpenses =
                        expenseManager.getExpenses();

                    if (currentExpenses.isEmpty()) {

                        System.out.println(
                            "No expenses to delete."
                        );

                        break;
                    }

                    for (int i = 0; i < currentExpenses.size(); i++) {

                        System.out.println(
                            (i + 1) + ". " + currentExpenses.get(i)
                        );
                    }

                    System.out.print(
                        "Enter expense number to delete: "
                    );

                    String deleteInput =
                        scanner.nextLine();

                    int deleteChoice;

                    try {

                        deleteChoice =
                            Integer.parseInt(deleteInput);

                    } catch (NumberFormatException e) {

                        System.out.println(
                            "Invalid number."
                        );

                        break;
                    }

                    if (
                        deleteChoice < 1 ||
                        deleteChoice > currentExpenses.size()
                    ) {

                        System.out.println(
                            "Invalid expense number."
                        );

                        break;
                    }

                    expenseManager.deleteExpense(
                        deleteChoice - 1
                    );

                    System.out.println(
                        "Expense deleted successfully!"
                    );

                    break;


                // ========================================
                // SEARCH
                // ========================================

                case 4:

                    System.out.println();
                    System.out.println("SEARCH EXPENSES");
                    System.out.println("----------------------------------------");

                    System.out.print(
                        "Enter keyword: "
                    );

                    String keyword =
                        scanner.nextLine();

                    List<Expense> results =
                        expenseManager.searchExpenses(keyword);

                    if (results.isEmpty()) {

                        System.out.println(
                            "No matching expenses found."
                        );

                    } else {

                        System.out.println();
                        System.out.println(
                            "SEARCH RESULTS"
                        );

                        for (Expense expense : results) {

                            System.out.println(expense);
                        }
                    }

                    break;


                // ========================================
                // TOTAL
                // ========================================

                case 5:

                    double total =
                        expenseManager.getTotalExpenses();

                    System.out.println();
                    System.out.println("TOTAL EXPENSES");
                    System.out.println("----------------------------------------");

                    System.out.printf(
                        "Total: EUR %.2f%n",
                        total
                    );

                    break;


                // ========================================
                // CATEGORY SUMMARY
                // ========================================

                case 6:

                    System.out.println();
                    System.out.println(
                        "SPENDING BY CATEGORY"
                    );

                    System.out.println(
                        "----------------------------------------"
                    );

                    for (Category cat : Category.values()) {

                        double categoryTotal =
                            expenseManager
                                .getTotalByCategory(cat);

                        if (categoryTotal > 0) {

                            System.out.printf(
                                "%-15s EUR %.2f%n",
                                cat,
                                categoryTotal
                            );
                        }
                    }

                    break;


                // ========================================
                // EXIT
                // ========================================

                case 7:

                    System.out.println();
                    System.out.println(
                        "Thank you for using Smart Expense Tracker!"
                    );

                    scanner.close();

                    return;


                // ========================================
                // INVALID OPTION
                // ========================================

                default:

                    System.out.println(
                        "Invalid option. Please choose between 1 and 7."
                    );
            }
        }
    }
}