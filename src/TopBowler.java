import java.util.*;

public class TopBowler {
    public TopBowler(HashMap<Integer, HashMap<String,String>> deliveriesData,HashMap<Integer, HashMap<String,String>> matchesData,String year){
        HashMap<String,HashMap<String,Integer>> bowlerMap=new HashMap<>();

        GetMatchId ids= new GetMatchId();
        int[] arrIds=ids.getId(year,matchesData);

        int startingId=arrIds[0];
        int endingId=arrIds[1];
        for(int id_outer=1;id_outer<=deliveriesData.size();id_outer++){

            int currentId=Integer.parseInt(deliveriesData.get(id_outer).get("match_id"));

            if (currentId >= startingId && currentId<= endingId) {

                String currentBowler=deliveriesData.get(id_outer).get("bowler");
                if(!bowlerMap.containsKey(currentBowler)){
                    HashMap<String,Integer> bowlerDetails=new HashMap<>();
                    bowlerDetails.put("total_balls",0);
                    bowlerDetails.put("total_runs",0);
                    bowlerMap.put(currentBowler,bowlerDetails);
                }
                if(bowlerMap.containsKey(currentBowler)){
                    HashMap<String,Integer> bowlerDetails=bowlerMap.get(currentBowler);
                    if(!deliveriesData.get(id_outer).get("extra_runs").equals("0")){
                        bowlerDetails.put("total_balls",(bowlerDetails.get("total_balls"))-1);
                    }
                    bowlerDetails.put("total_balls",(bowlerDetails.get("total_balls"))+1);

                    int current_run=Integer.parseInt(deliveriesData.get(id_outer).get("total_runs"));
                    bowlerDetails.put("total_runs",(bowlerDetails.get("total_runs"))+current_run);
                }

                bowlerMap.put(currentBowler,bowlerMap.get(currentBowler));

            }
        }
        HashMap<String,Double> bowlerEconomy=new HashMap<>();
        bowlerMap.forEach((key, value) -> {
            int total_run=bowlerMap.get(key).get("total_runs");
            int total_balls=bowlerMap.get(key).get("total_balls");
            double economyOfBowler=(double) total_run/ total_balls* 6;

            economyOfBowler= Double.valueOf(String.format("%.2f", economyOfBowler));

            bowlerEconomy.put(key,economyOfBowler);
        });

        List<Map.Entry<String, Double>> rankingList = new ArrayList<>(bowlerEconomy.entrySet());

        Collections.sort(rankingList, Comparator.comparing(Map.Entry::getValue));

        int rankingCount=1;
        for (Map.Entry<String, Double> entry : rankingList) {
            System.out.println((rankingCount++)+" "+entry.getKey() + " : " + entry.getValue());
        }

    }
}
