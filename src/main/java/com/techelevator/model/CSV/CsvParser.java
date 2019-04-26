package com.techelevator.model.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

	public List<CsvData> getListOfCSVDataFromFile(String path) throws IOException {

		List<CsvData> returnList = new ArrayList<CsvData>();

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		String surveyTitle = bufferedReader.readLine().replace(",,,,,,,,", "");
		String surveyDate = bufferedReader.readLine().replace(",,,,,,,,", "").replace("\"", "");
		String surveyRoom = bufferedReader.readLine().replace(",,,,,,,,", "").replace("Room: ", "");

		bufferedReader.readLine();
		bufferedReader.readLine();
		bufferedReader.readLine();

		CSVParser csvParser = new CSVParser(bufferedReader,
				CSVFormat.DEFAULT
						.withHeader("Presence", "Student Names", "Student Id", "Total Score (0 - 100)",
								"Number of correct answers", "The pace of yesterday's class was:",
								"The content of the previous class was:",
								"I feel my level of understanding of the previous day's material is:",
								"On a scale of 1-10, my energy level today is:")
						.withIgnoreHeaderCase().withTrim().withIgnoreEmptyLines());

		for (CSVRecord csvRecord : csvParser) {
			CsvData csvLine = new CsvData();

			csvLine.setPresenceAnswer(csvRecord.get("Presence"));
			csvLine.setStudentName(csvRecord.get("Student Names"));
			csvLine.setStudentId(csvRecord.get("Student ID"));
			csvLine.setPaceOfYesterdaysClassAnswer(csvRecord.get("The pace of yesterday's class was:"));
			csvLine.setContentOfPreviousClassAnswer(csvRecord.get("The content of the previous class was:"));
			csvLine.setUnderstandingOfPreviousDaysMaterialAnswer(
					csvRecord.get("I feel my level of understanding of the previous day's material is:"));
			csvLine.setEnergyLevel(csvRecord.get("On a scale of 1-10, my energy level today is:"));
			csvLine.setSurveyTitle(surveyTitle);
			csvLine.setSurveyDate(surveyDate);
			csvLine.setSurveyRoom(surveyRoom);
			csvLine.setQuestion1("Presence");
			csvLine.setQuestion2("The pace of yesterday's class was:");
			csvLine.setQuestion3("The content of the previous class was:");
			csvLine.setQuestion4("I feel my level of understanding of the previous day's material is:");
			csvLine.setQuestion5("On a scale of 1-10, my energy level today is:");
			returnList.add(csvLine);

		}
		csvParser.close();
		bufferedReader.close();

		returnList.remove(returnList.size() - 1);
		returnList.remove(0);

		return returnList;

	}
}
