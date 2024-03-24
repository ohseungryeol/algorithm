class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        boolean[] check = new boolean[words.length];
        boolean isContain = false;
    
        for (int i=0; i<words.length; i++){
            if(target.equals(words[i])) isContain = true;
        }
        // words 안에 target이 없으면 
        if(!isContain) return 0;
        else{
            DFS(begin,target,words,0, check);
            return answer;
        }
        
        //hit - hot - lot - log -cog
        //hit - hot - dot - dog -cog
    }
    
    public static void DFS(String begin, String target, String[] words, int count,boolean[] check){
        System.out.print(begin+" - ");
        if(begin.equals(target)){
            answer = Math.min(answer,count);
        }
        for (int i=0; i<words.length; i++){
            if(!check[i] && isChange(begin,words[i])){
                check[i]=true;
                DFS(words[i],target,words,count+1,check);
                check[i]=false;
            }
        }
    }
    
    // 한개의 알파벳만 바꿔서 만들 수 있는지 여부 return
    public static boolean isChange(String word, String changeWord){
        int num= changeWord.length();
        int cnt =0;
        for (int i=0; i<word.length(); i++){
            if(word.charAt(i)==changeWord.charAt(i)) cnt++;
        }
        if(cnt==num-1) return true;
        return false;
    }
}