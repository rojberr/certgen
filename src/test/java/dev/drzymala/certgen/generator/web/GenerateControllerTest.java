package dev.drzymala.certgen.generator.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class GenerateControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void createX509() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/generateX509?serialNumber=123321&keySize=2048&" +
                                "issuer=CN+%3D+CAcertificate%2C+O+%3D+Test&validFrom=&validTill=&" +
                                "subject=E+%3D+test-user1-without-subjaltname%40drzymala.devCN+%3D+Test+User+1+Without+SubjAltNameO+%3D" +
                                "+Drzymala+Test&basicConstraints=Subject+Type%3DEnd+EntityPath+Length+Constraint%3DNone&" +
                                "keyUsage=Digital+Signature%2C+Key+Encipherment+%28a0%29")
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andReturn();
    }
}