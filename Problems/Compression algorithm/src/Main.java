import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder string = new StringBuilder();
        int inputLength = input.length();
        int counter = 0;
        for (int i = 0; i < inputLength; i++) {
            String s = String.valueOf(input.charAt(i));
            counter++;
            if (i == 0) {
                string.append(s);
                counter = 0;
            } else {
                if (!String.valueOf(string.charAt(string.length() - 1)).equals(s)) {
                    string.append(counter);
                    string.append(s);
                    counter = 0;
                }
            }
            if (i == inputLength - 1) {
                counter++;
                string.append(counter);
            }
        }
        System.out.println(string);
    }
}