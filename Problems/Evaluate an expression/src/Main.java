import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = Double.parseDouble(scanner.nextLine());
        double b = Double.parseDouble(scanner.nextLine());
        double c = Double.parseDouble(scanner.nextLine());
        double d = Double.parseDouble(scanner.nextLine());
        System.out.println(a * 10.5 + b * 4.4 + (c + d) / 2.2);
    }
}