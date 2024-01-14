import java.util.ArrayList;
import java.util.List;
class Solution {
     static List<Integer> myCards= new ArrayList<>();
    static List<Integer> cardStorage = new ArrayList<>();
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int round=0;
        for (int i=0; i<n/3; i++){
            myCards.add(cards[i]);
        }



        for (int i=n/3; i<cards.length; i+=2){
            //일단 카드는 저장해놓는다. 나중에 코인써서 뽑기가능
            cardStorage.add(cards[i]);
            cardStorage.add(cards[i+1]);
            // 내 카드로 조합할 수 있는가.
            boolean done = false;
            for (int j=0; j< myCards.size()-1; j++){
                if(done) break;
                int card1 = myCards.get(j);
                for (int k=j+1; k< myCards.size(); k++){
                    int card2 = myCards.get(k);
                    if(!done && card1+card2==n+1){
                        myCards.remove(Integer.valueOf(card1));
                        myCards.remove(Integer.valueOf(card2));

                        done = true;
                        round++;
                        break;
                    }
                }
            }
            if(done==true) continue;

            //내 카드 1개랑 저장소 카드 1개 조합으로
            if(coin-1>=0){
                for (int m=0; m<myCards.size(); m++){
                    if(done) break;
                    for (int s=0; s<cardStorage.size(); s++){
                        if(!done && myCards.get(m)+ cardStorage.get(s)==n+1){
                            myCards.remove(m);
                            cardStorage.remove(s);
                            coin-=1;
                            done=true;
                            round++;
                            break;
                        }
                    }
                }
            }
            if(done==true) continue;

            // 마지막으론 뽑은 카드로만 조합
            if(coin-2>=0){
                for (int j=0; j<cardStorage.size()-1; j++){
                    if(done) break;
                    int card1=cardStorage.get(j);
                    for (int k=j+1; k< cardStorage.size(); k++){
                        int card2 = cardStorage.get(k);
                        if(!done && card1+card2==n+1){
                            cardStorage.remove(Integer.valueOf(card1));
                            cardStorage.remove(Integer.valueOf(card2));
                            coin-=2;
                            done = true;
                            round++;
                            break;
                        }
                    }
                }
            }

            //n+1 못만든 경우
            if(!done) break;

        }

        System.out.println(round+1);
        return round+1;
    }
}