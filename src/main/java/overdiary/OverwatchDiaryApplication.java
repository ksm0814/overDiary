package overdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import overdiary.helper.SchedulerConfig;


@SpringBootApplication
@EnableJpaAuditing
@Import({SchedulerConfig.class })

public class OverwatchDiaryApplication {

	public static void main(String[] args) {

		SpringApplication.run(OverwatchDiaryApplication.class, args);
	}


}
