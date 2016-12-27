import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class FileIOManager {
    public static void write(JSONObject obj, String fileName){
        try {

            FileWriter file = new FileWriter(Constants.RESOURCES_PATH + fileName);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(obj);
    }

    public static void generateParams(int vertexCount, int figuresCount, int step, int expNumber){
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject polygonParams = new JSONObject();
            polygonParams.put("figuresCount", figuresCount * step * expNumber);
            polygonParams.put("vertexCount", vertexCount * step * expNumber);

            list.add(polygonParams);

        obj.put("params", list);

        FileIOManager.write(obj, Constants.INPUT_FILE_NAME);
    }

    /**
     * Key - vertex count
     * Value - figures count
     * @return
     */
    public static HashMap<Long, Long> readParamsList(){
        JSONParser parser = new JSONParser();

        JSONArray params;
        HashMap<Long, Long> vertexFigureCountMap = new HashMap<Long, Long>();
        try {

            Object inputObj = parser.parse(new FileReader(Constants.RESOURCES_PATH + Constants.INPUT_FILE_NAME));

            JSONObject jsonObject = (JSONObject) inputObj;

            // loop array
            params = (JSONArray) jsonObject.get("params");
            Iterator<JSONObject> iterator = params.iterator();
            while (iterator.hasNext()) {
                JSONObject obj = iterator.next();
                long vertCount = (Long) obj.get("vertexCount");
                long figCount = (Long) obj.get("figuresCount");
                if (vertexFigureCountMap.containsKey(vertCount)){
                    vertexFigureCountMap.put(vertCount, vertexFigureCountMap.get(vertCount) + figCount);
                } else {
                    vertexFigureCountMap.put(vertCount, figCount);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vertexFigureCountMap;
    }

    public static void writeString(String s){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.RESOURCES_PATH + Constants.OUTPUT_FILE_NAME, true))) {
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName){
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                result += sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
