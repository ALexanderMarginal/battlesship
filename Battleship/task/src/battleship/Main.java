package battleship;

import java.util.*;

public class Main {
    enum PLAYERS {
        PLAYER_1,
        PLAYER_2
    }

    private static int sankCount = 0;

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

        /*printBattleField();
        startPlacement();
        startGame();*/
    }

    /*private static void startGame() {
        System.out.println("The game starts!");
        printBattleFieldWithFOW();
        takeShot();
    }*/

    /*private static void takeShot() {
        System.out.println("Take a shot!");
        String input = sc.next();

        int col = Integer.parseInt(input.substring(1));
        String lineLabel = input.substring(0, 1);
        boolean lineIsWrong = true;

        for (String label : LINE_LABELS) {
            if (label.equals(lineLabel)) {
                lineIsWrong = false;
                break;
            }
        }

        if (lineIsWrong || col < 1 || col > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            takeShot();
            return;
        }

        int line = LINES_MAP.get(lineLabel);

        LABELS shotCoordinate = (LABELS) BATTLE_FIELD[line][col];

        if (!shotCoordinate.equals(LABELS.X)) {
            BATTLE_FIELD[line][col] = shotCoordinate == LABELS.O ? LABELS.X : LABELS.M;
        }

        printBattleFieldWithFOW();

        shipArrayList.forEach(ship -> {
            boolean sanked = ship.checkSank(line, col);
            if (sanked) {
                sankCount++;
            }
        });

        if (sankCount == SHIPS.length) {
            System.out.println("You sank the last ship. You won. Congratulations!");
            return;
        }

        System.out.printf("%s Try again:\n", shotCoordinate == LABELS.O ? "You hit a ship!" : "You missed!");
        takeShot();
    }*/

    /*private static void placementShip(String shipName, int shipSize) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", shipName, shipSize);
        String start = sc.next();
        String end = sc.next();

        String lineStartLabel = start.substring(0, 1);
        String lineEndLabel = end.substring(0, 1);
        int colStartInput = Integer.parseInt(start.substring(1));
        int colEndInput = Integer.parseInt(end.substring(1));

        boolean lineStartIsNotWrong = false;
        boolean lineEndIsNotWrong = false;
        for (String lineLabel : LINE_LABELS) {
            if (lineLabel.equals(lineStartLabel)) {
                lineStartIsNotWrong = true;
            }
            if (lineLabel.equals(lineEndLabel)) {
                lineEndIsNotWrong = true;
            }
        }
        if (!lineStartIsNotWrong || !lineEndIsNotWrong || colStartInput < 1 || colStartInput > 10 || colEndInput < 1 || colEndInput > 10) {
            System.out.println("Error! Wrong ship location! Try again:");
            placementShip(shipName, shipSize);
            return;
        }

        int lineStartIndex = LINES_MAP.get(lineStartLabel);
        int lineEndIndex = LINES_MAP.get(lineEndLabel);

        int lineStart = Math.min(lineStartIndex, lineEndIndex);
        int lineEnd = Math.max(lineStartIndex, lineEndIndex);
        int colStart = Math.min(colStartInput, colEndInput);
        int colEnd = Math.max(colStartInput, colEndInput);

        int inputSize = (lineStart == lineEnd ? colEnd - colStart : lineEnd - lineStart) + 1;

        if (inputSize != shipSize) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", shipName);
            placementShip(shipName, shipSize);
            return;
        }

        if (lineEnd == lineStart || colStart == colEnd) {
            placeShip(shipName, shipSize, lineStart, lineEnd, colStart, colEnd);
            return;
        }

        System.out.println("Error! Wrong ship location! Try again:");
        placementShip(shipName, shipSize);
    }

    private static void placeShip(String name, int size, int lineStart, int lineEnd, int colStart, int colEnd) {
        int[][] shipCoordinates = new int[size][2];
        boolean crossing = false;

        for (int i = 0; i < size; i++) {
            if (lineStart == lineEnd) {
                if (BATTLE_FIELD[lineStart][colStart + i].equals(LABELS.O)) {
                    crossing = true;
                    break;
                }
                shipCoordinates[i][0] = lineStart;
                shipCoordinates[i][1] = colStart + i;
            }
            if (colStart == colEnd) {
                if (BATTLE_FIELD[lineStart + i][colStart].equals(LABELS.O)) {
                    crossing = true;
                    break;
                }
                shipCoordinates[i][0] = lineStart + i;
                shipCoordinates[i][1] = colStart;
            }
        }

        if (!crossing) {
            if (lineStart > 1) {
                if (colStart > 1 && BATTLE_FIELD[lineStart - 1][colStart - 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (colEnd < 10 && BATTLE_FIELD[lineStart - 1][colEnd + 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (!crossing) {
                    for (int i = colStart; i <= colEnd; i++) {
                        if (BATTLE_FIELD[lineStart - 1][i].equals(LABELS.O)) {
                            crossing = true;
                            break;
                        }
                    }
                }
            }
            if (lineEnd < 10) {
                if (colStart > 1 && BATTLE_FIELD[lineEnd + 1][colStart - 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (colEnd < 10 && BATTLE_FIELD[lineEnd + 1][colEnd + 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (!crossing) {
                    for (int i = colStart; i <= colEnd; i++) {
                        if (BATTLE_FIELD[lineEnd + 1][i].equals(LABELS.O)) {
                            crossing = true;
                            break;
                        }
                    }
                }
            }
        }

        if (!crossing) {
            if (colStart > 1) {
                for (int i = lineStart; i < lineEnd; i++) {
                    if (BATTLE_FIELD[i][colStart - 1].equals(LABELS.O)) {
                        crossing = true;
                        break;
                    }
                }
            }
            if (colEnd < 10) {
                for (int i = lineStart; i < lineEnd; i++) {
                    if (BATTLE_FIELD[i][colEnd + 1].equals(LABELS.O)) {
                        crossing = true;
                        break;
                    }
                }
            }
        }

        if (crossing) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            placementShip(name, size);
            return;
        }

        for (int[] shipCoordinate : shipCoordinates) {
            BATTLE_FIELD[shipCoordinate[0]][shipCoordinate[1]] = LABELS.O;
        }
        shipArrayList.add(new Ship(shipCoordinates, size));
    }


    private static void print(boolean fow) {
        for (int i = 0; i < SIZE; i++) {
            Object[] line = BATTLE_FIELD[i];
            for (int j = 0; j < SIZE; j++) {
                Object label = line[j];
                if (fow) {
                    if (LABELS.X.equals(line[j])) {
                        label = LABELS.X;
                    } else if (LABELS.M.equals(line[j])) {
                        label = LABELS.M;
                    } else if (i != 0 && j != 0) {
                        label = "~";
                    }
                } else if (line[j] == LABELS.NULL) {
                    label = "~";
                }
                if (j == SIZE - 1) {
                    System.out.println(label);
                } else {
                    System.out.print(label + " ");
                }
            }
        }
    }*/

    /*private static void printBattleField() {
        print(false);
    }

    private static void printBattleFieldWithFOW() {
        print(true);
    }*/

    private static class Ship {
        private ArrayList<Integer[]> coordinates = new ArrayList<>();
        private int health = 0;
        private boolean sank = false;

        public Ship(int[][] coordinatesArray, int size) {
            for (int[] ints : coordinatesArray) {
                coordinates.add(new Integer[]{ints[0], ints[1]});
                health = size;
            }
        }

        public boolean checkSank(int line, int col) {
            if (sank) {
                return false;
            }
            coordinates.forEach(coordinate -> {
                if (coordinate[0] == line && coordinate[1] == col) {
                    health--;
                }
            });
            if (health == 0) {
                sank = true;
            }

            return sank;
        }
    }
}

