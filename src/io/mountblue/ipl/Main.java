package io.mountblue.ipl;

import java.sql.*;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 1;
    public static final int SEASON = 2;
    public static final int CITY = 3;
    public static final int DATE = 4;
    public static final int TEAM1 = 5;
    public static final int TEAM2 = 6;
    public static final int TOSS_WINNER = 7;
    public static final int TOSS_DECISION = 8;
    public static final int RESULT = 9;
    public static final int DL_APPLIED = 10;
    public static final int WINNER = 11;
    public static final int WIN_BY_RUNS = 12;
    public static final int WIN_BY_WICKETS = 13;
    public static final int PLAYER_OF_MATCH = 14;
    public static final int VENUE = 15;
    public static final int UMPIRE1 = 16;
    public static final int UMPIRE2 = 17;
    public static final int UMPIRE3 = 18;
    public static final int INNING = 2;
    public static final int BATTING_TEAM = 3;
    public static final int BOWLING_TEAM = 4;
    public static final int OVER = 5;
    public static final int BALL = 6;
    public static final int BATSMAN = 7;
    public static final int NON_STRIKER = 8;
    public static final int BOWLER = 9;
    public static final int IS_SUPER_OVER = 10;
    public static final int WIDE_RUNS = 11;
    public static final int BYE_RUNS = 12;
    public static final int LEG_BYE_RUNS = 13;
    public static final int NO_BALL_RUNS = 14;
    public static final int PENALTY_RUNS = 15;
    public static final int BATSMAN_RUNS = 16;
    public static final int EXTRA_RUNS = 17;
    public static final int TOTAL_RUNS = 18;
    public static final int PLAYER_DISMISSED = 19;
    public static final int DISMISSAL_KIND = 20;
    public static final int FIELDER = 21;

    public static void main(String[] args) {
        List<Match> matches = getMatchData();
        List<Delivery> deliveries = getDeliveryData();

        System.out.println("""
                1.Number of matches played per year of all the years in IPL.
                2.Number of matches won of all teams over all the years of IPL.
                3.For the year 2016 get the extra runs conceded per team.
                4.For the year 2015 get the top economical bowlers.
                5.For the match Id 1 get Second Highest wicket taker of each team.
                6.For every year get highest wicket taker per team
                7.For the year 2015 get highest score by venue in 2015.
                Enter the Question number you want to know...""");
        Scanner sc = new Scanner(System.in);
        String questionNumber = sc.next();

        switch (questionNumber) {
            case "1":
                findNumberOfMatchesPlayedPerYear(matches);
                break;
            case "2":
                findNumberOfMatchesWonByEachTeams(matches);
                break;
            case "3":
                int extraRunsInyear = 2016;
                findExtraRunsConcededPerTeam(deliveries, matches, extraRunsInyear);
                break;
            case "4":
                int topBowlerInyear = 2015;
                findTheMostEconomicalBowlerInYear(deliveries, matches, topBowlerInyear);
                break;
            case "5":
                int matchIdNumber = 1;
                secondHighestWicketTakerOfEachTeam(deliveries, matchIdNumber);
                break;
            case "6":
                findHighestWicketTakerPerTeamForEachSeason(deliveries, matches);
                break;
            case "7":
                findHeighestScoreOfBatsmanByVenue(deliveries, matches);
                break;
            default:
                System.out.print("Invalid Number");
        }
    }

    public static List<Match> getMatchData() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * from matches";
        String url = "jdbc:mysql://localhost:3306/ipl_project";
        String username = "root";
        String password = "Password@123";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Match match = new Match();

                match.setMatchId(resultSet.getInt(MATCH_ID));
                match.setSeason(resultSet.getInt(SEASON));
                match.setCity(resultSet.getString(CITY));
                match.setDate(resultSet.getString(DATE));
                match.setTeam1(resultSet.getString(TEAM1));
                match.setTeam2(resultSet.getString(TEAM2));
                match.setTossWinner(resultSet.getString(TOSS_WINNER));
                match.setTossDecision(resultSet.getString(TOSS_DECISION));
                match.setResult(resultSet.getString(RESULT));
                match.setDlApplied(resultSet.getInt(DL_APPLIED));
                match.setWinner(resultSet.getString(WINNER));
                match.setWinByRuns(resultSet.getInt(WIN_BY_RUNS));
                match.setWinByWickets(resultSet.getInt(WIN_BY_WICKETS));
                match.setPlayerOfMatch(resultSet.getString(PLAYER_OF_MATCH));
                match.setVenue(resultSet.getString(VENUE));
                match.setUmpire1(resultSet.getString(UMPIRE1));
                match.setUmpire2(resultSet.getString(UMPIRE2));
                match.setUmpire3(resultSet.getString(UMPIRE3));

                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }


    public static List<Delivery> getDeliveryData() {
        List<Delivery> deliveries = new ArrayList<>();
        String sql = "SELECT * from deliveries";
        String url = "jdbc:mysql://localhost:3306/ipl_project";
        String username = "root";
        String password = "Password@123";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Delivery delivery = new Delivery();

                delivery.setMatchId(resultSet.getInt(MATCH_ID));
                delivery.setInning(resultSet.getInt(INNING));
                delivery.setOver(resultSet.getInt(OVER));
                delivery.setBall(resultSet.getInt(BALL));
                delivery.setWideRuns(resultSet.getInt(WIDE_RUNS));
                delivery.setByeRuns(resultSet.getInt(BYE_RUNS));
                delivery.setLegByeRuns(resultSet.getInt(LEG_BYE_RUNS));
                delivery.setNoBallRuns(resultSet.getInt(NO_BALL_RUNS));
                delivery.setPenaltyRuns(resultSet.getInt(PENALTY_RUNS));
                delivery.setBatsmanRuns(resultSet.getInt(BATSMAN_RUNS));
                delivery.setExtraRuns(resultSet.getInt(EXTRA_RUNS));
                delivery.setTotalRuns(resultSet.getInt(TOTAL_RUNS));
                delivery.setSuperOver(resultSet.getBoolean(IS_SUPER_OVER));
                delivery.setBattingTeam(resultSet.getString(BATTING_TEAM));
                delivery.setBowlingTeam(resultSet.getString(BOWLING_TEAM));
                delivery.setBatsman(resultSet.getString(BATSMAN));
                delivery.setNonStriker(resultSet.getString(NON_STRIKER));
                delivery.setBowler(resultSet.getString(BOWLER));
                delivery.setPlayerDismissed(resultSet.getString(PLAYER_DISMISSED));
                delivery.setDismissalKind(resultSet.getString(DISMISSAL_KIND));
                delivery.setFielder(resultSet.getString(FIELDER));

                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    public static void findNumberOfMatchesPlayedPerYear(List<Match> matches) {
        HashMap<Integer, Integer> matchesPlayedPerYear = new HashMap<>();
        int year = 0;
        int matchesCount = 0;

        for (int index = 0; index < matches.size(); index++) {
            int currentYear = matches.get(index).getSeason();
            if (year == 0) {
                year = currentYear;
            }
            if (currentYear == year) {
                matchesCount++;

            } else {
                matchesPlayedPerYear.put(year, matchesCount);
                year = matches.get(index).getSeason();
                matchesCount = 1;
            }
        }
        matchesPlayedPerYear.put(year, matchesCount);

        for (Integer key : matchesPlayedPerYear.keySet()) {
            int value = matchesPlayedPerYear.get(key);
            System.out.println("Total number matches played in year " + key + " : " + value);
        }
    }

    public static void findNumberOfMatchesWonByEachTeams(List<Match> matches) {
        HashMap<String, Integer> winnerMap = new HashMap<>();

        for (int id = 1; id < matches.size(); id++) {
            String currentWinner = matches.get(id).getWinner();
            int winCount;
            if (winnerMap.containsKey(currentWinner)) {
                winCount = (winnerMap.get(currentWinner)) + 1;
                winnerMap.put(currentWinner, winCount);
            } else {
                winnerMap.put(currentWinner, 1);
            }
        }

        for (String key : winnerMap.keySet()) {
            int value = winnerMap.get(key);
            if (!key.isEmpty()) {
                System.out.println("Number of match own by team " + key + " : " + value);
            }
        }
    }

    public static void findExtraRunsConcededPerTeam(List<Delivery> deliveries, List<Match> matches, int extraRunsInyear) {
        HashMap<String, Integer> extraRunPerTeam = new HashMap<>();
        int[] matchIdRange = getMatchIdRange(extraRunsInyear, matches);
        int startingId = matchIdRange[0];
        int endingId = matchIdRange[1];

        for (int index = 0; index < deliveries.size(); index++) {
            int currentId = deliveries.get(index).getMatchId();

            if (currentId >= startingId && currentId <= endingId) {
                String currentBowlingTeam = deliveries.get(index).getBowlingTeam();
                int extraRun = deliveries.get(index).getExtraRuns();
                if (!extraRunPerTeam.containsKey(currentBowlingTeam)) {
                    extraRunPerTeam.put(currentBowlingTeam, extraRun);
                } else {
                    extraRunPerTeam.put(currentBowlingTeam, extraRunPerTeam.get(currentBowlingTeam) + extraRun);
                }
            }
        }

        for (String key : extraRunPerTeam.keySet()) {
            int value = extraRunPerTeam.get(key);
            if (!key.isEmpty()) {
                System.out.println("Extra Runs by " + key + " : " + value);
            }
        }
    }

    public static void findTheMostEconomicalBowlerInYear(List<Delivery> deliveries, List<Match> matches, int topBowlerInyear) {
        HashMap<String, HashMap<String, Integer>> mostEconomicalBowler = new HashMap<>();

        int[] matchIdRange = getMatchIdRange(topBowlerInyear, matches);
        int startingId = matchIdRange[0];
        int endingId = matchIdRange[1];

        for (int index = 0; index < deliveries.size(); index++) {
            int currentId = deliveries.get(index).getMatchId();

            if (currentId >= startingId && currentId <= endingId) {
                String currentBowler = deliveries.get(index).getBowler();
                if (!mostEconomicalBowler.containsKey(currentBowler)) {
                    HashMap<String, Integer> bowlerDetails = new HashMap<>();
                    bowlerDetails.put("total_balls", 0);
                    bowlerDetails.put("total_runs", 0);
                    mostEconomicalBowler.put(currentBowler, bowlerDetails);
                }
                if (mostEconomicalBowler.containsKey(currentBowler)) {
                    HashMap<String, Integer> bowlerDetails = mostEconomicalBowler.get(currentBowler);
                    if (!(deliveries.get(index).getExtraRuns() == 0)) {
                        bowlerDetails.put("total_balls", (bowlerDetails.get("total_balls")) - 1);
                    }
                    bowlerDetails.put("total_balls", (bowlerDetails.get("total_balls")) + 1);

                    int current_run = deliveries.get(index).getTotalRuns();
                    bowlerDetails.put("total_runs", (bowlerDetails.get("total_runs")) + current_run);
                }
                mostEconomicalBowler.put(currentBowler, mostEconomicalBowler.get(currentBowler));
            }
        }
        HashMap<String, Double> bowlerEconomy = new HashMap<>();

        for (String key : mostEconomicalBowler.keySet()) {
            HashMap<String, Integer> value = mostEconomicalBowler.get(key);
            int total_run = mostEconomicalBowler.get(key).get("total_runs");
            int total_balls = mostEconomicalBowler.get(key).get("total_balls");
            double economyOfBowler = (double) total_run / total_balls * 6;

            economyOfBowler = Double.valueOf(String.format("%.2f", economyOfBowler));
            bowlerEconomy.put(key, economyOfBowler);
        }

        List<Map.Entry<String, Double>> rankingList = new ArrayList<>(bowlerEconomy.entrySet());
        Collections.sort(rankingList, Comparator.comparing(Map.Entry::getValue));

        for (int i = 0; i < rankingList.size(); i++) {
            Map.Entry<String, Double> entry = rankingList.get(i);
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println((i + 1) + " " + key + " : " + value);
        }
    }

    public static int[] getMatchIdRange(int year, List<Match> matches) {
        int startId = 0;
        int endId = 0;
        boolean flagStart = true;

        for (int id = 0; id < matches.size(); id++) {
            int currentYear = matches.get(id).getSeason();

            if (year == currentYear && flagStart) {
                startId = id;
                flagStart = false;
            } else if (year == currentYear) {
                endId = id;
            }
        }
        int[] matchIds = {++startId, ++endId};
        return matchIds;
    }

    public static void secondHighestWicketTakerOfEachTeam(List<Delivery> deliveries, int matchIdNumber) {
        HashMap<String, Integer> wicketTakersOfTeam1 = new HashMap<>();
        HashMap<String, Integer> wicketTakersOfTeam2 = new HashMap<>();
        String team1 = "";
        String team2 = "";

        for (int index = 0; index < deliveries.size(); index++) {
            if (deliveries.get(index).getMatchId() == matchIdNumber && deliveries.get(index).getInning() == 1) {
                team1 = deliveries.get(index).getBowlingTeam();
                if (deliveries.get(index).getPlayerDismissed() != "") {
                    if (!wicketTakersOfTeam1.containsKey(deliveries.get(index).getBowler())) {
                        wicketTakersOfTeam1.put(deliveries.get(index).getBowler(), 1);
                    } else {
                        wicketTakersOfTeam1.put(deliveries.get(index).getBowler(), (wicketTakersOfTeam1.get(deliveries.get(index).getBowler()) + 1));
                    }
                }
            }
            if (deliveries.get(index).getMatchId() == matchIdNumber && deliveries.get(index).getInning() == 2) {
                team2 = deliveries.get(index).getBowlingTeam();
                if (deliveries.get(index).getPlayerDismissed() != "") {
                    if (!wicketTakersOfTeam2.containsKey(deliveries.get(index).getBowler())) {
                        wicketTakersOfTeam2.put(deliveries.get(index).getBowler(), 1);
                    } else {
                        wicketTakersOfTeam2.put(deliveries.get(index).getBowler(), (wicketTakersOfTeam2.get(deliveries.get(index).getBowler()) + 1));
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> sortedWicketTakerOfTeam1 = wicketTakersOfTeam1.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue()))
                .toList();
        List<Map.Entry<String, Integer>> sortedWicketTakerOfTeam2 = wicketTakersOfTeam2.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue()))
                .toList();

        if (sortedWicketTakerOfTeam1.get(2) != null) {
            System.out.println("2nd Highest wicket Taker of Team " + team1 + " is " + sortedWicketTakerOfTeam1.get(2));
        } else {
            System.out.println("There is no Second Wicket Taker");
        }
        if (sortedWicketTakerOfTeam1.get(2) != null) {
            System.out.println("2nd Highest wicket Taker of Team " + team2 + " is " + sortedWicketTakerOfTeam2.get(2));
        } else {
            System.out.println("There is no Second Wicket Taker");
        }
    }

    public static void findHighestWicketTakerPerTeamForEachSeason(List<Delivery> deliveries, List<Match> matches) {
        ArrayList<Integer> years = new ArrayList<>();

        for (int i = 0; i < matches.size(); i++) {
            if (!years.contains(matches.get(i).getSeason())) {
                years.add(matches.get(i).getSeason());
                findHighestWicketTakerPerTeamOfYear(deliveries, matches, matches.get(i).getSeason());
            }
        }
    }

    public static void findHighestWicketTakerPerTeamOfYear(List<Delivery> deliveries, List<Match> matches, int year) {
        HashSet<Integer> matchIds = new HashSet<>();

        for (int j = 0; j < matches.size(); j++) {
            if (matches.get(j).getSeason() == year) {
                matchIds.add(matches.get(j).getMatchId());
            }
        }
        HashMap<String, HashMap<String, Integer>> wicketTakers = new HashMap<>();

        for (int index = 0; index < deliveries.size(); index++) {
            int currentId = deliveries.get(index).getMatchId();

            if (matchIds.contains(currentId) && !deliveries.get(index).getPlayerDismissed().equals("")) {
                String currentTeam = deliveries.get(index).getBowlingTeam();
                String currentBowler = deliveries.get(index).getBowler();
                HashMap<String, Integer> bowlerDetails = new HashMap<>();

                if (!wicketTakers.containsKey(currentTeam)) {
                    bowlerDetails.put(currentBowler, 1);
                    wicketTakers.put(currentTeam, bowlerDetails);
                } else {
                    if (!wicketTakers.get(currentTeam).containsKey(currentBowler)) {
                        bowlerDetails.putAll(wicketTakers.get(currentTeam));
                        bowlerDetails.put(currentBowler, 1);
                        wicketTakers.put(currentTeam, bowlerDetails);
                    } else {
                        bowlerDetails.putAll(wicketTakers.get(currentTeam));
                        bowlerDetails.put(currentBowler, (wicketTakers.get(currentTeam).get(currentBowler)) + 1);
                        wicketTakers.put(currentTeam, bowlerDetails);
                    }
                }
            }
        }
        for (String wicketTaker : wicketTakers.keySet()) {
            HashMap<String, Integer> bowlerDetails = wicketTakers.get(wicketTaker);
            List<Map.Entry<String, Integer>> sortedBowlerDetails = new ArrayList<>(bowlerDetails.entrySet());
            Collections.sort(sortedBowlerDetails, Comparator.comparing(Map.Entry::getValue));
            System.out.println(year + " : " + wicketTaker + " : " + sortedBowlerDetails.get(sortedBowlerDetails.size() - 1));
        }
    }

    public static void findHeighestScoreOfBatsmanByVenue(List<Delivery> deliveries, List<Match> matches) {
        HashMap<Integer, String> matchVenue = new HashMap<>();
        HashSet<Integer> matchIdsIn2015 = new HashSet<>();

        for (int index = 0; index < matches.size(); index++) {
            if (matches.get(index).getSeason() == 2015) {
                matchVenue.put(matches.get(index).getMatchId(), matches.get(index).getVenue());
                matchIdsIn2015.add(matches.get(index).getMatchId());
            }
        }
        HashMap<String, HashMap<String, Integer>> heighestStrikeRateByVenue = new HashMap<>();

        for (int index = 0; index < deliveries.size(); index++) {
            int currentMatchId = deliveries.get(index).getMatchId();

            if (matchIdsIn2015.contains(currentMatchId)) {
                String currentMatchVenue = matchVenue.get(currentMatchId);
                String currentPlayer = deliveries.get(index).getBatsman();
                int currentRun = deliveries.get(index).getBatsmanRuns();
                HashMap<String, Integer> playerDetails = new HashMap<>();

                if (!heighestStrikeRateByVenue.containsKey(currentMatchVenue)) {
                    playerDetails.put(currentPlayer, currentRun);
                    heighestStrikeRateByVenue.put(currentMatchVenue, playerDetails);
                } else {
                    if (!heighestStrikeRateByVenue.get(currentMatchVenue).containsKey(currentPlayer)) {
                        playerDetails.putAll(heighestStrikeRateByVenue.get(currentMatchVenue));
                        playerDetails.put(currentPlayer, currentRun);
                        heighestStrikeRateByVenue.put(currentMatchVenue, playerDetails);
                    } else {
                        playerDetails.putAll(heighestStrikeRateByVenue.get(currentMatchVenue));
                        playerDetails.put(currentPlayer, currentRun + (heighestStrikeRateByVenue.get(currentMatchVenue).get(currentPlayer)));
                        heighestStrikeRateByVenue.put(currentMatchVenue, playerDetails);
                    }
                }
            }
        }
        for (String heighestScore : heighestStrikeRateByVenue.keySet()) {
            HashMap<String, Integer> bowlerDetails = heighestStrikeRateByVenue.get(heighestScore);
            List<Map.Entry<String, Integer>> sortedBatsmanDetails = new ArrayList<>(bowlerDetails.entrySet());
            Collections.sort(sortedBatsmanDetails, Comparator.comparing(Map.Entry::getValue));
            System.out.println(heighestScore + " : " + sortedBatsmanDetails.get(sortedBatsmanDetails.size() - 1));
        }
    }
}

