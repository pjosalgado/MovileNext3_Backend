package tech.paulosalgado.ifoodorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    protected final Gson GSON = new GsonBuilder().create();

    public void setupBase() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
