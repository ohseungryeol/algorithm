class Solution {
    public int solution(int n) {
        int answer = 0;
        String binaryN=Integer.toBinaryString(n);
        int num=0;
        for (int i=0; i<binaryN.length(); i++){
            if(binaryN.charAt(i)=='1')num++;
        }
        while(n<=1000000){
            int nextNum=0;
            ++n;
            String nextBinary = Integer.toBinaryString(n);
            for (int i=0; i<nextBinary.length(); i++){
                if(nextBinary.charAt(i)=='1') nextNum++;
            }
            if(num==nextNum) break;
        }
        return n;
    }
}