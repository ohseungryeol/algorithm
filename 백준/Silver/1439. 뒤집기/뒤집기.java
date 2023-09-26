
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tmp = sc.next();

        int zeroCnt =0;
        int oneCnt = 0;
        boolean zeroFlag=false;
        boolean oneFlag=false;

        for (int i=0; i<tmp.length(); i++){
            if(tmp.charAt(i)=='0' && !zeroFlag){
                zeroCnt++;
                zeroFlag=true;
                oneFlag=false;
            } else if (tmp.charAt(i)=='1' && !oneFlag){
                oneCnt++;
                oneFlag=true;
                zeroFlag=false;
            }
        }

        System.out.println(Math.min(oneCnt,zeroCnt));
    }
}
