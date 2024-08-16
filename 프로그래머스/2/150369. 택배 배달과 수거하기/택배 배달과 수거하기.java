
import java.util.*;

class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer =0;
        Stack<Integer> deliver = new Stack<>(); //배달 상자
        Stack<Integer> pick = new Stack<>(); // 수거 상자

        for (int i=0; i<deliveries.length; i++){
            if(deliveries[i]>0) {
                deliver.add(i);
            }

            if(pickups[i]>0){
                pick.add(i);
            }
        }

        while(!deliver.isEmpty() || !pick.isEmpty()){
            //각자 cap씩 할당
            int dcap=cap;
            int pcap=cap;
            int max=0;


            int deliverAddress =0;
            while(dcap>0 && !deliver.isEmpty()){
                int idx=deliver.pop();
                deliverAddress = Math.max(deliverAddress,idx);

                if(dcap<deliveries[idx]){
                    deliveries[idx]-=dcap;
                    deliver.push(idx);
                    dcap=0;
                } else {
                    dcap-=deliveries[idx];
                    deliveries[idx]=0;
                }
            }

            int pickAddress=0;
            while(pcap>0 && !pick.isEmpty()){
                int idx=pick.pop();
                pickAddress = Math.max(pickAddress,idx);

                if(pcap<pickups[idx]){
                    pickups[idx]-=pcap;
                    pick.push(idx);
                    pcap=0;
                } else {
                    pcap-=pickups[idx];
                    pickups[idx]=0;
                }
            }

            max = Math.max(deliverAddress,pickAddress);
            answer+=(max+1)*2;

        }

        System.out.println(answer);
        return answer;

    }
    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0});
    }
}




