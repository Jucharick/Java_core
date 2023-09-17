package ru.jucharick.lesson4;

public class ArrayDataException extends Exception {
    public ArrayDataException (String message, int i, int j) {
        super(String.format("Преобразование не удалось в ячейке [%d , %d]", i, j));
    }
}
