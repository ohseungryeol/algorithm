
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();

        String ans = "";
        StringBuilder sb = new StringBuilder();
//        닉네임 변경
//        1. 채팅방을 나간 후 새로운 닉네임으로 다시 들어오기
//        2. 채팅방 안에서 닉네임을 변경
        for (String str:record){
            String[] spl = str.split(" ");
            String command = spl[0];

            if(command.equals("Enter")){
                //id, nickname
                map.put(spl[1],spl[2]);
                sb.append(spl[1]+"님이 들어왔습니다.").append("\n");
                //ans+=spl[1]+"님이 들어왔습니다.\n";
            }

            if(command.equals("Change")){
                map.put(spl[1],spl[2]);
            }

            if (command.equals("Leave")) {
                sb.append(spl[1]+"님이 나갔습니다.").append("\n");
                //ans+=spl[1]+"님이 나갔습니다.\n";
            }
        }

        System.out.println(ans);

        String[] ansSpl = sb.toString().split("\n");
        String[] answer = new String[ansSpl.length];
        int i=0;
        for(String chat:ansSpl){
            int index = chat.indexOf('님');
            String id = chat.substring(0, index);
            chat = chat.replaceAll(id,map.get(id));
            answer[i++]=chat;
        }
        return answer;
    }
    
}
