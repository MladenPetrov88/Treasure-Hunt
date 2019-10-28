import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<String> items = Arrays.stream(input.split("\\|")).collect(Collectors.toList());

        while (!input.equals("Yohoho!")) {
            String[] temp = input.split("\\s+");

            if (temp[0].equals("Loot")) {
                for (int i = 1; i < temp.length; i++) {
                    if (!items.contains(temp[i])) {
                        items.add(0, temp[i]);
                    }
                }
            }
            if (temp[0].equals("Drop")) {
                if (Integer.parseInt(temp[1]) >= 0 && Integer.parseInt(temp[1]) <= items.size()) {
                    String a = items.get(Integer.parseInt(temp[1]));
                    items.remove(Integer.parseInt(temp[1]));
                    items.add(a);
                }
            }
            if (temp[0].equals("Steal")) {
                List<String> command = new ArrayList<>();

                if (Integer.parseInt(temp[1]) >= items.size()){
                    for (int i = 0; i < items.size(); i++) {
                        command.add(items.get(i));
                        items.remove(i);
                        i--;
                    }
                } else {
                    for (int i = items.size() - Integer.parseInt(temp[1]); i < items.size(); i++) {
                        command.add(items.get(i));
                        items.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < command.size() - 1; i++) {
                    System.out.print(command.get(i) + ", ");
                }
                System.out.print(command.get(command.size() - 1));
            }
            input = scanner.nextLine();
        }
        System.out.println();

        double sum = 0;

        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                String a = items.get(i);
                sum += a.length();
            }
            double average = sum / items.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.", average);
        } else {
            System.out.println("Failed treasure hunt.");
        }
    }
}
