package cs.miu.edu.meditationattendance;

import cs.miu.edu.meditationattendance.dummydata.DummyData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MeditationAttendanceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MeditationAttendanceApplication.class, args);
//		DummyData data = context.getBean(DummyData.class);
//		data.createDummyData();
	}
}
