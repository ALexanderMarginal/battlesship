import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split("");
        reader.close();
        for (int i = str.length - 1; i >= 0; i--) {
            System.out.print(str[i]);
        }
    }
}