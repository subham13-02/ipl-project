import java.util.HashMap;

public class GetExtraRun {
    public GetExtraRun(HashMap<Integer, HashMap<String,String>> deliveriesData,HashMap<Integer, HashMap<String,String>> matchesData,String year){

        HashMap <String,Integer> extraRunMap= new HashMap<>();
        GetMatchId ids= new GetMatchId();
        int[] arrIds=ids.getId(year,matchesData);
        int startingId=arrIds[0];
        int endingId=arrIds[1];

        for(int id_outer=1;id_outer<=deliveriesData.size();id_outer++) {

            int currentId=Integer.parseInt(deliveriesData.get(id_outer).get("match_id"));


            if (currentId >= startingId && currentId<= endingId) {
                String currentBowlingTeam=deliveriesData.get(id_outer).get("bowling_team");

                if(!extraRunMap.containsKey(currentBowlingTeam)){
                    extraRunMap.put(currentBowlingTeam,1);
                }else{
                    int extraRun=Integer.parseInt(deliveriesData.get(id_outer).get("extra_runs"));
                    extraRunMap.put(currentBowlingTeam,extraRunMap.get(currentBowlingTeam)+extraRun);
                }
            }

        }
        extraRunMap.forEach((key, value) -> {
            System.out.println("Extra Runs by " + key + " : " + value);
        });
    }
}
