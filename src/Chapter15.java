import java.util.Arrays;

public class Chapter15 {
    //《算法导论》，第207页
    public int memoizedCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = Integer.MIN_VALUE;
        }

        return memoizedCutRodAux(p, n, r);
    }

    public int memoizedCutRodAux(int[] p, int n, int[] r) {
        int q;
        if (r[n] >= 0) {//已经计算过该切割方案的收益
            return r[n];
        }
        if (n == 0) {//切割长度为0，收益为0
            q = 0;
        } else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                q = Math.max(q, p[i] + memoizedCutRodAux(p, n - i, r));
            }
        }

        r[n] = q;
        return q;
    }

    //《算法导论》，第208页
    public int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n + 1];
        int q;

        for (int i = 1; i <= n; i++) {//计算r[1~n]
            q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                q = Math.max(q, p[j] + r[i - j]);
            }

            r[i] = q;
        }

        return r[n];
    }
}
