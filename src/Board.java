import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size; // side length
    private HashMap<Point, Car> cars;

    public Board() {
        cars = new HashMap<>();
        size = 5;
    }

    public Board(int size, List<Car> carList) {
        this.size = size;
        this.cars = new HashMap<>();
        for (Car car : carList) {
            cars.put(car.getP(), car);
        }
    }

    public boolean classInv() {
        for (Car car1 : cars.values()) {
            for (Car car2 : cars.values()) {
                if (car1 != car2) {
                    assert !car1.collidesWith(car2);
                }
            }
            for (Point p : car1.boundingBox()) {
                assert p.getX() >= 0 && p.getY() >= 0;
                assert p.getX() <= this.size && p.getY() <= this.size;
            }
        }
        return true;
    }

    public void moveCar(Point p, int z) {
        Car temp = cars.remove(p);
        cars.put(temp.move(z), temp);
    }

    public char[][] generateBoard() {
        char[][] board = new char[size][size];
        for (Map.Entry<Point, Car> entry : cars.entrySet()) {
            Point p = entry.getKey();
            Car c = entry.getValue();
            for (int i = 0; i < c.getLength(); i++) {
                if (c.isHorizontal()) {
                    board[size - 1 - p.getY()][p.getX() + i] = (char) c.getId();
                } else {
                    board[size - 1 - p.getY() - i][p.getX()] = (char) c.getId();
                }
            }
        }
        return board;
    }

    @Override
    public String toString() {
        char[][] board = generateBoard();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char x = board[i][j];
                if (x == 0) x = '*';
                sb.append(x);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /*

    OOOOO
    OOOOO
    OOOOO
    *OOOO   * = 0,0


     */
}