class Game {
    private Player player1;
    private Player player2;
    protected static final String[] SHIPS = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
    protected static final Map<String, Integer> SHIP_SIZES = new HashMap<>(Map.of(
            "Aircraft Carrier", 5,
            "Battleship", 4,
            "Submarine", 3,
            "Cruiser", 3,
            "Destroyer", 2
    ));

    public Game() {
        this.player1 = new Player(1);
        this.player2 = new Player(2);
    }

    public void startGame() {
        this.player1.placeShips();
        this.player2.placeShips();
    }
}

class Player {
    private String name;
    private Board board;
    private ArrayList<Ship> ships = new ArrayList<>();

    public Player(int count) {
        this.name = "Player" + count;
        this.board = new Board();
    }

    protected void placeShips() {
        System.out.println(this.name + ", place your ships on the game field");
        board.print(false);
        for (String ship : Game.SHIPS) {
            Ship newShip = board.placement(ship, Game.SHIP_SIZES.get(ship));
            if (newShip != null) {
                this.ships.add(newShip);
                this.board.print(false);
            }
        }
    }
}

class Board {
    private static final Scanner sc = new Scanner(System.in);

    enum LABELS {
        O,
        M,
        X,
        NULL,
    }

    private static final int SIZE = 11;
    private static final Object[][] BOARD = new Object[SIZE][SIZE];
    private final String[] LINE_LABELS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final Map<String, Integer> LINES_MAP = new HashMap<>();

