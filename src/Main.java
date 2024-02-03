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

        System.out.println("""
                1.Number of matches played per year of all the years in IPL.
                2.Number of matches won of all teams over all the years of IPL.
                3.For the year 2016 get the extra runs conceded per team.
                4.For the year 2015 get the top economical bowlers.
                5.Create your own scenario.
                Enter the Question number you want to know...""");
        Scanner sc=new Scanner(System.in);
        int questionNumber=sc.nextInt();
        if(questionNumber==1){
            NumberOfMatches countMatches= new NumberOfMatches();
            countMatches.matchesPlayed(matchesData);
        }else if(questionNumber==2){
            MatchesWon countWin=new MatchesWon();
            countWin.numberOfMatchesWon(matchesData);
        }else if(questionNumber==3){
            String year="2016";
            new GetExtraRun(deliveriesData, matchesData,year);
        }
        else if(questionNumber==4){
            String year="2015";
            new TopBowler(deliveriesData,matchesData,year);
        }else{
            System.out.println("Invalid Number");
        }
    }
}