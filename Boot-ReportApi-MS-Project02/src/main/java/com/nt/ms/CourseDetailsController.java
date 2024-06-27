package com.nt.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.service.CourseMgmtServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/report/api")
public class CourseDetailsController {
	@Autowired
	private CourseMgmtServiceImpl service;
	 @GetMapping("categories")
     public ResponseEntity<?> GetAllCourseCategories(){
	 try { 
	       Set<String>  courseCategories=service.showAllCourseCategories();
	       return new ResponseEntity<Set<String>>(courseCategories,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }//end of method
	 @GetMapping("/faculties")
	 public ResponseEntity<?>  getAllFacultyNames(){
		try {
		    Set<String> facultyNames=service.showAllFacultyNames();
		     return new ResponseEntity<Set<String>>(facultyNames,HttpStatus.OK);
		 }catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
		 
	 }//end of method
	 @GetMapping("/training-modes")
	 public ResponseEntity<?> getAllTrainingModes(){
		 try {
			  Set<String> trainingModes=service.showAllTraningModes();
			  return new ResponseEntity<Set<String>>(trainingModes,HttpStatus.OK);
		 }catch(Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
	 }//end of method
	 @PostMapping("/search")
	 public ResponseEntity<?> getCourseDetailsByFilters(@RequestBody SearchInputs inputs){
		 try {
			  List<SearchResults> listResults=service.showCoursesByFilters(inputs);
			  return new ResponseEntity<List<SearchResults>>(listResults,HttpStatus.OK);
		 }catch(Exception e) {
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
	 }//end of method
	 @PostMapping("pdf-report")
	 public void generatePdfCourseReport(@RequestBody SearchInputs inputs,HttpServletResponse response) {
		 try {
		 //set the content response type
		   response.setContentType("application/pdf");
		   //set the content-disposition make file as downloadble file
		   response.setHeader("Content-Disposition","attachment;filename=courses.pdf");
		   service.generatePdfReport(inputs, response);
		 }
	      catch(Exception e) {
		      e.printStackTrace();
	      }
      }//end of method
	 @PostMapping("excel-report")
	 public void generateExcelCourseReport(@RequestBody SearchInputs inputs,HttpServletResponse response) {
		try {
		 //set the content type
		 response.setContentType("application/vnd.ms-excel");
		 response.setHeader("Content-Disposition","attachment;filename=courses.xls" );
		 service.generateExcelReport(inputs, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	 }//end of method
 }
