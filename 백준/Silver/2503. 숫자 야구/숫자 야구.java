import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tries = Integer.parseInt(reader.readLine());
        int[][] ballInfo = new int[tries][3];
        for (int i = 0; i < tries; i++) {
            StringTokenizer countTokens = new StringTokenizer(reader.readLine());
            ballInfo[i] = new int[]{
                    Integer.parseInt(countTokens.nextToken()),
                    Integer.parseInt(countTokens.nextToken()),
                    Integer.parseInt(countTokens.nextToken())
            };
        }

        // 모든 조건을 만족하는 숫자의 갯수
        int answer = 0;
        for (int i = 1; i < 10; i++) {  // 1부터 9까지
            for (int j = 1; j < 10; j++) {
                if (i == j) continue;
                for (int k = 1; k < 10; k++) {
                    if (k == i || k == j) continue;
                    // 모든 질문에 대하여 조건이 일치하는지를 확인하다, 
                    // 하나라도 틀릴경우 flag = false를 진행한다. 이후
                    // flag == true 일때만 모든 조건이 만족했다고 확인한다.
                    boolean flag = true;
                    // 확인을 위해 숫자를 자리 단위로 나눠야 하기 때문에 굳이 하나의 숫자로 
                    // 합치지 않는다.
                    int[] select = new int[]{i, j, k};
                    for (int t = 0; t < tries; t++) {
                        int target = ballInfo[t][0];
                        int[] result = getCount(target, select);
                        if (result[0] != ballInfo[t][1] || result[1] != ballInfo[t][2]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) answer++;
                }
            }
        }

        return answer;
    }

    // 스트라이크와 볼의 갯수를 판단하는 메소드이다.
    private int[] getCount(int target, int[] select) {
        // 비교를 위해 동일한 형태로 숫자를 배열로 나눈다.
        int[] targetNums = new int[]{target / 100, (target / 10) % 10, target % 10};
        int strikes = 0;
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            // 현재 위치의 숫자가 동일하면 strike
            if (targetNums[i] == select[i]) strikes++;
                // 아니라면
            else {
                // 해당 숫자가 다른 위치에 존재하는지 확인, 존재하면 ball
                for (int j = 0; j < 3; j++) {
                    if (targetNums[j] == select[i]) balls++;
                }
            }
        }
        return new int[]{strikes, balls};
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }
}