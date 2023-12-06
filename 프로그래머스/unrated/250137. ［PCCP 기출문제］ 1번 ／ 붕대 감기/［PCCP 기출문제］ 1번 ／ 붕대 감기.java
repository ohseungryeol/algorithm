class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int curHealth=health;
        int successBand=bandage[0]; //이만큼 성공하면 추가로 회복
        int secondsHealth=bandage[1]; //초당회복량
        int addHealth=bandage[2]; //추가 회복량 
        
        // attacks[i]는 [공격 시간, 피해량]
        int lastAttack = attacks[attacks.length-1][0];
        int curTime = 1;
        int successive =0; //연속 성공횟수 
        for (int i=0; i<attacks.length; i++){
            if(curHealth==-1) break;
            
            int[] at = attacks[i];
            int attackTime = at[0];
            int damage = at[1];
            for (int j=curTime; j<=lastAttack; j++){ //1초부터 마지막 공격까지 
                
                if(j==attackTime){ //공격 받았을 때 
                    successive=0;
                    curHealth-=damage;
                    if(curHealth<=0){
                        curHealth = -1;
                        break;
                    } 
                    curTime=j+1;
                    break;
                }
                
                //공격 안받았을 떄 
                successive++; //공격을 안받으면 연속추가 
                
                if(successive==successBand){
                    if(curHealth+addHealth<health) curHealth+=addHealth;
                    else curHealth = health;
                    successive=0;
                }
                if(curHealth+secondsHealth<health) curHealth+=secondsHealth;
                else curHealth = health;
            }
        }
        
        answer=curHealth;
        return answer;
    }
    

}