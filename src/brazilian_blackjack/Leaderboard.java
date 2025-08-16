package brazilian_blackjack;
import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final int MAX_ENTRIES = 10;
    
    
    public static void recordHiScore(String playerName, int score) 
    {
        List<ScoreEntry> leaderboard = loadLeaderboard();

        // Add new score
        leaderboard.add(new ScoreEntry(playerName, score));

        // Sort by score in descending order
        leaderboard.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        // Keep only the top MAX_ENTRIES
        if (leaderboard.size() > MAX_ENTRIES) 
        {
            leaderboard = leaderboard.subList(0, MAX_ENTRIES);
        }

        saveLeaderboard(leaderboard);
    }
    
    
    static List<ScoreEntry> loadLeaderboard() {
        List<ScoreEntry> leaderboard = new ArrayList<>();
        File file = new File(LEADERBOARD_FILE);

        if (!file.exists()) 
        {
            return leaderboard;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 2) 
                {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    leaderboard.add(new ScoreEntry(name, score));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading leaderboard: " + e.getMessage());
        }

        return leaderboard;
    }
    
    private static void saveLeaderboard(List<ScoreEntry> leaderboard) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE))) 
        {
            for (ScoreEntry entry : leaderboard) 
            {
                writer.write(entry.getName() + "," + entry.getScore());
                writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }
    
    public static void printLeaderboard() {
        List<ScoreEntry> leaderboard = loadLeaderboard();
        System.out.println("\n--- High Score Leaderboard ---");
        for (int i = 0; i < leaderboard.size(); i++) 
        {
            ScoreEntry entry = leaderboard.get(i);
            System.out.println((i + 1) + ". " + entry.getName() + " - " + entry.getScore());
        }
    }
    
    
    private static class ScoreEntry {
    	
        private final String name;
        private final int score;

        public ScoreEntry(String name, int score) 
        {
            this.name = name;
            this.score = score;
        }

        public String getName() 
        {
            return name;
        }

        public int getScore() 
        {
            return score;
        }
    }
    
}
