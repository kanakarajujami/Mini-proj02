package com.nt.runner;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.CourseDetails;
import com.nt.repository.ICourseDetailsRepository;
@Component
public class CourseDetaisRunner implements CommandLineRunner {
	@Autowired
private ICourseDetailsRepository courseRepo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
     /*  List<CourseDetails> listCourses=courseRepo.saveAll(List.of(new CourseDetails("FullStackJava","hyderabad","java","venkatesh sir",14000.0,
    		                                                                                       "sareef",8786578986l,"hybrid",LocalDateTime.now(),"active",LocalDateTime.now(),
    		                                                                                           "varma","null"),
    		                                            new CourseDetails("Ui-ux","hyderabad","html/css/js","shiva sir",16000.0,
                                                                                  "pavithra",90086578986l,"offline",LocalDateTime.now(),"active",LocalDateTime.now(),
                                                                        "varma","null"),
    		                                            new CourseDetails("DotNet","hyderabad","C#","balaarju sir",13000.0,
                                                                "sareef",60786578986l,"hybrid",LocalDateTime.now(),"active",LocalDateTime.now(),
                                                                   "varma","null")   ));*/
       //System.out.println(listCourses.size()+": records are saved");
	}

}
