package io.mountblue.ipl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUNS = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;

    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEG_BYE_RUNS = 12;
    public static final int NO_BALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS = 15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSAL_KIND = 19;
    public static final int FIELDER = 20;
    public static void main(String[] args) {

        List<Match> matches = getMatchData();
        List<Delivery> deliveries = getDeliveryData();

//        for(int i=0;i<deliveries.size();i++){
//            System.out.println(deliveries.get(i) );
//        }
        System.out.println("""
                1.Number of matches played per year of all the years in IPL.
                2.Number of matches won of all teams over all the years of IPL.
                3.For the year 2016 get the extra runs conceded per team.
                4.For the year 2015 get the top economical bowlers.
                Enter the Question number you want to know...""");
        Scanner sc = new Scanner(System.in);
        int questionNumber = sc.nextInt();
//        switch(questionNumber){
//            case 1:
//                findNumberOfMatchesPlayedPerYear(matches);
//                break;
//            case 2:
//                findNumberOfMatchesWonByEachTeams(matches);
//                break;
//            case 3:
//                String extraRunsInyear = "2016";
//                findExtraRuns(delivery, match, extraRunsInyear);
//                break;
//            case 4:
//                String topBowlerInyear = "2015";
//                findTheMostEconomicalBowlerInYear(delivery, match, topBowlerInyear);
//                break;
//            default:
//                System.out.print("Invalid Number");
//        }
    }
    public static List<Match> getMatchData(){
        List<Match> matches=new ArrayList<>();
        String filePath="src/io/mountblue/ipl/archive/matches.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line= reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);

                Match match= new Match();
                match.setMatchId(Integer.parseInt(data[MATCH_ID]));
                match.setSeason(Integer.parseInt(data[SEASON]));
                match.setCity(data[CITY]);
                match.setDate(data[DATE]);
                match.setTeam1(data[TEAM1]);
                match.setTeam2(data[TEAM2]);
                match.setTossWinner(data[TOSS_WINNER]);
                match.setTossDecision(data[TOSS_DECISION]);
                match.setResult(data[RESULT]);
                match.setDlApplied(Integer.parseInt(data[DL_APPLIED]));
                match.setWinner(data[WINNER]);
                match.setWinByRuns(Integer.parseInt(data[WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(data[WIN_BY_WICKETS]));
                match.setPlayerOfMatch(data[PLAYER_OF_MATCH]);
                match.setVenue(data[VENUE]);
                match.setUmpire1(data[UMPIRE1]);
                match.setUmpire2(data[UMPIRE2]);
                match.setUmpire3(data[UMPIRE3]);

                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public static List<Delivery> getDeliveryData(){
        List<Delivery> deliveries=new ArrayList<>();
        String filePath="src/io/mountblue/ipl/archive/deliveries.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line= reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);

                Delivery delivery= new Delivery();
                delivery.setMatchId(Integer.parseInt(data[MATCH_ID]));
                delivery.setInning(Integer.parseInt(data[INNING]));
                delivery.setOver(Integer.parseInt(data[OVER]));
                delivery.setBall(Integer.parseInt(data[BALL]));
                delivery.setWideRuns(Integer.parseInt(data[WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(data[BYE_RUNS]));
                delivery.setLegByeRuns(Integer.parseInt(data[LEG_BYE_RUNS]));
                delivery.setNoBallRuns(Integer.parseInt(data[NO_BALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(data[PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(data[BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(data[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(data[TOTAL_RUNS]));
                delivery.setSuperOver((data[IS_SUPER_OVER]).equals("1"));
                delivery.setBattingTeam(data[BATTING_TEAM]);
                delivery.setBowlingTeam(data[BOWLING_TEAM]);
                delivery.setBatsman(data[BATSMAN]);
                delivery.setNonStriker(data[NON_STRIKER]);
                delivery.setBowler(data[BOWLER]);
                delivery.setPlayerDismissed(data[PLAYER_DISMISSED]);
                delivery.setDismissalKind(data[DISMISSAL_KIND]);
                delivery.setFielder(data[FIELDER]);

                deliveries.add(delivery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    public static void findNumberOfMatchesPlayedPerYear(List<Match> matches){
        HashMap<Integer,Integer> matchesPlayedPerYear =new HashMap<>();
        int year=0;
        int matchesCount=0;

        for(int index=0; index<matches.size() ;index++){
            int currentYear=matches.get(index).getSeason();
            if(year==0){
                year=currentYear;
            }
            if(currentYear==year){
                matchesCount++;

            }else {
                matchesPlayedPerYear.put(year,matchesCount);
                System.out.println("Total number matches played in year "+year+" : "+matchesCount);
                year=matches.get(index).getSeason();
                matchesCount=1;
            }
        }
        matchesPlayedPerYear.put(year,matchesCount);

        for (Integer key : matchesPlayedPerYear.keySet()) {
            int value = matchesPlayedPerYear.get(key);
            System.out.println("Total number matches played in year "+key+" : "+value);
        }
    }



}
