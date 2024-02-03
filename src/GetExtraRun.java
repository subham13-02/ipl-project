import java.util.HashMap;

public class GetExtraRun {
    public void extraRuns(HashMap<Integer, HashMap<String,String>> deliveriesData,int startId,int endId){

        HashMap <String,Integer> extraRunMap= new HashMap<>();

        for(int id_outer=1;id_outer<=deliveriesData.size();id_outer++) {

            int currentId=Integer.parseInt(deliveriesData.get(id_outer).get("match_id"));


            if (currentId >= startId && currentId<= endId) {
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
            System.out.println("Extra Runs by" + key + ": " + value);
        });
    }
}
