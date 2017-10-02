package org.bioplat.classifiers;

import org.bioplat.classifiers.service.Profiles;
import org.bioplat.classifiers.service.RClassifier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.unit_testing)
public class ClassifiersApplicationTests {

	@MockBean
	private RClassifier rClassifier;

	@Test
	public void contextLoads() {
	}

	@Test public void checkmock(){
		Mockito.when(rClassifier.create(Mockito.any())).thenReturn("ok");
		Assert.assertTrue(rClassifier.create(null).equals("ok"));
		Assert.assertTrue(rClassifier != null);
	}

}
