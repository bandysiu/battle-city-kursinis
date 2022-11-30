package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LevelService {

    public static int[][] getLevel(int levelNumber){
        List<List<Integer>> levelList = readLevelFile(levelNumber);
        int[][] level = levelList.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        return level;
    }


    private static List<List<Integer>> readLevelFile(int levelNumber) {
        List<List<Integer>> level = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Levels/level" + levelNumber));

            String line;

            while ((line = reader.readLine()) != null) {
                List<Integer> rowLine = new ArrayList<>();
                String[] values = line.trim().split("");
                for (String string : values) {
                    if (!string.isEmpty()) {
                        switch (string) {
                            case "#":
                                rowLine.add(2);
                                break;
                            case "@":
                                rowLine.add(1);
                                break;
                            case "B":
                                rowLine.add(3);
                                break;
                            default:
                                rowLine.add(0);
                                break;
                        }
                    }
                }
                level.add(rowLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return level;
    }

}
