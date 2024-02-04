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
                Enter the Question number you want to know...""");
        Scanner sc=new Scanner(System.in);
        int questionNumber=sc.nextInt();
        switch (questionNumber){
            case 1 :
                NumberOfMatches countMatches= new NumberOfMatches();
                countMatches.matchesPlayed(matchesData);
                break;
            case 2:
                MatchesWon countWin=new MatchesWon();
                countWin.numberOfMatchesWon(matchesData);
                break;
            case 3:
                String year="2016";
                new GetExtraRun(deliveriesData, matchesData,year);
                break;
            case 4:
                String topBowlerOfyear ="2015";
                new TopBowler(deliveriesData,matchesData, topBowlerOfyear);
                break;
            default:
                System.out.print("Invalid Number");
        }
    }
}