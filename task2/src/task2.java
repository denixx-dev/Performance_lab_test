import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class task2 {
    public static void main(String[] args) throws IOException {

        String file1 = args[0];
        String file2 = args[1];

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        Scanner scanner = new Scanner(new File(file2));

        int[] coordinates = new int[2];
        int radius;
        List<String[]> dots = new ArrayList<>();

        String[] strCoords = reader1.readLine().split(" ");
        String strRadius = reader1.readLine();
        coordinates[0] = Integer.parseInt(strCoords[0]);
        coordinates[1] = Integer.parseInt(strCoords[1]);
        radius = Integer.parseInt(strRadius);

        while (scanner.hasNextLine()) {
            dots.add(scanner.nextLine().split(" "));
        }

        Map<String, Integer> circle = new HashMap<>();
        circle.put("x", coordinates[0]);
        circle.put("y", coordinates[1]);
        circle.put("radius_sq", (int)Math.pow(radius, 2));

        List<Integer> positions = new ArrayList<>();

        for (String[] dot : dots){

            int[] intDot = new int[2];

            intDot[0] = Integer.parseInt(dot[0]);
            intDot[1] = Integer.parseInt(dot[1]);

            int diff = (int)Math.pow(intDot[0] - circle.get("x"), 2)+
                    (int)Math.pow(intDot[1] - circle.get("y"), 2) -
                    circle.get("radius_sq");

            if (diff > 0) {
                positions.add(2);
            }
            else if (diff == 0) {
                positions.add(0);
            }
            else {
                positions.add(1);
            }

        }
        positions.forEach(System.out::println);

    }
}