    public Board() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < LINE_LABELS.length; i++) {
            LINES_MAP.put(LINE_LABELS[i], i + 1);
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 && j == 0) {
                    BOARD[i][j] = " ";
                }
                if (i != 0 && j == 0) {
                    BOARD[i][j] = LINE_LABELS[i - 1];
                }
                if (i == 0 && j != 0) {
                    BOARD[i][j] = j;
                }
                if (i != 0 && j != 0) {
                    BOARD[i][j] = LABELS.NULL;
                }
            }
        }
    }

    protected void print(boolean fow) {
        for (int i = 0; i < SIZE; i++) {
            Object[] line = BOARD[i];
            for (int j = 0; j < SIZE; j++) {
                Object label = line[j];
                if (fow) {
                    if (LABELS.X.equals(line[j])) {
                        label = LABELS.X;
                    } else if (LABELS.M.equals(line[j])) {
                        label = LABELS.M;
                    } else if (i != 0 && j != 0) {
                        label = "~";
                    }
                } else if (line[j] == LABELS.NULL) {
                    label = "~";
                }
                if (j == SIZE - 1) {
                    System.out.println(label);
                } else {
                    System.out.print(label + " ");
                }
            }
        }
    }

    protected Ship placement(String name, int size) {
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", name, size);
        String start = sc.next();
        String end = sc.next();

        String lineStartLabel = start.substring(0, 1);
        String lineEndLabel = end.substring(0, 1);
        int colStartInput = Integer.parseInt(start.substring(1));
        int colEndInput = Integer.parseInt(end.substring(1));

        boolean lineStartIsNotWrong = false;
        boolean lineEndIsNotWrong = false;
        for (String lineLabel : LINE_LABELS) {
            if (lineLabel.equals(lineStartLabel)) {
                lineStartIsNotWrong = true;
            }
            if (lineLabel.equals(lineEndLabel)) {
                lineEndIsNotWrong = true;
            }
        }
        if (!lineStartIsNotWrong || !lineEndIsNotWrong || colStartInput < 1 || colStartInput > 10 || colEndInput < 1 || colEndInput > 10) {
            System.out.println("Error! Wrong ship location! Try again:");
            this.placement(name, size);
            return null;
        }

        int lineStartIndex = LINES_MAP.get(lineStartLabel);
        int lineEndIndex = LINES_MAP.get(lineEndLabel);

        int lineStart = Math.min(lineStartIndex, lineEndIndex);
        int lineEnd = Math.max(lineStartIndex, lineEndIndex);
        int colStart = Math.min(colStartInput, colEndInput);
        int colEnd = Math.max(colStartInput, colEndInput);

        int inputSize = (lineStart == lineEnd ? colEnd - colStart : lineEnd - lineStart) + 1;

        if (inputSize != size) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", name);
            this.placement(name, size);
            return null;
        }

        if (lineEnd == lineStart || colStart == colEnd) {
            return this.placeShip(name, size, lineStart, lineEnd, colStart, colEnd);
        }

        System.out.println("Error! Wrong ship location! Try again:");
        this.placement(name, size);
        return null;
    }

    private Ship placeShip(String name, int size, int lineStart, int lineEnd, int colStart, int colEnd) {
        int[][] shipCoordinates = new int[size][2];
        boolean crossing = false;

        for (int i = 0; i < size; i++) {
            if (lineStart == lineEnd) {
                if (BOARD[lineStart][colStart + i].equals(LABELS.O)) {
                    crossing = true;
                    break;
                }
                shipCoordinates[i][0] = lineStart;
                shipCoordinates[i][1] = colStart + i;
            }
            if (colStart == colEnd) {
                if (BOARD[lineStart + i][colStart].equals(LABELS.O)) {
                    crossing = true;
                    break;
                }
                shipCoordinates[i][0] = lineStart + i;
                shipCoordinates[i][1] = colStart;
            }
        }

        if (!crossing) {
            if (lineStart > 1) {
                if (colStart > 1 && BOARD[lineStart - 1][colStart - 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (colEnd < 10 && BOARD[lineStart - 1][colEnd + 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (!crossing) {
                    for (int i = colStart; i <= colEnd; i++) {
                        if (BOARD[lineStart - 1][i].equals(LABELS.O)) {
                            crossing = true;
                            break;
                        }
                    }
                }
            }
            if (lineEnd < 10) {
                if (colStart > 1 && BOARD[lineEnd + 1][colStart - 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (colEnd < 10 && BOARD[lineEnd + 1][colEnd + 1].equals(LABELS.O)) {
                    crossing = true;
                }
                if (!crossing) {
                    for (int i = colStart; i <= colEnd; i++) {
                        if (BOARD[lineEnd + 1][i].equals(LABELS.O)) {
                            crossing = true;
                            break;
                        }
                    }
                }
            }
        }

        if (!crossing) {
            if (colStart > 1) {
                for (int i = lineStart; i < lineEnd; i++) {
                    if (BOARD[i][colStart - 1].equals(LABELS.O)) {
                        crossing = true;
                        break;
                    }
                }
            }
            if (colEnd < 10) {
                for (int i = lineStart; i < lineEnd; i++) {
                    if (BOARD[i][colEnd + 1].equals(LABELS.O)) {
                        crossing = true;
                        break;
                    }
                }
            }
        }

        if (crossing) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            this.placement(name, size);
            return null;
        }

        for (int[] shipCoordinate : shipCoordinates) {
            BOARD[shipCoordinate[0]][shipCoordinate[1]] = LABELS.O;
        }

        return new Ship(name, size, shipCoordinates);
    }
}

class Ship {
    private String title;
    private int size;
    private int health;
    private final ArrayList<Integer[]> coordinates = new ArrayList<>();
    private boolean isSank = false;

    public Ship(String title, int size, int[][] coordinatesArray) {
        this.title = title;
        this.size = size;
        this.health = size;
        for (int[] ints : coordinatesArray) {
            coordinates.add(new Integer[]{ints[0], ints[1]});
        }
    }

    public boolean checkSank(int line, int col) {
        if (this.isSank) {
            return false;
        }
        coordinates.forEach(coordinate -> {
            if (coordinate[0] == line && coordinate[1] == col) {
                this.health--;
            }
        });
        if (health == 0) {
            this.isSank = true;
        }

        return this.isSank;
    }

    public ArrayList<Integer[]> getCoordinates() {
        return this.coordinates;
    }
}