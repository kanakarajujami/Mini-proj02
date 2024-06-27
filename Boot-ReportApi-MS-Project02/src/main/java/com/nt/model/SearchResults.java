package com.nt.model;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResults { 
private Integer courseId;
private String courseName;
private String location;
private Double fees;
private String courseCategory;
private String facultyName;
private String trainingMode;
private LocalDateTime startTime;
private Long adminContact;
private String courseStatus;
}
