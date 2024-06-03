import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder path = new StringBuilder();
        path.append("1");
        for (int i = 1; i < n; i++) {
            int index = (m-1) * i;
            int element = (index + 1) %n;
            if (element == 0) {
                element = n;
            }
            if (element == 1) break;

            path.append(element);
        }

        System.out.println(path.toString());
    }
}