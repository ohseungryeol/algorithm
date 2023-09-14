class Solution {
    static int[] disCount = {10, 20, 30, 40};
    static int maxSell=0;
    static int maxServiceRegister=0;
    static int[] answer = new int[2];
    
     public int[] solution(int[][] users, int[] emoticons) {
        //0번: 서비스 가입 명수, 1번: 이모티콘 판매액
        int[] myEmoDiscount = new int[emoticons.length];

        DFS(0,users,emoticons,myEmoDiscount);
        return answer;
    }
    public void DFS(int L,int[][] users, int[] emoticons,int[] myEmoDiscount){
        if(L==emoticons.length){
            int sell=0;
            int serviceCount=0;
            for(int[] userInfo:users){
                int myMinDiscount=userInfo[0];
                int money = userInfo[1];
                int thisSell=0;
                for (int i=0; i<myEmoDiscount.length; i++){
                    if(myEmoDiscount[i]>=myMinDiscount){
                        thisSell += emoticons[i]/100 * (100-myEmoDiscount[i]);
                    }
                }
                if(thisSell>=money){
                    serviceCount++;
                } else{
                    sell+=thisSell;
                }

                if(serviceCount>maxServiceRegister){
                    maxServiceRegister=serviceCount;
                    maxSell=sell;
                } else if (maxServiceRegister==serviceCount){
                    maxSell = Math.max(maxSell,sell);
                }
            }



            answer[0]=maxServiceRegister;
            answer[1]=maxSell;
            return;
        }
        for(int i=0; i<disCount.length; i++){
           myEmoDiscount[L]=disCount[i];
           DFS(L+1,users,emoticons,myEmoDiscount);

        }


    }

}