import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static class Time implements Comparable<Time>{
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            // 시간별로 오름차순 정렬
            if(this.start==o.start) return this.end-o.end;
            return this.start-o.start;
        }
    }
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Time> timeList = new ArrayList<>();
        for (String[] times:book_time){
            int startTime = toMinutes(times[0]);
            int endTime = toMinutes(times[1]);
            timeList.add(new Time(startTime, endTime));
        }


        answer =1;
        List<Integer> rooms = new ArrayList<>();
        for (int i=0; i<timeList.size(); i++){
            // 해당시간
            Collections.sort(timeList);
            int startTime = timeList.get(i).start;
            boolean reserve = false;

            for (int j=0; j<rooms.size(); j++){
                if(startTime>=rooms.get(j)+10){
                    rooms.set(j,timeList.get(i).end);
                    reserve=true;
                    break;
                }
            }
            /*for (int j=0; j<timeList.size(); j++){
                Time Roomtime = timeList.get(j);
                //비어있는 룸이 있는가. 해당 시작시간 <= 룸의 퇴실+10분시간
                if(startTime>=Roomtime.end+10){
                    // 해당 룸의 종료시간을 늘린다.
                    Roomtime.end=timeList.get(i).end;
                    reserve = true;
                    break;
                }
            }*/
            if(!reserve){
                rooms.add(timeList.get(i).end);
            }

        }
        System.out.println(rooms.size());
        return rooms.size();

    }

    public int toMinutes(String time){
        String regex = ":";
        String[] split = time.split(regex);

        int hourToMinutes = Integer.parseInt(split[0]) * 60;
        int Minutes = Integer.parseInt(split[1]);

        return hourToMinutes + Minutes;
    }

    public int EmptyRoomTime(int time){
        return time+10;
    }

  
}
