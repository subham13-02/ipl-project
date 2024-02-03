import java.util.HashMap;

public class NumberOfMatches {
    public void matchesPlayed(HashMap<Integer, HashMap<String,String>> matchesData){

        String year="";
        int matchesCount=0;

        for(int id=1; id<=matchesData.size() ;id++){
            String currentYear=matchesData.get(id).get("date").substring(0,4);
            if(year.isEmpty()){
                year=currentYear;
            }
            if(currentYear.equals(year)){
                matchesCount++;

            }else {
                System.out.println("Total number matches played in year "+year+" : "+matchesCount);
                year=matchesData.get(id).get("date").substring(0,4);
                matchesCount=1;
            }


        }
        System.out.println("Total number matches played in year "+year+" : "+matchesCount);
    }
}
