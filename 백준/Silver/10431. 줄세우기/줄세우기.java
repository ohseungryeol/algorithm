import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

//        1. 아무나 1명 뽑아 맨 앞에
//        2. 1명씩 맨 뒤에 서기
//
//        3. 자기 앞에 키가 더 큰 학생이 1명이상 있으면 그 가장 앞에있는 학생 바로 앞에 서기
//        4. 나머지 뒤로
//
//        -> 자신보다 큰 수중에 가장 작은 수 앞에 서기

        for (int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[20];
            int testNum = Integer.parseInt(st.nextToken());

            for (int j=0; j<20; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int cnt=0;
            for (int j=0; j<20; j++){
                for (int k=0; k<j; k++){
                    if(arr[k]>arr[j]) cnt++;
                }
            }

            System.out.println(testNum+" "+cnt);
        }

    }
}
