package generictemplate;

import LogBack.LogBack;

import java.util.Arrays;

public class Genericarray {
    public static <T> void ExchangeElements(T[] array, int index1, int index2) {
        if (index1 < 0 || index1 >= array.length || index2 < 0 || index2 >= array.length) {
            throw new IllegalArgumentException("Invalid indices");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        // Example with Integer array
        Integer[] intArray = {10, 20, 30, 40, 50};
        String toBeLogged="Array beforeexchanging elements: ".concat(Arrays.toString(intArray));
        LogBack.slf4jLogger.debug(toBeLogged);
        ExchangeElements(intArray, 0, 3);
        toBeLogged="Array after exchanging elements: ".concat(Arrays.toString(intArray));
        LogBack.slf4jLogger.debug(toBeLogged);

    }
    }


