import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FetchData {
    public HashMap<Integer, HashMap<String, String>>  fetchedData(String filePath) {

        HashMap<Integer, HashMap<String, String>> data = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine;
            String[] heading = null;

            int rowNumber = 1;

            while ((currentLine = reader.readLine()) != null) {
                String[] values = currentLine.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");

                if (heading == null) {
                    heading = values;
                    continue;
                }

                HashMap<String, String> rowData = new HashMap<>();

                for (int i = 0; i < heading.length && i < values.length; i++) {
                    rowData.put(heading[i], values[i]);
                    //System.out.println();
                }
                data.put(rowNumber++, rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
