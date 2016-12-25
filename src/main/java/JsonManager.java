import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pogorilyi Yurii on 20.12.2016.
 */
public class JsonManager {
    public static void write(JSONObject obj, String fileName){
        try {

            FileWriter file = new FileWriter(Constants.RESOURSES_PATH + fileName);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(obj);
    }

    public static void generateParams(int vertexCount, int figuresCount){
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject polygonParams = new JSONObject();
        polygonParams.put("figuresCount", figuresCount);
        polygonParams.put("vertexCount", vertexCount);

        list.add(polygonParams);

        obj.put("params", list);

        JsonManager.write(obj, "input.json");
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

            Object inputObj = parser.parse(new FileReader(Constants.RESOURSES_PATH + "input.json"));

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
}
