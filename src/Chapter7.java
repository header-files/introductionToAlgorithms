import java.util.Random;

public class Chapter7 {
    //《算法导论》，第95页
    private int partition(int[] array, int indexStart, int indexEnd) {
        int pivot = array[indexEnd];
        int indexPartition = indexStart - 1;
        int temp;

        for (int i = indexStart; i < indexEnd; i++) {
            if (array[i] <= pivot) {
                indexPartition++;
                temp = array[indexPartition];
                array[indexPartition] = array[i];
                array[i] = temp;
            }
        }

        indexPartition++;
        temp = array[indexPartition];
        array[indexPartition] = pivot;
        array[indexEnd] = temp;

        return indexPartition;
    }

    //《算法导论》，第95页
    public void quickSort(int[] array, int indexStart, int indexEnd) {
        if (indexStart < indexEnd) {
            int indexPartition = partition(array, indexStart, indexEnd);
            quickSort(array, indexStart, indexPartition - 1);
            quickSort(array, indexPartition + 1, indexEnd);
        }
    }

    //《算法导论》，第100页
    private int randomizedPartition(int[] array, int indexStart, int indexEnd) {
        int indexPartition = indexStart + new Random().nextInt(indexEnd - indexStart + 1);

        int temp = array[indexPartition];
        array[indexPartition] = array[indexEnd];
        array[indexEnd] = temp;

        return partition(array, indexStart, indexEnd);
    }

    //《算法导论》，第100页
    public void randomizedQuickSort(int[] array, int indexStart, int indexEnd) {
        if (indexStart < indexEnd) {
            int indexPartition = randomizedPartition(array, indexStart, indexEnd);
            randomizedQuickSort(array, indexStart, indexPartition - 1);
            randomizedQuickSort(array, indexPartition + 1, indexEnd);
        }
    }
}
