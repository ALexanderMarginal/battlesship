import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        string = string.trim();
        reader.close();
        if (string.length() == 0) {
            System.out.println(0);
        } else {
            String[] strings = string.split("\\s+");
            System.out.println(strings.length);
        }
    }
}