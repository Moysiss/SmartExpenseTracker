import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public static boolean isValidDate(String date) {

        try {
            LocalDate.parse(date);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}