import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        //fees -> 기본시간, 기본요금, 단위시간(분), 단위시간당 요금
        int[] answer = {}; //차량 번호 오름차순으로 요금 return
        Map<String, String> infoMap = new HashMap<>();
        Map<String, Integer> totalParkingTimeMap = new HashMap<>();

        for(String Info:records){
            String[] carInfo = Info.split(" ");
            String time = carInfo[0];
            String carNumber = carInfo[1];
            String status = carInfo[2];
            if(status.equals("IN")){
                infoMap.put(carNumber,time);
            } else { //차가 out할 때 누적 요금을 저장해둠
                String inTime = infoMap.get(carNumber);// IN한시간
                int parkingFee = calculateTime(inTime, time);
                if(totalParkingTimeMap.containsKey(carNumber)) totalParkingTimeMap.put(carNumber,totalParkingTimeMap.get(carNumber)+parkingFee);
                else totalParkingTimeMap.put(carNumber,parkingFee);
                infoMap.remove(carNumber);
            }
        }
        //위 작업이 끝나고 infoMap이 비어있지 않으면 해당 차량은 23:59분에 나간 것이다.
        if(!infoMap.isEmpty()){
            for(String carNumber: infoMap.keySet()){
                String inTime = infoMap.get(carNumber);
                int parkingFee = calculateTime(inTime,"23:59");
                if(totalParkingTimeMap.containsKey(carNumber)) totalParkingTimeMap.put(carNumber,totalParkingTimeMap.get(carNumber)+parkingFee);
                else totalParkingTimeMap.put(carNumber,parkingFee);
            }
        }


        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitMinute = fees[2]; //단위시간
        int unitFee = fees[3]; //단위 요금

        List<String> cars = new ArrayList<>();
        for (String carNumber: totalParkingTimeMap.keySet()){
            int totalParkingTime = totalParkingTimeMap.get(carNumber);
            int totalFee = calculateFee(totalParkingTime,basicTime,basicFee,unitMinute,unitFee);
            totalParkingTimeMap.put(carNumber,totalFee);
            cars.add(carNumber);
        }

        Collections.sort(cars);
        answer = new int[cars.size()];
        for (int i=0; i< answer.length; i++){
            answer[i]= totalParkingTimeMap.get(cars.get(i));
        }

        /*for (String carNumber: totalParkingTimeMap.keySet()){
            System.out.printf("carNumber = %s fee = %d\n",carNumber,totalParkingTimeMap.get(carNumber));
        }*/

        //차 번호 오름차순 정렬
        //System.out.println(Arrays.toString(answer));
        return answer;
    }
    
     public int calculateTime(String inTime, String outTime){
        String[] inTimeInfo = inTime.split(":");
        int inTimeHour = Integer.parseInt(inTimeInfo[0]);
        int inTimeMinute = Integer.parseInt(inTimeInfo[1]);
        int inTimeTotalMinute = inTimeHour*60+inTimeMinute;

        String[] outTimeInfo = outTime.split(":");
        int outTimeHour = Integer.parseInt(outTimeInfo[0]);
        int outTimeMinute = Integer.parseInt(outTimeInfo[1]);
        int outTimeTotalMinute = outTimeHour*60+outTimeMinute;

        return outTimeTotalMinute-inTimeTotalMinute;
    }

    public int calculateFee(int totalParkingTime,int basicTime, int basicFee, int unitMinute, int unitFee){
        if(totalParkingTime<=basicTime) return basicFee;
        double time = totalParkingTime-basicTime;
        time = Math.ceil(time/unitMinute);
        return (int)(basicFee+(time*unitFee));
    }
}