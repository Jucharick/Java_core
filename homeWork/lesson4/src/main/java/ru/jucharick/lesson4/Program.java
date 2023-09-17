package ru.jucharick.lesson4;

public class Program {

    /**
     * на вход подаётся двумерный строковый массив размером 4х4
     * @throws ArraySizeException некорректный размер массив
     * @throws ArrayDataException преобразование в int не удалось
     */
    public static String[][] array = new String[][]{
            {"5","1","1","3"},
            {"3","1","6","9"},
            {"1","1","1","1"},
//            {"1","2","3","5"},
            {"1","2","3","5"}
    };

    public static int currentSize = 4;

    public static void printArray (String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void validateSize (String[][] array) throws ArraySizeException {
        if (array.length != currentSize ) {
            throw new ArraySizeException(String.format("Количество строк должно быть = %d", currentSize));
        }
        if (array[0].length != currentSize ) {
            throw new ArraySizeException(String.format("Количество столбцов должно быть = %d", currentSize));
        }
    }

    public static int sumElementsArray (String[][] array) throws ArrayDataException {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                try {
                    sum = sum + Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new ArrayDataException ("Преобразование не удалось в ячейке [%d, %d]", i+1, j+1);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        try {
            validateSize(array);
            printArray(array);
            System.out.printf("Сумма элементов массива: %d", sumElementsArray (array));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
