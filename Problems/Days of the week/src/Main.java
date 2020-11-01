import java.util.*;

public class Main {

    private static final String[] WEEK_NAMES = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public static String getDayOfWeekName(int number) throws IllegalArgumentException {
        if (number <= 0 || number > WEEK_NAMES.length) {
            throw new IllegalArgumentException();
        }
        return WEEK_NAMES[number - 1];
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dayNumber = scanner.nextInt();
        try {
            System.out.println(getDayOfWeekName(dayNumber));
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    }
}