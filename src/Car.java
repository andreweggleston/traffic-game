

public class Car {

    private static int nextId = 65;
    private Point p;
    private final int length;
    private final boolean horizontal;

    private final char id;

    public Car(int x, int y, int length, boolean horizontal) {
        this.p = new Point(x, y);
        this.length = length;
        this.horizontal = horizontal;
        Car.nextId++;
        this.id = (char)nextId;
    }

    public void move(int z) {
        this.p.inc(this.horizontal, z);
    }

    public boolean collidesWith(Car c) {
        for (Point point : this.boundingBox()) {
            for (Point point2 : c.boundingBox()) {
                if (point2.equals(point)) return true;
            }
        }
        return false;
    }


    public Point[] boundingBox() {
        Point[] arr = new Point[this.length];
        arr[0] = this.p;
        if (horizontal) {
            for (int i = 0; i < this.length; i++) {
                arr[i] = new Point(p.getX() + i, p.getY());
            }
        } else {
            for (int i = 0; i < this.length; i++) {
                arr[i] = new Point(p.getX(), p.getY() + i);
            }
        }
        return arr;
    }

    public char getId() {
        return this.id;
    }

    public static int getNextId() {
        return Car.nextId;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public Point getP() {
        return p;
    }

    public int getLength() {
        return length;
    }
}


