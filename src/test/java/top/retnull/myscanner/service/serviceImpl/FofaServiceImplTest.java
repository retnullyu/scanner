package top.retnull.myscanner.service.serviceImpl;

import com.r4v3zn.fofa.core.DO.FofaData;
import com.r4v3zn.fofa.core.client.FofaClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest
class FofaServiceImplTest {
    @Autowired
    SysUserService sysUserService;
    @Test
    void fofaquery() throws Exception {
        SysUser sysUser = sysUserService.findById(1);
        String email = sysUser.getEmail();
        String token = sysUser.getFofakey();
        FofaClient fofaClient = new FofaClient(email,token);

        FofaData fofadata = fofaClient.getData("app=\"Solr\"",1,100,"ip,title");
        ArrayList<String> result = new ArrayList<>();
        JsonUtils.objectToJson(fofadata.getResults());
    }

    @Test
    void fofahistory() {
    }

    @Test
    void export() {
    }
}