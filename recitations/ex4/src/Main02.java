import java.util.Scanner;

public class Main02
{
    private static int[] array;
    private static int[] tempMergArr;
    private static int length;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int numberOfMarbles = sc.nextInt(); // N
        int numberOfQueries = sc.nextInt(); // Q

        int caseCount = 1;
        while ((numberOfMarbles != 0) || (numberOfQueries != 0))
        {
            System.out.println("CASE# " + caseCount + ":");

            int[] marbles = new int[numberOfMarbles];

            for (int i = 0; i < numberOfMarbles; ++i)
                marbles[i] = sc.nextInt();

            int[] queries = new int[numberOfQueries];

            for (int i = 0; i < numberOfQueries; ++i)
                queries[i] = sc.nextInt();

            // arrays.sort is too slow, need something faster
            //sort(marbles);
            java.util.Arrays.sort(marbles);

            for (int i = 0; i < numberOfQueries; ++i)
            {
                boolean isFound = false;
                int foundValue = java.util.Arrays.binarySearch(marbles, queries[i]);

                if (foundValue >= 0)
                    isFound = true;

                foundValue += 1;
                if (isFound)
                    System.out.println(queries[i] + " found at " + foundValue);
                else
                    System.out.println(queries[i] + " not found");
            }

            // Sets up for next run
            numberOfMarbles = sc.nextInt();
            numberOfQueries = sc.nextInt();
            ++caseCount;
        }
    } // End of the main method

    public static void sort(int inputArr[]) {
        array = inputArr;
        length = inputArr.length;
        tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private static void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private static void mergeParts(int lowerIndex, int middle, int higherIndex)
    {

        for (int i = lowerIndex; i <= higherIndex; i++) {tempMergArr[i] = array[i];}
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    } // End of mergeparts method
} // End of the main class
