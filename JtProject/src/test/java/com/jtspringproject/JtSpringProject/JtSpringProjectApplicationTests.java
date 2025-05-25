import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;




@SpringBootTest
@TestPropertySource(properties = { /* your properties if needed */ })
@Disabled // Temporarily disable tests
public class JtSpringProjectApplicationTests {

	@Test
	void contextLoads() {
	}
}
