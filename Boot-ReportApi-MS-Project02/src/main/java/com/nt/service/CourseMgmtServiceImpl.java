package com.nt.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.directory.SearchResult;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CourseDetails;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import com.nt.repository.ICourseDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service("courseService")
public class CourseMgmtServiceImpl implements ICourseMgmtService {
 @Autowired	
 private ICourseDetailsRepository courseRepository;
	@Override
	public Set<String> showAllCourseCategories() {
		return  courseRepository.getUniqueCourseCategories();
	}

	@Override
	public Set<String> showAllFacultyNames() {
		return courseRepository.getUniqueFacultyNames();
	}

	@Override
	public Set<String> showAllTraningModes() {
		return courseRepository.getUniqueTrainingModes();
	}

	@Override
	public List<SearchResults> showCoursesByFilters(SearchInputs inputs) {
		CourseDetails entity=new CourseDetails();
		String courseCategory=inputs.getCourseCategory();
		     if(StringUtils.hasLength(courseCategory))
	                  entity.setCourseCategory(courseCategory);
	    String facultyName=inputs.getFacultyName() ;
		      if(StringUtils.hasLength(facultyName)) 
			         entity.setFacultyName(facultyName);
		      
		String trainingMode=inputs.getTrainingMode();
		      if(StringUtils.hasLength(trainingMode))
		    	            entity.setTrainingMode(trainingMode);
		 LocalDateTime startOn=inputs.getStartsOn() ;
		       if(!ObjectUtils.isEmpty(startOn))
		    	           entity.setStartTime(startOn);  
		Example<CourseDetails> example=Example.of(entity);  ;
		List<CourseDetails> listCourses=courseRepository.findAll(example);
		//convert list<CourseDetails> object to List<SearchResults> object
		List<SearchResults> listResults=new ArrayList<SearchResults>();
		 listCourses.forEach(course->{
			SearchResults result= new SearchResults();
		   BeanUtils.copyProperties(course, result);
		   listResults.add(result);
		});
		return listResults;
	  }

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse response) throws Exception {
		List<SearchResults> listResults=showCoursesByFilters(inputs);
		 //create document object
		Document document=new Document(PageSize.A4);
		//get pdfwriter obj to write to the content in document and response object
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		  //define font  for the paragraph
		 Font font=FontFactory.getFont(FontFactory.TIMES_BOLD);
		 font.setSize(30);
		 font.setColor(Color.RED);
		 
		 //create the paragraph having content and font-style
		 Paragraph para=new Paragraph("Search Report of Courses", font);
		 para.setAlignment(Paragraph.ALIGN_CENTER);
		 //add paragraph to document
	     document.add(para);
	     
	    //display search results as pdf table
	     PdfPTable table=new PdfPTable(10);
	     table.setWidthPercentage(80);
	     table.setWidths(new float[]{5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f,5.0f});
	     table.setSpacingBefore(4.0f);
	     
	   //perpare heading row cells in the pdf table
	     
	     PdfPCell cell=new PdfPCell();
	     cell.setBackgroundColor(Color.gray);
	     cell.setPadding(5);
	     Font cellFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	     cellFont.setColor(Color.BLACK);
	     
	     cell.setPhrase(new Phrase("CourseId",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("CourseName",cellFont) );
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("Location",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("Fees",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("CourseCategory",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("FacultyName",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("TrainingMode",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("StartTime",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("adminContact",cellFont));
	     table.addCell(cell);
	     cell.setPhrase(new Phrase("courseStatus",cellFont));
	     table.addCell(cell);
	     
	    // add data cells to pdftable
	     listResults.forEach(result->{
	    	 table.addCell(String.valueOf(result.getCourseId()));
	    	 table.addCell(result.getCourseName());
	    	 table.addCell(result.getLocation());
	    	 table.addCell(String.valueOf(result.getFees()));
	    	 table.addCell(result.getCourseCategory());
	    	 table.addCell(result.getFacultyName());
	    	 table.addCell(result.getTrainingMode());
	    	 table.addCell(result.getStartTime().toString());
	    	 table.addCell(String.valueOf(result.getAdminContact()));
	    	 table.addCell(result.getCourseStatus());
	     });
		//add table to document
	     document.add(table);
	     //close the document
	     document.close();
	}//end of method

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse response) throws Exception {
	    List<SearchResults> listResults=showCoursesByFilters(inputs);
	    //create excel work book
	    HSSFWorkbook workBook=new HSSFWorkbook();
	  //create sheet in the work book
	    HSSFSheet sheet1=workBook.createSheet("CourseDetails");
	    //creating Heading row in sheet one
        HSSFRow headingRow=sheet1.createRow(0);
     
        headingRow.createCell(0).setCellValue("CourseId");
        headingRow.createCell(1).setCellValue("CourseName");
        headingRow.createCell(2).setCellValue("Location");
        headingRow.createCell(3).setCellValue("Fees");
        headingRow.createCell(4).setCellValue("CourseCategory");
        headingRow.createCell(5).setCellValue("FacultyName");
        headingRow.createCell(6).setCellValue("TrainingMode");
        headingRow.createCell(7).setCellValue("StartTime");
        headingRow.createCell(8).setCellValue("AdminContact");
        headingRow.createCell(9).setCellValue("CourseStatus");
       
        //add data rows to the sheet
        int i=1;
        for(SearchResults result:listResults) {
        	HSSFRow dataRow=sheet1.createRow(i);
        	dataRow.createCell(0).setCellValue(result.getCourseId());
        	dataRow.createCell(1).setCellValue(result.getCourseName());
        	dataRow.createCell(2).setCellValue(result.getLocation());
        	dataRow.createCell(3).setCellValue(result.getFees());
        	dataRow.createCell(4).setCellValue(result.getCourseCategory());
        	dataRow.createCell(5).setCellValue(result.getFacultyName());
        	dataRow.createCell(6).setCellValue(result.getTrainingMode());
        	dataRow.createCell(7).setCellValue(result.getStartTime());
        	dataRow.createCell(8).setCellValue(result.getAdminContact());
        	dataRow.createCell(9).setCellValue(result.getCourseStatus());
        	i++;
        }
        //get OutputStream object pointing to response object
                    ServletOutputStream outputStream =    response.getOutputStream();
                    workBook.write(outputStream);
                   outputStream.close();
                   workBook.close();
	}

}
