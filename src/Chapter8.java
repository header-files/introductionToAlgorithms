import java.util.Arrays;

public class Chapter8 {
    //《算法导论》，第109页
    public int[] countingSort(int[] input, int k) {
        int[] temp = new int[k + 1];
        int[] output = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            temp[input[i]] = temp[input[i]] + 1;
        }

        for (int i = 1; i < k + 1; i++) {
            temp[i] = temp[i] + temp[i - 1];
        }

        for (int i = input.length - 1; i > -1; i--) {
            output[temp[input[i]] - 1] = input[i];
            temp[input[i]]--;
        }

        return output;
    }
}
