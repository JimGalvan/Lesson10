package exercises;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;


public class validatorTest {
	@Test
	void testPhoneNumber() throws Exception{
		
		assertTrue(validator.phoneNumber("(619)717-4813"));
		
	}
	
	@Test
	void testEmail() throws Exception{
		assertTrue(validator.email("jimmy-galvan@live.com.mx"));
		assertTrue(validator.email("jimmy95@outlook.com"));
		assertTrue(validator.email("john.doe43@domainsample.co.uk"));
//		assertTrue(validator.email("@ohn.doe43@domainsample.co.uk")); to test syntax //



	}

}
