public class Chapter6 {
    //《算法导论》，第86页
    private void maxHeapify(int[] heap, int parent, int heapSize) {
        int l = 2 * parent + 1;
        int r = 2 * (parent + 1);
        int largest = parent;

        if (l < heapSize && heap[l] > heap[parent]) {
            largest = l;
        }
        if (r < heapSize && heap[r] > heap[largest]) {
            largest = r;
        }

        if (largest != parent) {
            int temp = heap[parent];
            heap[parent] = heap[largest];
            heap[largest] = temp;

            maxHeapify(heap, largest, heapSize);
        }
    }

    //《算法导论》，第87页
    private void buildMaxHeap(int[] array) {
        int heapSize = array.length;
        for (int i = (int) (heapSize / 2) - 1; i >= 0; i--) {
            maxHeapify(array, i, heapSize);
        }
    }

    //《算法导论》，第89页
    public void heapSort(int[] array) {
        buildMaxHeap(array);

        int heapSize = array.length;
        int temp;

        for (int i = array.length - 1; i > 0; i--) {
            temp = array[0];
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = temp;

            heapSize--;

            maxHeapify(array, 0, heapSize);
        }
    }
}
