import angel.HelloWorldApplication;
import angel.model.bo.StyleBo;
import angel.model.bo.ValuationBo;
import angel.model.vo.OrderVo;
import angel.model.vo.StyleVo;
import angel.model.vo.ValuationVo;
import angel.model.vto.OrderVto;
import angel.model.vto.StyleVto;
import angel.rest.BaseController;
import angel.rest.OrderController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author hongql
 *
 * @Date Created by blm on 26/10/17.
 * @Description 描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=HelloWorldApplication.class)
@WebAppConfiguration
public class Test1 {

    @Autowired
    private BaseController baseController;

    @Autowired
    private OrderController orderController;

    /*@Test
    public void test() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(baseController).build();
        mockMvc.perform(get("/")).andReturn();
    }*/

    /*@Test
    public void testCreateStyle() throws Exception {
        ValuationVo valuationVo = new ValuationVo(0,99.9);
        ValuationVo valuationVo1 = new ValuationVo(1,99.9);
        List<ValuationVo> valuationVos = new ArrayList<ValuationVo>();
        valuationVos.add(valuationVo);
        valuationVos.add(valuationVo1);
        StyleVo styleVo = new StyleVo("衣服",100,10,valuationVos);
        JsonObject request = new Gson().toJsonTree(styleVo).getAsJsonObject();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        MvcResult result = mockMvc.perform(post("/statistics/v1/style").contentType(MediaType.APPLICATION_JSON).content(request.toString())).andReturn();
    }*/

    /*@Test
    public void testCreateOrder() throws Exception {
        StyleVto styleVto =new StyleVto("4a62d3200c5747af88603281679c2b5f","77fda48fb8dc4b9298c5d792c1994f41",500);
        OrderVto orderVto = new OrderVto("磷啊", Collections.singletonList(styleVto));
        JsonObject request = new Gson().toJsonTree(orderVto).getAsJsonObject();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        MvcResult result = mockMvc.perform(post("/statistics/v1/order").contentType(MediaType.APPLICATION_JSON).content(request.toString())).andReturn();
    }*/

    @Test
    public void testCreateOrder() throws Exception {
        ValuationBo valuationBo = new ValuationBo("77fda48fb8dc4b9298c5d792c1994f41",1,99.99,0.01);
        StyleBo styleBo =new StyleBo("4a62d3200c5747af88603281679c2b5f","好烦",Collections.singletonList(valuationBo));
        JsonObject request = new Gson().toJsonTree(styleBo).getAsJsonObject();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        MvcResult result = mockMvc.perform(put("/statistics/v1/style").contentType(MediaType.APPLICATION_JSON).content(request.toString())).andReturn();
    }
}
