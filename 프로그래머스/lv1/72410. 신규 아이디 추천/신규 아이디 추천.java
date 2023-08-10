class Solution {
    public String solution(String new_id) {
        String answer = "";

        //1단계
        if(new_id.length()==0) new_id+="a";
        new_id = new_id.toLowerCase();
        //2단계
        new_id = deleteChar(new_id);
        if(new_id.length()==0) new_id+="a";
        //3단계
        new_id = changeOneSpot(new_id);
        //4단계: new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        new_id = deleteFirstAndLastSpot(new_id);
        //5단계
        if(new_id.length()==0) new_id+="a";
        //6단계
        if(new_id.length()>=16){
            new_id = len16Up(new_id);
        }
        //7단계
        if(new_id.length()<=2){
            new_id = appendLast(new_id);
        }
        return new_id;
    }
    public String appendLast(String new_id){
        int len = new_id.length();
        char appendC = new_id.charAt(len-1);
        
        while(len++<3){
            new_id+=appendC;
        }
        
        return new_id;
    }

    public String deleteChar(String new_id){
        String deleteNewId = "";
        for (int i=0; i<new_id.length(); i++){
            char c = new_id.charAt(i);

            // 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
            if(Character.isDigit(c) || Character.isLowerCase(c) || c=='-' || c=='_' || c=='.'){
                deleteNewId+=c;
            }
        }

        return deleteNewId;
    }

    //new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
    public String changeOneSpot(String new_id){
        String changeString ="";
        changeString += new_id.charAt(0);
        for(int i=1; i<new_id.length(); i++){
            if(new_id.charAt(i)=='.' && new_id.charAt(i-1)=='.') continue;
            changeString+=new_id.charAt(i);
        }

        return changeString;
    }

    public String deleteFirstAndLastSpot(String new_id){
        String changeId = "";
        for (int i=0; i<new_id.length(); i++){
            if(i==0 && new_id.charAt(i)=='.' ) continue;
            if(i== new_id.length()-1 && new_id.charAt(new_id.length()-1)=='.')continue;
            changeId += new_id.charAt(i);
        }

        return changeId;
    }

    public String len16Up(String new_id){
        String changeId="";
        for(int i=0; i<15; i++){
            changeId+=new_id.charAt(i);
        }

        String changeId2="";
        for (int i=0; i<changeId.length(); i++){
            if(i==changeId.length()-1 && changeId.charAt(i)=='.') break;
            changeId2+=changeId.charAt(i);
        }

        return changeId2;

    }
}