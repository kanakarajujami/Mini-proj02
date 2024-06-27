package com.nt.service;

import java.util.List;

import java.util.Set;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResults;
import jakarta.servlet.http.HttpServletResponse;

public interface ICourseMgmtService {
public Set<String> showAllCourseCategories();
public Set<String> showAllFacultyNames();
public Set<String> showAllTraningModes();
public List<SearchResults> showCoursesByFilters(SearchInputs inputs);
public void generatePdfReport(SearchInputs inputs,HttpServletResponse response) throws Exception;
public void generateExcelReport(SearchInputs inputs,HttpServletResponse response) throws Exception;
}
