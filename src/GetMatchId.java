import java.util.HashMap;

public class GetMatchId {
    public int[] getId(String year, HashMap<Integer,HashMap<String,String>> matchesData){


        int startId=0;
        int endId=0;
        boolean flagStart=true;
        for(int id=1;id<=matchesData.size();id++){

            String currentYear=matchesData.get(id).get("season");

            if(year.equals(currentYear)&& flagStart){
                startId=id;
                flagStart=false;

            }else if(year.equals(currentYear)){
                endId=id;
            }

        }
        int[] ids={startId,endId};
        return ids;
    }
}
