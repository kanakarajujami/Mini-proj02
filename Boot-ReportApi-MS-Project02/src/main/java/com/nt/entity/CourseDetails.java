package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="COURSE_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseDetails {
@Id
@GeneratedValue
private Integer courseId;
@Column(length=50)
@NonNull
private String courseName;
@Column(length=50)
@NonNull
private String location;
@Column(length=50)
@NonNull
private String courseCategory;
@Column(length=50)
@NonNull
private String facultyName;
@NonNull
@Column
private Double fees;
@NonNull
@Column(length=50)
private String  adminName;
@NonNull
private Long adminContact;
@Column(length=30)
@NonNull
private String trainingMode;
@Column
@NonNull
private LocalDateTime startTime;
@Column(length=50)
@NonNull
private String courseStatus;
@CreationTimestamp()
@NonNull
@Column(insertable=true,updatable=false)
private LocalDateTime creationDate;
@Column(insertable=false,updatable=true)
@UpdateTimestamp
private LocalDateTime updationDate;
@NonNull
@Column(length=50)
private String createdBy;
@NonNull
@Column(length=50)
private String updatedBy;
}
