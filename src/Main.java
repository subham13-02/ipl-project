import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String csvFilePath = "src/archive";
        String matchesFileName = "/matches.csv";
        String deliveriesFileName = "/deliveries.csv";
        FetchData objectOfFetchData = new FetchData();
        HashMap<Integer, HashMap<String, String>> matchesData = new HashMap<>(objectOfFetchData.fetchedData(csvFilePath + matchesFileName));

        HashMap<Integer, HashMap<String, String>> deliveriesData = new HashMap<>(objectOfFetchData.fetchedData(csvFilePath + deliveriesFileName));
//        deliveriesData.forEach((key, value) -> {
//            System.out.println("ID " + key + ": " + value);
//        });
//        Scanner sc=new Scanner(System.in);
//        int questionNumber=sc.nextInt();
//        if(questionNumber==1){
//            NumberOfMatches countMatches= new NumberOfMatches();
//            countMatches.matchesPlayed(matchesData);
//        }else if(questionNumber==2){
//            MatchesWon countWin=new MatchesWon();
//            countWin.numberOfMatchesWon(matchesData);
//        }else if(questionNumber==3){
            GetMatchId ids= new GetMatchId();
            int[] arrIds=ids.getId("2016",matchesData);
            int startingId=arrIds[0];
            int endingId=arrIds[1];
//        System.out.println(startingId+"  " +endingId);
            GetExtraRun countWin=new GetExtraRun();
            countWin.extraRuns(deliveriesData,startingId,endingId);
//        }
    }
}