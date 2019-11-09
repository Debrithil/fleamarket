package swd20.fleamarket;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import swd20.fleamarket.web.FleamarketController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FleamarketApplicationTests {

	// SMOKE -TEST
    @Autowired
    private FleamarketController controller;
	
	@Test
	public void contextLoads() throws Exception {
		
	    assertThat(controller).isNotNull();
	
	    }

}
