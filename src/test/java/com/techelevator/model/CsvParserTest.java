package com.techelevator.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.CSV.CsvData;
import com.techelevator.model.CSV.CsvParser;

public class CsvParserTest {

	private CsvParser dbw;
	private String filepath;
	private List<CsvData> list;

	@Before
	public void setup() {
		dbw = new CsvParser();
		filepath = "prototyping/sampleCSV.csv";
		list = new ArrayList<CsvData>();
	}

	@Test
	public void correct_amount_of_records_returned() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals(17, list.size());
	}

	@Test
	public void studentname_returned_correctly() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals("Lastname, Firstname", list.get(0).getStudentName());
	}

	@Test
	public void previous_class_thoughts_returned_correctly() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals("Somewhat uninteresting", list.get(8).getContentOfPreviousClassAnswer());
	}

	@Test
	public void survey_title_returned_correctly() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals("Daily Pulse Survey (copy)", list.get(8).getSurveyTitle());
	}

	@Test
	public void survey_date_returned_correctly() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals("Wednesday, May 23 2018 09:02 AM", list.get(3).getSurveyDate());
	}

	@Test
	public void survey_room_returned_correctly() throws IOException {
		list = dbw.getListOfCSVDataFromFile(filepath);
		Assert.assertEquals("tecbusjavab", list.get(7).getSurveyRoom());
	}
}
