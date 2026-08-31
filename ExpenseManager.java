import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private final List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
    }

    public double getTotalExpenses() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    public List<Expense> searchExpenses(String keyword) {

        List<Expense> results = new ArrayList<>();

        for (Expense expense : expenses) {

            if (expense.getDescription()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                results.add(expense);
            }
        }

        return results;
    }

    public double getTotalByCategory(Category category) {

        double total = 0;

        for (Expense expense : expenses) {

            if (expense.getCategory() == category) {
                total += expense.getAmount();
            }
        }

        return total;
    }
}