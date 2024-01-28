import java.io.BufferedReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String tmp = sc.next();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        char[][] map = new char[100][100];
        for (int i=0; i<100; i++){
            for (int j=0; j<100; j++){
                map[i][j] = '#';
            }
        }
        int x = 50;
        int y = 50;
        int nx =x;
        int ny =y;
        map[x][y]='.';
        int d=0;

        char prev = tmp.charAt(0);
        for (int i=0; i<n; i++){
            char c = tmp.charAt(i);

            if(c=='R'){
                if(d==3) d=0;
                else d++;
                nx = x+dx[d];
                ny = y+dy[d];
                prev= 'R';
            } else if (c=='L'){
                if(d==0) d=3;
                else d--;
                nx = x+dx[d];
                ny = y+dy[d];
                prev = 'L';
            } else if (c=='F'){
                if(prev=='F') {
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                map[nx][ny]='.';
                x=nx;
                y=ny;
                prev = 'F';
            }
        }
        int minRow=Integer.MAX_VALUE;
        int minCol=Integer.MAX_VALUE;
        int maxRow=Integer.MIN_VALUE;
        int maxCol=Integer.MIN_VALUE;


        for (int i=0; i<100; i++){
            for (int j=0; j<100; j++){
                if(map[i][j]=='.'){
                    minRow = Math.min(minRow,i);
                    maxRow = Math.max(maxRow,i);
                    minCol = Math.min(minCol,j);
                    maxCol = Math.max(maxCol,j);
                }
            }
        }

//        for (int i=startR; i<startR+row; i++){
//            for (int j=startC; j<startC+col; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        for (int i=minRow; i<=maxRow; i++){
            for (int j=minCol; j<=maxCol; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
