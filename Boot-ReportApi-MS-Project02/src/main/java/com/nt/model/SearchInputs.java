package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchInputs {
private String courseCategory;
private String facultyName;
private String trainingMode;
private LocalDateTime startsOn;
}
