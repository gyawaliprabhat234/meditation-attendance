package cs.miu.edu.meditationattendance;

import cs.miu.edu.meditationattendance.dummydata.DummyData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MeditationAttendanceApplication {

	public static void main(String[] args) {
//		String pass = new BCryptPasswordEncoder().encode("123456");
//		System.out.println("pass: " + pass);
		ApplicationContext context = SpringApplication.run(MeditationAttendanceApplication.class, args);
		DummyData data = context.getBean(DummyData.class);
		data.createDummyData();
	}
}
