import java.util.*;
class Solution {
    static class Info implements Comparable<Info>{
        int num;
        int count;

        public Info( int num,int count) {
            this.num = num;
            this.count = count;
        }

        /*
        * [ 장르별 total 순으로 먼저 ]
        * [ 장르별 최다 count 2개만 뽑기]
        *
        * */
        @Override
        public int compareTo(Info o) {
            if(this.count==o.count) return this.num-o.num;
            return o.count-this.count;

        }
    }

    public ArrayList<Integer> solution(String[] genres, int[] plays) {

        // 두개씩
        //return answer;
        //장르별 total > 장르 내 최다곡 2개> 재생횟수가 같으면 고유번호 낮은순

        //1. 장르에 속한 곡이 1개면 1개만 선택
        //2. 모든 장르는 재생횟수가 다름 .
        HashMap<String,Integer> tmap = new HashMap<>();
        HashMap<String,List<Info>> map = new HashMap<>();

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i=0; i<genres.length; i++){
            String genre = genres[i];
            int num = i;
            int count = plays[i];
            //장르별 전체 갯수 tmap
            tmap.put(genre,tmap.getOrDefault(genre,0)+count);

            //해당 장르 내에서 곡 정보
            if(map.get(genre)==null) { //해당 장르에 리스트가 존재하지 않으면 할당해줌.
                map.put(genre, new ArrayList<>());
            }
            map.get(genre).add(new Info(num, count));

        }



        /*장르별 정렬*/
        List<String> listKeySet = new ArrayList<>(tmap.keySet());

        Collections.sort(listKeySet, (value1, value2) -> (tmap.get(value2).compareTo(tmap.get(value1))));
        for(String key : listKeySet) {
           
            List<Info> SongOfGenre = map.get(key);
            Collections.sort(SongOfGenre);
            if(SongOfGenre.size()==1) answer.add(SongOfGenre.get(0).num);
            else{
                answer.add(SongOfGenre.get(0).num);
                answer.add(SongOfGenre.get(1).num);
            }
        }
        
        
        /*anwer = 고유 번호 순서대로 return*/

        return answer;
    }
}