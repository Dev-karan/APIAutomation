package com.test.apiautomation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * Contains method realted to readign test data and getting configuration from an ini file and storing them in hashmap
 */
public class JavaUtils {

	public static HashMap<String, String> configProperties = new HashMap<String, String>();

	/*
	 * read configuration from config.ini file i.e inside test-data folder and store them in a hashmap
	 */
	public HashMap<String, String> readConfigProperties() {
		Set<Entry<String, String>> dataSet;
		Ini ini;
		try {
			ini = new Ini(new File("./test-data/config.ini"));

			Ini.Section section = ini.get("Common");
			dataSet = section.entrySet();
			dataSet.addAll(section.entrySet());
			for (Map.Entry<String, String> set : dataSet) {
				configProperties.put(set.getKey().toString(), set.getValue().toString());
			}
			return configProperties;
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * converts a List of HashMap to a 2D object array
	 * and this is called from data provider method   
	 */
	public Object[][] returnAllUniqueValuesInMap(String fileKey, String apiTestData, String testType) {

		List<HashMap<String, String>> listValues = returnRowsUniqueValueBasedOnTestTypeList(fileKey, apiTestData,
				testType);

		Object[][] allValues = new Object[listValues.size()][1];

		for (int i = 0; i < listValues.size(); i++) {
			allValues[i][0] = listValues.get(i);
		}
		return allValues;
	}

	/*
	 * reads the json file for particular API for a particular test type e.g positive, negative etc
	 */
	public List<HashMap<String, String>> returnRowsUniqueValueBasedOnTestTypeList(String fileKey, String apiTestData,
			String testType) {

		HashMap<String, String> dataMap = new HashMap<String, String>();
		List<HashMap<String, String>> allValues = new ArrayList<HashMap<String, String>>();
		try {
			FileReader file = new FileReader(configProperties.get(fileKey));
			if (file != null) {
				System.out.println();
			}
			System.out.println(configProperties.get(fileKey));

			BufferedReader br = new BufferedReader(file);
			// get object json object for the api
			JsonElement jsonParser = new JsonParser().parse(br);
			JsonArray apiData = jsonParser.getAsJsonObject().get(apiTestData).getAsJsonObject()
					.get(testType.toLowerCase()).getAsJsonArray();
			for (int i = 0; i < apiData.size(); i++) {
				JsonObject dataObject = apiData.get(i).getAsJsonObject();
				Set<String> keysItr = dataObject.keySet();
				for (String key : keysItr) {
					dataMap.put(key, dataObject.get(key).getAsString());
				}
				allValues.add(dataMap);
				dataMap = new HashMap<String, String>();
			}
			// get array for test type

			return allValues;

		} catch (NullPointerException e) {
			throw new NullPointerException("Failed due to NullPointerException" + e);
		} catch (IOException e) {
			throw new NullPointerException("Failed due to IOException" + e);
		}

	}
}
