package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {
	
	public Map<String,String> loadClassMethods() throws FileNotFoundException, IOException, ParseException
	{
		
		Map<String,String> classMethodMap=new HashMap<String,String>();
		String path=System.getProperty("user.dir")+"//src//test//resources//jsons//classmethods.json";
		JSONParser parser=new JSONParser();
		JSONObject json= (JSONObject) parser.parse(new FileReader(new File(path)));
        //System.out.println(json.toString());
        JSONArray classDetails=(JSONArray) json.get("classdetails");
        //System.out.println(classdetails.toString());
        
        for(int cmid=0;cmid<classDetails.size();cmid++)
        {
        	JSONObject classDetail=(JSONObject) classDetails.get(cmid);
        	String className=(String) classDetail.get("class");
        	System.out.println(className);
        	JSONArray methods=(JSONArray) classDetail.get("methods");
        	for(int mId=0;mId<methods.size();mId++)
        	{
        		String method=(String) methods.get(mId);
        		System.out.println(method);
        		classMethodMap.put(method, className);
        	}
        			
        	System.out.println("**************************");		
        }
        
    	System.out.println(classMethodMap);		
    	return classMethodMap;

	}
	
	public int getTestDataSets(String pathFile,String dataflag) throws FileNotFoundException, IOException, ParseException
	{
		
		JSONParser parser=new JSONParser();
		JSONObject json= (JSONObject)parser.parse(new FileReader(new File(pathFile)));
		JSONArray testDataSets=(JSONArray) json.get("testdata");
		for(int dSetiId=0;dSetiId<testDataSets.size();dSetiId++)
		{
			JSONObject testdata=(JSONObject) testDataSets.get(dSetiId);
			String flag=(String) testdata.get("flag");
			if(dataflag.equals(flag))
			{
				JSONArray dataSets=(JSONArray) testdata.get("data");
				return dataSets.size();

			}
		}
		
		return -1;
	}
	
	
	public JSONObject getTestData(String pathFile,String dataflag,int Iteration) throws FileNotFoundException, IOException, ParseException
	{
		
		JSONParser parser=new JSONParser();
		JSONObject json= (JSONObject)parser.parse(new FileReader(new File(pathFile)));
		JSONArray testDataSets=(JSONArray) json.get("testdata");
		for(int dSetiId=0;dSetiId<testDataSets.size();dSetiId++)
		{
			JSONObject testdata=(JSONObject) testDataSets.get(dSetiId);
			String flag=(String) testdata.get("flag");
			if(dataflag.equals(flag))
			{
				JSONArray dataSets=(JSONArray) testdata.get("data");
				JSONObject data= (JSONObject) dataSets.get(Iteration);
				return data;

			}
		}
		
		return null;
	}

	
}
