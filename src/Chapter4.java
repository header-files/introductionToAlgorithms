public class Chapter4 {
    //《算法导论》，第40页
    private int[] findMaxCrossingSubarray(int[] array, int indexStart, int indexMiddle, int indexEnd) {
        int indexLeft = indexMiddle;
        int indexRight = indexMiddle + 1;
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = indexMiddle; i >= indexStart; i--) {
            sum += array[i];
            if (sum >= leftSum) {
                leftSum = sum;
                indexLeft = i;
            }
        }

        sum = 0;
        for (int i = indexMiddle + 1; i <= indexEnd; i++) {
            sum += array[i];
            if (sum >= rightSum) {
                rightSum = sum;
                indexRight = i;
            }
        }

        return new int[]{indexLeft, indexRight, leftSum + rightSum};
    }

    //《算法导论》，第41页
    public int[] findMaximumSubarray(int[] array, int indexStart, int indexEnd) {
        if (indexStart == indexEnd) {
            return new int[]{indexStart, indexEnd, array[indexStart]};
        }

        int indexMiddle = (int) ((indexEnd - indexStart) / 2 + indexStart);
        int[] leftResult = findMaximumSubarray(array, indexStart, indexMiddle);
        int[] rightResult = findMaximumSubarray(array, indexMiddle + 1, indexEnd);
        int[] middleResult = findMaxCrossingSubarray(array, indexStart, indexMiddle, indexEnd);

        if (leftResult[2] >= rightResult[2] && leftResult[2] >= middleResult[2]) {
            return leftResult;
        } else if (rightResult[2] >= leftResult[2] && rightResult[2] >= middleResult[2]) {
            return rightResult;
        } else {
            return middleResult;
        }
    }

    //《算法导论》，第42页4.1-5
    public int[] findMaximumSubarray(int[] array) {
        int maxSum = array[0];
        int sum = 0;
        int indexLeft = 0;
        int indexRight = 0;


        for (int i = 1; i < array.length; i++) {
            //已知array[0, i - 1]的最大子数组，求array[0, i]的最大子数组
            //最大子数组要么是array[0,i-1]的最大子数组，要么是某个array[j, i]
            //依次计算以i为结尾的子数组之和，求得最大子数组
            sum = 0;
            for (int j = i; 0 <= j; j--) {
                sum += array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    indexLeft = j;
                    indexRight = i;
                }
            }
        }
        return new int[]{indexLeft, indexRight, maxSum};
    }


    private int[][] copeTwoDimensionalArray(int[][] array, int[] indexStart, int[] indexEnd) {
        int[][] newArray = new int[indexEnd[0] - indexStart[0]][indexEnd[1] - indexStart[1]];

        for (int i = 0; i < indexEnd[0] - indexStart[0]; i++) {
            for (int j = 0; j < indexEnd[1] - indexStart[1]; j++) {
                newArray[i][j] = array[indexStart[0] + i][indexStart[1] + j];
            }
        }

        return newArray;
    }

    private int[][] addTwoArray(int[][] arrayA, int[][] arrayB) {
        int[][] newArray = new int[arrayA.length][arrayA[0].length];

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayA[0].length; j++) {
                newArray[i][j] = arrayA[i][j] + arrayB[i][j];
            }
        }

        return newArray;
    }

    private int[][] subTwoArray(int[][] arrayA, int[][] arrayB) {
        int[][] newArray = new int[arrayA.length][arrayA[0].length];

        for (int i = 0; i < arrayA.length; i++) {
            for (int j = 0; j < arrayA[0].length; j++) {
                newArray[i][j] = arrayA[i][j] - arrayB[i][j];
            }
        }

        return newArray;
    }

    private int[][] concatArray(int[][] arrayA, int[][] arrayB, int axis) {
        if (axis == 0) {//纵向拼接
            int[][] newArray = new int[arrayA.length + arrayB.length][arrayA[0].length];

            for (int i = 0; i < arrayA.length; i++) {
                for (int j = 0; j < arrayA[0].length; j++) {
                    newArray[i][j] = arrayA[i][j];
                    newArray[i + arrayA.length][j] = arrayB[i][j];
                }
            }

            return newArray;
        } else if (axis == 1) {//横向拼接
            int[][] newArray = new int[arrayA.length][arrayA[0].length + arrayB[0].length];

            for (int i = 0; i < arrayA.length; i++) {
                for (int j = 0; j < arrayA[0].length; j++) {
                    newArray[i][j] = arrayA[i][j];
                    newArray[i][j + arrayA[0].length] = arrayB[i][j];
                }
            }

            return newArray;
        }

        return null;
    }

    //《算法导论》，第45页
    public int[][] strassen(int[][] arrayA, int[][] arrayB) {
        if (arrayA.length == 1) {
            return new int[][]{{arrayA[0][0] * arrayB[0][0]}};
        }

        int middle = (int) (arrayA.length / 2);

        //1)
        int[][] arrayA11 = copeTwoDimensionalArray(arrayA, new int[]{0, 0}, new int[]{middle, middle});
        int[][] arrayA12 = copeTwoDimensionalArray(arrayA, new int[]{0, middle}, new int[]{middle, arrayA.length});
        int[][] arrayA21 = copeTwoDimensionalArray(arrayA, new int[]{middle, 0}, new int[]{arrayA.length, middle});
        int[][] arrayA22 = copeTwoDimensionalArray(arrayA, new int[]{middle, middle}, new int[]{arrayA.length, arrayA.length});

        int[][] arrayB11 = copeTwoDimensionalArray(arrayB, new int[]{0, 0}, new int[]{middle, middle});
        int[][] arrayB12 = copeTwoDimensionalArray(arrayB, new int[]{0, middle}, new int[]{middle, arrayA.length});
        int[][] arrayB21 = copeTwoDimensionalArray(arrayB, new int[]{middle, 0}, new int[]{arrayA.length, middle});
        int[][] arrayB22 = copeTwoDimensionalArray(arrayB, new int[]{middle, middle}, new int[]{arrayA.length, arrayA.length});

        //2)
        int[][] s1 = subTwoArray(arrayB12, arrayB22);
        int[][] s2 = addTwoArray(arrayA11, arrayA12);
        int[][] s3 = addTwoArray(arrayA21, arrayA22);
        int[][] s4 = subTwoArray(arrayB21, arrayB11);
        int[][] s5 = addTwoArray(arrayA11, arrayA22);
        int[][] s6 = addTwoArray(arrayB11, arrayB22);
        int[][] s7 = subTwoArray(arrayA12, arrayA22);
        int[][] s8 = addTwoArray(arrayB21, arrayB22);
        int[][] s9 = subTwoArray(arrayA11, arrayA21);
        int[][] s10 = addTwoArray(arrayB11, arrayB12);

        //3)
        int[][] p1 = strassen(arrayA11, s1);
        int[][] p2 = strassen(s2, arrayB22);
        int[][] p3 = strassen(s3, arrayB11);
        int[][] p4 = strassen(arrayA22, s4);
        int[][] p5 = strassen(s5, s6);
        int[][] p6 = strassen(s7, s8);
        int[][] p7 = strassen(s9, s10);

        //4)
        int[][] c11 = addTwoArray(subTwoArray(addTwoArray(p5, p4), p2), p6);
        int[][] c12 = addTwoArray(p1, p2);
        int[][] c21 = addTwoArray(p3, p4);
        int[][] c22 = subTwoArray(subTwoArray(addTwoArray(p5, p1), p3), p7);

        int[][] c1 = concatArray(c11, c12, 1);
        int[][] c2 = concatArray(c21, c22, 1);
        return concatArray(c1, c2, 0);
    }
}
