package lesson4;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private final static int SIZE = 3; // размер поля
    private final static char DOT_EMPTY = '.'; // пустые поля заполняются точкой
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    private static char [][] MAP = new char[SIZE][SIZE]; //создаем поле(массив)
    private final static Scanner SCANNER = new Scanner(System.in); //Ввод значений с консоли
    private final static Random RANDOM = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
        SCANNER.close();
        }

    public static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++)
                if ((MAP[i][0] == symbol && MAP[i][1] == symbol && MAP[i][2] == symbol)
                        || (MAP[0][i] == symbol && MAP[1][i] == symbol && MAP[2][i] == symbol)
                ) return true;
            if ((MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol)
                    || (MAP[2][0] == symbol && MAP[1][1] == symbol && MAP[0][2] == symbol))
                return true;

        return false;

    }


    /**
     * public static boolean checkWin(char symbol) { // Проверка на победу одного из игроков
        if (MAP[0][0] == symbol && MAP[0][1] == symbol && MAP[0][2] == symbol ) return true;
        if(MAP[1][0] == symbol && MAP[1][1] == symbol && MAP[1][2] == symbol) return true;
        if(MAP[2][0] == symbol && MAP[2][1] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[0][0] == symbol && MAP[1][0] == symbol && MAP[2][0] == symbol) return true;
        if(MAP[0][1] == symbol && MAP[1][1] == symbol && MAP[2][1] == symbol) return true;
        if(MAP[0][2] == symbol && MAP[1][2] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol) return true;
        if(MAP[2][0] == symbol && MAP[1][1] == symbol && MAP[0][2] == symbol) return true;
        return false;

    }
*/


    public static boolean isMapFull() {   // Проверка на заполненость полей
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() { // Ход ии
        int x, y;
        do {
            System.out.println("Введите координаты: ");
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер положил в точку " + (x + 1) + " " + (y + 1));
        MAP[x][y] = DOT_O;
    }

    private static void initMap() {   //создаем поле(Заполняем массив)
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                MAP[i][j]= DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i<=SIZE; i++) { // первый for ставит цифры полей, горизонтально
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // создает вертикальную нумерацию
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() { //Ход человека
        int x, y;
        do {
            System.out.println("Введите координаты: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[x][y] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) { // проверка на правильность введенного числа
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (MAP[x][y] == DOT_EMPTY) return true;
        return false;
    }





}
