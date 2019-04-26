package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.model.Answer.Answer;
import com.techelevator.model.Answer.AnswerDAO;
import com.techelevator.model.Answer.AnswerStats;
import com.techelevator.model.Answer.AnswerStatsDAO;
import com.techelevator.model.CSV.CsvData;
import com.techelevator.model.CSV.CsvParser;
import com.techelevator.model.Log.LogDAO;
import com.techelevator.model.Question.Question;
import com.techelevator.model.Question.QuestionDAO;
import com.techelevator.model.Student.StudentDAO;
import com.techelevator.model.Survey.Survey;
import com.techelevator.model.Survey.SurveyDAO;
import com.techelevator.model.Survey.SurveyQuestionDAO;
import com.techelevator.model.Survey.SurveySubmission;
import com.techelevator.model.User.User;
import com.techelevator.model.User.UserDAO;

@Controller
public class SurveyController {
	
	private CsvParser csvParser = new CsvParser(); 
	
	@Value("${path}")
	private String path;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SurveyDAO surveyDao;
	
	@Autowired
	private LogDAO logDao;
	
	@Autowired
	private AnswerDAO answerDao;
	
	@Autowired
	private QuestionDAO questionDao;
	
	@Autowired
	private AnswerStatsDAO answerStatsDao;
	
	@Autowired
	private SurveyQuestionDAO surveyQuestionDao;
	
	@Autowired
	private StudentDAO studentDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyView(ModelMap map, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			List<Survey> surveyList = surveyDao.getAllSurveys();
			
			map.addAttribute("surveys", surveyList);
			
			return "survey";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/surveyDetails", method=RequestMethod.GET)
	public String displaySurveyDetailView(ModelMap map, @RequestParam long surveyId, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			List<Survey> surveyList = surveyDao.getAllSurveys();
			
			for(Survey survey : surveyList) {
				if(survey.getSurveyId() == surveyId) {
					map.addAttribute("selectedSurvey", survey);
				}
			}
			
			List<Question> questionList = questionDao.getQuestionsBySurveyId(surveyId);
			List<AnswerStats> surveyStats = answerStatsDao.getCountAndTextOfUniqueAnswersForSurvey(surveyId); 
			
			map.addAttribute("questions", questionList);
			map.addAttribute("surveyStats", surveyStats); 
			

			return "surveyDetails";
		} else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping(path="/deleteSurvey", method=RequestMethod.POST) 
	public String deleteSurvey(@RequestParam long id, HttpSession session) {
		
		surveyDao.deleteExistingSurvey(id);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Deleted Survey " + id);
		
		return "redirect:/survey";
	}
	
	@RequestMapping(path="/editSurvey", method=RequestMethod.POST) 
	public String editSurvey(@RequestParam long id, @RequestParam String campus, @RequestParam String cohortNumber, @RequestParam String instructor, @RequestParam String topic, HttpSession session) {
		
		surveyDao.updateSurvey(id, campus, cohortNumber, instructor, topic);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Edited Survey " + id);
		
		return "redirect:/surveyDetails?surveyId=" + id;
	}
	
	@RequestMapping(path="/editAnswer", method=RequestMethod.POST) 
	public String editSurvey(@RequestParam long id, @RequestParam String answerText, @RequestParam String beforeAnswerText, @RequestParam long questionId, @RequestParam long surveyId, HttpSession session) {
		
		answerDao.updateAnswerText(answerText, id);
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "User Edited Answer " + id + " || Before: " + beforeAnswerText + " After: " + answerText );
		
		return "redirect:/answers?questionId=" + questionId + "&surveyId=" + surveyId;
	}
	
	@RequestMapping(path="/answers", method=RequestMethod.GET)
	public String displayAnswerDetailView(ModelMap map, @RequestParam long surveyId, @RequestParam long questionId, HttpSession session) {
		
		if(session.getAttribute("currentUser") != null) {
			
			List<Answer> answerList = answerDao.getStudentNameAndAnswerBySurveyIdAndQuestionId(surveyId, questionId);
			List<Question> questionList = questionDao.getQuestionsBySurveyId(surveyId);
			List<AnswerStats> surveyQuestionStats = answerStatsDao.getCountAndTextOfUniqueAnswersForSurveyQuestion(questionId, surveyId); 
			
			for(Question question : questionList) {
				if(question.getQuestionId() == questionId) {
					map.addAttribute("selectedQuestion", question);
				}
			}
			
			map.addAttribute("answers", answerList);
			map.addAttribute("surveyQuestionStats", surveyQuestionStats); 

			for(AnswerStats stats : surveyQuestionStats) {
				if(stats.getAnswerText().length() > 25) {
					stats.setAnswerText(stats.getAnswerText().substring(0,23) + "...");
				}
				map.addAttribute("truncatedStats", surveyQuestionStats);
			}
			return "answers";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(SurveySubmission submission , ModelMap map, HttpSession session) throws IOException {
		
		User user = ((User) session.getAttribute("currentUser"));
		
		logDao.insertLog(user.getUserName(), "Uploaded Survey");
		
		
		File csvPath = getCSVFilePath();
		String csvName = csvPath + File.separator + "csvFile";
		
		createCSV(submission.getFile(), csvName);

				
		List<CsvData> csvData = csvParser.getListOfCSVDataFromFile(csvName);  
		
		surveyDao.createNewSurvey(csvData.get(0).getSurveyDate(), csvData.get(0).getSurveyTitle(), csvData.get(0).getSurveyRoom(), submission.getLocation(), submission.getCohortNumber(), submission.getInstructor(), submission.getTopic());
		long surveyId = surveyDao.getNextSurveyId() -1; 
		long question1Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion1()); 
		long question2Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion2()); 
		long question3Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion3());  
		long question4Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion4()); 
		long question5Id = questionDao.getQuestionIdByQuestionText(csvData.get(0).getQuestion5());  

			
		for (CsvData eachLine : csvData) {
			answerDao.createNewAnswer(question1Id, eachLine.getPresenceAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question2Id, eachLine.getPaceOfYesterdaysClassAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question3Id, eachLine.getContentOfPreviousClassAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question4Id, eachLine.getUnderstandingOfPreviousDaysMaterialAnswer(), eachLine.getStudentId(), surveyId);
			answerDao.createNewAnswer(question5Id, eachLine.getEnergyLevel(), eachLine.getStudentId(), surveyId);
			studentDao.insertStudentIntoTableIfStudentDoesNotExits( eachLine.getStudentId(), eachLine.getStudentName());
		}
				
		surveyQuestionDao.createNewRow(question1Id, surveyId);
		surveyQuestionDao.createNewRow(question2Id, surveyId);
		surveyQuestionDao.createNewRow(question3Id, surveyId);
		surveyQuestionDao.createNewRow(question4Id, surveyId);
		surveyQuestionDao.createNewRow(question5Id, surveyId);
		
		return "redirect:/survey";
	}
	
	private File getCSVFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + path; 
	}
	
	private void createCSV(MultipartFile file, String name) {
		File image = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			stream.write(file.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
