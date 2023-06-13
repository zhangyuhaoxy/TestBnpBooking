import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Rover {
    static class Point {
        int x;
        int y;
        Direction direction;
        int maxx;
        int maxy;

        public Point() {
        }

        public Point(int x, int y, Direction direction, int maxx, int maxy) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.maxx = maxx;
            this.maxy = maxy;
        }

        public void trunLeft() {
            direction = direction.left();
        }

        public void trunRight() {
            direction = direction.right();
        }

        @Override
        public String toString() {
            return x + " " + y + " " + direction;
        }

        public void move() {
            if (direction == Direction.N) {
                if (y < 5)
                    y++;
            } else if (direction == Direction.S) {
                if (y > 0)
                    y--;
            } else if (direction == Direction.E) {
                if (x < 5)
                    x++;
            } else if (direction == Direction.W) {
                if (x > 0)
                    x--;
            }
        }
    }

    enum Direction {
        N,
        E,
        S,
        W;
        private static final Direction[] vals = values();

        public Direction right() {
            return vals[(this.ordinal() + 1) % vals.length];
        }

        public Direction left() {
            return vals[(this.ordinal() - 1 + vals.length) % vals.length];
        }
    }

    public static final void main(String[] args) {
        int maxx;
        int maxy;
        try (BufferedReader reader = Files.newBufferedReader(Path.of(args[0]), StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            String[] plateau = line.split(" ");
            maxx = Integer.parseInt(plateau[0]);
            maxy = Integer.parseInt(plateau[1]);
            line = reader.readLine();
            while (line != null) {
                String[] stringPoint = line.split(" ");
                Point point = new Point(Integer.parseInt(stringPoint[0]), Integer.parseInt(stringPoint[1]), Direction.valueOf(stringPoint[2]), maxx, maxy);
                String[] actions = reader.readLine().split("");
                doActions(point, actions);
                line = reader.readLine();
                System.out.println(point.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doActions(Point point, String[] actions) {
        for (String action : actions) {
            if ("L".equals(action)) {
                point.trunLeft();
            } else if ("R".equals(action)) {
                point.trunRight();
            } else if ("M".equals(action)) {
                point.move();
            }
        }
    }
}
