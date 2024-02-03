import java.util.HashMap;

public class MatchesWon {
    public void numberOfMatchesWon(HashMap<Integer, HashMap<String,String>> matchesData){
        HashMap<String,Integer> winnerMap =new HashMap<>();
        for(int id=1; id<=matchesData.size() ;id++){
            String currentWinner=matchesData.get(id).get("winner");
            int winCount=0;
            if(winnerMap.containsKey(currentWinner)){
                winCount=(winnerMap.get(currentWinner))+1;
                winnerMap.put(currentWinner,winCount);
            }else{
                winnerMap.put(currentWinner,1);
            }
        }
        for(String key : winnerMap.keySet()){
            if(!key.equals("")){
                System.out.println(key+" : "+winnerMap.get(key));
            }
        }

    }
}
