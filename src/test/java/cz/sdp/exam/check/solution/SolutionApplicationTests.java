package cz.sdp.exam.check.solution;

import cz.sdp.exam.check.solution.service.FileService;
import cz.sdp.exam.check.solution.util.FileContentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Solution.class, FileService.class, FileContentBuilder.class})
@TestPropertySource("/application.properties")
class SolutionApplicationTests {


	@Autowired
	private Solution solution;
	@Test
	void contextLoads() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			SolutionApplication sa = new SolutionApplication();
			SpringApplication.run(SolutionApplication.class, null);
		});

		assertThrows(IllegalStateException.class, () -> {
			SolutionApplication sa = new SolutionApplication();
			SpringApplication.run(SolutionApplication.class, " ");
		});
		assertThrows(IllegalStateException.class, () -> {
			SolutionApplication sa = new SolutionApplication();
			SpringApplication.run(SolutionApplication.class, "Unexiistent file path ");
		});
		solution.run("src/test/resources/input.txt");
	}

}
