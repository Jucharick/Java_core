package ru.jucharick.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final int WIN_COUNT = 4; // Выигрышная комбинация
    private static final char DOT_HUMAN = 'X'; // Фишка игрока - человек
    private static final char DOT_AI = '0'; // Фишка игрока - компьютер
    private static final char DOT_EMPTY = '*'; // Признак пустого поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static int fieldSizeX = 7; // Размерность игрового поля
    private static int fieldSizeY = 8; // Размерность игрового поля

    public static void main(String[] args) {
        field = new char[fieldSizeY][fieldSizeX];
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }

        scanner.close();
    }

    /**
     * Инициализация объектов игры
     */
    private static void initialize(){
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     *
     *     +-1-2-3-
     *     1|*|X|0|
     *     2|*|*|0|
     *     3|*|*|0|
     *     --------
     */
    private static void printField(){
        System.out.print("+");
        for (int x = 0; x <= fieldSizeX * 2 + 1; x++){
            System.out.print((x % 2 == 0) ? "-" : x / 2 + 1);
        }
        System.out.println();

        for (int x = 0; x < fieldSizeX; x++){
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++){
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn(){
        int x, y;

//        do {
//            System.out.printf("Введите координату X и Y (от одного до %d)\n через пробел >>> ", fieldSizeX);
//            x = scanner.nextInt() - 1;
//            y = scanner.nextInt() - 1;
//        }

        do {
            while (true){
                System.out.printf("Введите координату хода по вертикали (от 1 до %d): ", fieldSizeX);
                if (scanner.hasNextInt()){
                    x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                }
                else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
            while (true){
                System.out.printf("Введите координату хода по горизонтали (от 1 до %d): ", fieldSizeY);
                if (scanner.hasNextInt()){
                    y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    break;
                }
                else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    scanner.nextLine();
                }
            }
        }

        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода компьютера
     */
    private static void aiTurn(){
        int x, y;

        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка состояния игры
     * @param c фишка игрока
     * @param s победный слоган
     * @return
     */
    private static boolean checkGameState(char c, String s){
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }

        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

    /**
     * Проверка победы
     * @param c фишка игрока
     * @return
     */
    private static boolean checkWin(char c) {

        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {

                // Проверка по горизонтали !!
                if (fieldSizeY - y >= WIN_COUNT) {
                    int checkLine = 0;
                    for (int i = 0; i <= WIN_COUNT; i++) {
                        if (field[x][i] == c) checkLine +=1;
                    }
                    if (checkLine == WIN_COUNT) return true;
                }

                // Проверка по вертикали !!
                if (fieldSizeX - x >= WIN_COUNT) {
                    int checColumn = 0;
                    for (int i = x; i <= WIN_COUNT; i++) {
                        if (field[i][y] == c) checColumn +=1;
                    }
                    if (checColumn == WIN_COUNT) return true;
                }

                // Проверка по диагонали вниз
                if (fieldSizeX - x >= WIN_COUNT && fieldSizeY - y >= WIN_COUNT) {
                    int checDiagonalDown = 0;
                    for (int i = x; i <= WIN_COUNT; i++) {
                        if (field[i][i] == c) checDiagonalDown +=1;
                    }
                    if (checDiagonalDown == WIN_COUNT) return true;
                }

                // Проверка по диагонали вверх !!
                if (fieldSizeY - y >= WIN_COUNT && fieldSizeX - x >= WIN_COUNT) {
                    int checDiagonalUp = 0;
                    for (int i = x; i <= WIN_COUNT; i++) {
                        if (field[WIN_COUNT - i][i] == c) checDiagonalUp +=1;
                    }
                    if (checDiagonalUp == WIN_COUNT) return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка на ничью
     * @return
     */
    private static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

}
