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

        System.out.println("""
                1.Number of matches played per year of all the years in IPL.
                2.Number of matches won of all teams over all the years of IPL.
                3.For the year 2016 get the extra runs conceded per team.
                4.For the year 2015 get the top economical bowlers.
                Enter the Question number you want to know...""");
        Scanner sc = new Scanner(System.in);
        int questionNumber = sc.nextInt();
        switch(questionNumber){
            case 1:
                findNumberOfMatchesPlayedPerYear(matches);
                break;
            case 2:
                findNumberOfMatchesWonByEachTeams(matches);
                break;
            case 3:
                int extraRunsInyear = 2016;
                findExtraRunsConcededPerTeam(deliveries, matches, extraRunsInyear);
                break;
            case 4:
                int topBowlerInyear = 2015;
                findTheMostEconomicalBowlerInYear(deliveries, matches, topBowlerInyear);
                break;
            default:
                System.out.print("Invalid Number");
        }
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

    public static void findNumberOfMatchesWonByEachTeams(List<Match> matches){
        HashMap<String,Integer> winnerMap =new HashMap<>();

        for(int id=1; id<matches.size() ;id++){
            String currentWinner=matches.get(id).getWinner();
            int winCount;
            if(winnerMap.containsKey(currentWinner)){
                winCount=(winnerMap.get(currentWinner))+1;
                winnerMap.put(currentWinner,winCount);
            }else{
                winnerMap.put(currentWinner,1);
            }
        }

        for(String key : winnerMap.keySet()){
            int value=winnerMap.get(key);
            if(!key.isEmpty()){
                System.out.println("Number of match own by team "+key+" : "+value);
            }
        }
    }

    public static void findExtraRunsConcededPerTeam(List<Delivery> deliveries, List<Match>matches, int extraRunsInyear){
        HashMap <String,Integer> extraRunPerTeam= new HashMap<>();
        int[] matchIdRange= getMatchIdRange(extraRunsInyear, matches);
        int startingId=matchIdRange[0];
        int endingId=matchIdRange[1];

        for(int index = 0 ; index < deliveries.size() ; index++) {
            int currentId=deliveries.get(index).getMatchId();

            if (currentId >= startingId && currentId<= endingId) {
                String currentBowlingTeam=deliveries.get(index).getBowlingTeam();
                int extraRun=deliveries.get(index).getExtraRuns();
                if(!extraRunPerTeam.containsKey(currentBowlingTeam)){
                    extraRunPerTeam.put(currentBowlingTeam,extraRun);
                }else{
                    extraRunPerTeam.put(currentBowlingTeam,extraRunPerTeam.get(currentBowlingTeam)+extraRun);
                }
            }
        }

        for(String key : extraRunPerTeam.keySet()){
            int value=extraRunPerTeam.get(key);
            if(!key.isEmpty()){
                System.out.println("Extra Runs by " + key + " : " + value);
            }
        }
    }

    public static void findTheMostEconomicalBowlerInYear(List<Delivery> deliveries,List<Match> matches,int topBowlerInyear){
        HashMap<String,HashMap<String,Integer>> mostEconomicalBowler=new HashMap<>();

        int[] matchIdRange= getMatchIdRange(topBowlerInyear, matches);
        int startingId=matchIdRange[0];
        int endingId=matchIdRange[1];

        for(int index=0;index<deliveries.size();index++){
            int currentId=deliveries.get(index).getMatchId();

            if (currentId >= startingId && currentId<= endingId) {
                String currentBowler=deliveries.get(index).getBowler();
                if(!mostEconomicalBowler.containsKey(currentBowler)){
                    HashMap<String,Integer> bowlerDetails=new HashMap<>();
                    bowlerDetails.put("total_balls",0);
                    bowlerDetails.put("total_runs",0);
                    mostEconomicalBowler.put(currentBowler,bowlerDetails);
                }
                if(mostEconomicalBowler.containsKey(currentBowler)){
                    HashMap<String,Integer> bowlerDetails=mostEconomicalBowler.get(currentBowler);
                    if(!(deliveries.get(index).getExtraRuns() == 0)){
                        bowlerDetails.put("total_balls",(bowlerDetails.get("total_balls"))-1);
                    }
                    bowlerDetails.put("total_balls",(bowlerDetails.get("total_balls"))+1);

                    int current_run=deliveries.get(index).getTotalRuns();
                    bowlerDetails.put("total_runs",(bowlerDetails.get("total_runs"))+current_run);
                }
                mostEconomicalBowler.put(currentBowler,mostEconomicalBowler.get(currentBowler));
            }
        }
        HashMap<String,Double> bowlerEconomy=new HashMap<>();

        for(String key : mostEconomicalBowler.keySet()){
            HashMap<String,Integer> value=mostEconomicalBowler.get(key);
            int total_run=mostEconomicalBowler.get(key).get("total_runs");
            int total_balls=mostEconomicalBowler.get(key).get("total_balls");
            double economyOfBowler=(double) total_run/ total_balls* 6;

            economyOfBowler= Double.valueOf(String.format("%.2f", economyOfBowler));
            bowlerEconomy.put(key,economyOfBowler);
        }

        List<Map.Entry<String, Double>> rankingList = new ArrayList<>(bowlerEconomy.entrySet());
        Collections.sort(rankingList, Comparator.comparing(Map.Entry::getValue));
        int rankingCount=1;

        for (int i = 0; i < rankingList.size(); i++) {
            Map.Entry<String, Double> entry = rankingList.get(i);
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println((i + 1) + " " + key + " : " + value);
        }
    }
    public static int[] getMatchIdRange(int year, List<Match> matches){
        int startId=0;
        int endId=0;
        boolean flagStart=true;

        for(int id=0 ; id< matches.size() ; id++){
            int currentYear=matches.get(id).getSeason();

            if(year == currentYear && flagStart){
                startId=id;
                flagStart=false;
            }else if(year == currentYear){
                endId=id;
            }
        }
        int[] matchIds={++startId,++endId};
        return matchIds;
    }
}
