package dhi.ds;

import dhi.ds.repositories.SolutionRepository;
import dhi.ds.services.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:application-local.properties")
public class SpringBootWebApplicationTests {
	@Test
	public void contextLoads() {
	}

}
