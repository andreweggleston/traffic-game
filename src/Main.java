import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car(0, 0, 2, true),
                new Car(2, 0, 2, false)
        );
        Board b = new Board(5, carList);
        System.out.println(b.toString());
        b.moveCar(new Point(2, 1), -1);
        System.out.println(b.toString());
    }
}

