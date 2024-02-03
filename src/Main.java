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
//        matchesData.forEach((key, value) -> {
//            System.out.println("ID " + key + ": " + value);
//        });matchesData.forEach((key, value) -> S{
//            System.out.println("ID " + key + ": " + value);
//        });
        HashMap<Integer, HashMap<String, String>> deliveriesData = new HashMap<>(objectOfFetchData.fetchedData(csvFilePath + deliveriesFileName));
//        matchesData.forEach((key, value) -> {
//            System.out.println("ID " + key + ": " + value);
//        });

//        Scanner sc=new Scanner(System.in);
//        int questionNumber=sc.nextInt();
//        if(questionNumber==1){
//            NumberOfMatches countMatches= new NumberOfMatches();
//            countMatches.matchesPlayed(matchesData);
//        }else if(questionNumber==2){
            MatchesWon countWin=new MatchesWon();
            countWin.numberOfMatchesWon(matchesData);
//        }
    }
}