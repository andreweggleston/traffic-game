import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size; // side length
    private Car[][] board;
    private List<Car> carList;

    public Board() {
        this(5, Arrays.asList(new Car(0, 0, 2, true), new Car(2, 0, 2, false)));
    }

    public Board(int size, List<Car> carList) {
        this.size = size;
        this.carList = carList;
        board = new Car[size][size];
        for (Car c : carList) {
            for (Point point : c.boundingBox()) {
                board[point.getY()][point.getX()] = c;
            }
        }
    }


    public boolean classInv() {
        boolean flag = true;
        for (Car car1 : carList) {
            for (Car car2 : carList) {
                if (car1 != car2) {
                    flag &= !car1.collidesWith(car2);
                }
            }
            for (Point p : car1.boundingBox()) {
                flag &= p.getX() >= 0 && p.getY() >= 0;
                flag &= p.getX() <= this.size && p.getY() <= this.size;
            }
        }
        return flag;
    }

    public void moveCar(Point p, int z) {
        Car c = board[p.getY()][p.getX()];
        if(c != null) {
            Point[] oldBox = c.boundingBox().clone();
            c.move(z);
            if (!classInv()){
                c.move(-z);
                return;
            }
            for (Point o : oldBox) {
                board[o.getY()][o.getX()] = null;
            }
            for (Point n : c.boundingBox()) {
                board[n.getY()][n.getX()] = c;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]!=null ? (char) board[i][j].getId() : '*');
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


