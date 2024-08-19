public class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        int[] dlru_num = new int[4]; // d, l, r, u 순서로 저장
        
        int dx = r - x;
        int dy = c - y;
        int distance = Math.abs(dx) + Math.abs(dy);
        
        if (k < distance || (k - distance) % 2 != 0) {
            return "impossible";
        }

        // 방향별 이동량 계산
        dlru_num[0] = (dx >= 0) ? dx : 0; // d
        dlru_num[1] = (dy < 0) ? -dy : 0; // l
        dlru_num[2] = (dy >= 0) ? dy : 0; // r
        dlru_num[3] = (dx < 0) ? -dx : 0; // u

        k -= distance;
        int max_d = k / 2;

        int height = n - Math.max(x, r);
        int left = Math.min(y, c);

        while (max_d > 0 && height > 0) {
            dlru_num[0]++; // d
            dlru_num[3]++; // u
            height--;
            max_d--;
        }

        while (max_d > 0 && left > 1) {
            dlru_num[1]++; // l
            dlru_num[2]++; // r
            left--;
            max_d--;
        }

        // 문자열 생성
        for (int i = 0; i < 4; i++) {
            char move = ' ';
            switch(i) {
                case 0: move = 'd'; break;
                case 1: move = 'l'; break;
                case 2: move = 'r'; break;
                case 3: move = 'u'; break;
            }

            if (i == 2 && max_d > 0) {
                for (int j = 0; j < max_d; j++) {
                    answer.append("rl");
                }
            }
            for (int j = 0; j < dlru_num[i]; j++) {
                answer.append(move);
            }
        }
        
        return answer.toString();
    }
}
