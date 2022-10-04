import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car(0, 0, 2, true),
                new Car(2, 0, 2, false)
        );
        Board b = new Board(5, carList);
        System.out.println(b.toString());
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while (!in.substring(0,5).contains("quit")) {
            switch (in.charAt(0)){
                case 'm':
                    String[] move = in.split(" ");
                    char id = move[1].charAt(0);
                    int z = Integer.parseInt(move[2]);
                    for (Car c : carList) {
                        if (c.getId() == id) b.moveCar(c.getP(), z);
                    }
            }
            System.out.println(b.toString());
            in = sc.nextLine();
        }
    }
}

