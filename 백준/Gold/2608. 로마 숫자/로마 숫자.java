import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       // 기호	I	V	X	L	C	D	M
        // 값	1	5	10	50	100	500	1000

        /*
        * 최대한 적은 기호로 숫자를 표현
        * V L D : 1   나머지는 3번까지 연속 가능
        * 작은 값이 큰값보다 왼쪽에 오는 경우 |x2-x1|
        *
        *
        * */
//        Character[] roma = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
//        int[] value = {1, 5, 10, 50, 100, 500, 1000}; ,4,9,40,90,400,900
        Scanner sc = new Scanner(System.in);
        String tmp1 = sc.next();
        String tmp2 = sc.next();
        int value = romaToValue(tmp1)+romaToValue(tmp2);
        System.out.println(value);
        System.out.println(valueToRoma(value));

    }

    public static int romaToValue(String tmp){
        int num = 0;

        for (int i=0; i<tmp.length(); i++){
            char r = tmp.charAt(i);
            if(i+1<tmp.length()) {
                if (r == 'I' && tmp.charAt(i + 1) == 'V') {
                    num += 4;
                    i++;
                    continue;
                }
                if (r == 'I' && tmp.charAt(i + 1) == 'X') {
                    num += 9;
                    i++;
                    continue;
                }
                if (r == 'X' && tmp.charAt(i + 1) == 'L') {
                    num += 40;
                    i++;
                    continue;
                }

                if (r == 'X' && tmp.charAt(i + 1) == 'C') {
                    num += 90;
                    i++;
                    continue;
                }
                if (r == 'C' && tmp.charAt(i + 1) == 'D') {
                    num += 400;
                    i++;
                    continue;
                }
                if (r == 'C' && tmp.charAt(i + 1) == 'M') {
                    num += 900;
                    i++;
                    continue;
                }
            }
            if(r=='I') num+=1;
            if(r=='V') num+= 5;
            if(r=='X') num+=10;
            if(r=='L') num+=50;
            if(r=='C') num+=100;
            if(r=='D') num+=500;
            if(r=='M') num+=1000;
        }

        return num;
    }

    public static String valueToRoma(int input){

        String roma = "";


        while(input>0){
            //최소 문자이므로 위에서 아래로
            if(input>=1000){
                int n = input/1000;
                for (int i=0; i<n; i++){
                    roma+="M";
                    input-=1000;
                }
                continue;
            }
            if(input>=900){
                roma+="CM";
                input-=900;
                continue;
            }

            if(input>=500){
                roma+="D";
                input-=500;
                continue;
            }
            if(input>=400){
                roma+="CD";
                input-=400;
                continue;
            }
            if(input>=100){
                roma+="C";
                input-=100;
                continue;
            }
            if(input>=90){
                roma+="XC";
                input-=90;
                continue;
            }
            if(input>=50){
                roma+="L";
                input-=50;
                continue;
            }
            if(input>=40){
                roma+="XL";
                input-=40;
                continue;
            }
            if(input>=10){
                roma+="X";
                input-=10;
                continue;
            }
            if(input>=9){
                roma+="IX";
                input-=9;
                continue;
            }
            if(input>=5){
                roma+="V";
                input-=5;
                continue;
            }
            if(input>=4){
                roma+="IV";
                input-=4;
                continue;
            }
            if(input>=1){
                roma+="I";
                input-=1;
                continue;
            }

        }

        return roma;

    }
}
