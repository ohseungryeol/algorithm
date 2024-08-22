import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1; //최소간격
        int right = arr[N - 1] - arr[0]; //최대간격

        while (left <= right) {
            int mid = (left + right) / 2;
            int start = arr[0];
            int router = 1; //공유기 갯수

            for (int i = 1; i < N; i++) {
                if (arr[i] - start >= mid) {
                    router++;
                    start = arr[i];
                }
            }

            if (router < C) { // 설치 갯수가 만족안되면
                right = mid - 1;

            } else {
                left = mid + 1;
                answer = mid;
            }

        }

        System.out.println(answer);
    }
}