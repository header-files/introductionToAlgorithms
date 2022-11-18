import java.util.Arrays;

public class Chapter2 {
    //《算法导论》，第17页
    private <T extends Comparable> void merge(T[] waitSort, int indexStart, int indexMiddle, int indexEnd) {
        int sortNum1 = indexMiddle - indexStart + 1;
        int sortNum2 = indexEnd - indexMiddle;

        T[] array1 = Arrays.copyOfRange(waitSort, indexStart, indexStart + sortNum1);
        T[] array2 = Arrays.copyOfRange(waitSort, indexMiddle + 1, indexEnd + 1);

        int index1 = 0;
        int index2 = 0;
        for (int i = indexStart; i <= indexEnd; i++) {
            if (array1[index1].compareTo(array2[index2]) < 0) {
                waitSort[i] = array1[index1];
                index1++;
                if (index1 == sortNum1) {
                    for (int j = i + 1; j <= indexEnd; j++) {
                        waitSort[j] = array2[index2];
                        index2++;
                    }
                    break;
                }
            } else {
                waitSort[i] = array2[index2];
                index2++;
                if (index2 == sortNum2) {
                    for (int j = i + 1; j <= indexEnd; j++) {
                        waitSort[j] = array1[index1];
                        index1++;
                    }
                    break;
                }
            }
        }
    }

    //《算法导论》，第19页
    public <T extends Comparable> void mergeSort(T[] waitSort, int indexStart, int indexEnd) {
        if (indexStart < indexEnd) {
            int indexMiddle = (int) ((indexEnd - indexStart) / 2 + indexStart);
            mergeSort(waitSort, indexStart, indexMiddle);
            mergeSort(waitSort, indexMiddle + 1, indexEnd);
            merge(waitSort, indexStart, indexMiddle, indexEnd);
        }
    }
}
