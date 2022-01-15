package top.retnull.myscanner.service.serviceImpl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.r4v3zn.fofa.core.DO.FofaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import top.retnull.myscanner.common.BaseMapper;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonUtils;
import top.retnull.myscanner.vo.ExcelVo;
import top.retnull.myscanner.utils.FofaClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        FofaData fofadata = fofaClient.getData("domain=\"jdl.cn\"",1,10000,"host,title,ip,domain,port,protocol,server");
        List<List<String>> lists = fofadata.getResults();
        ArrayList<ExcelVo> excelVos = new ArrayList<>();
        for(List<String> temp:lists){
            ExcelVo excelVo = ExcelVo.builder().url(temp.get(0)).host(temp.get(2)).title(temp.get(1)).domain(temp.get(3)).port(Integer.valueOf(temp.get(4))).protocol(temp.get(5)).server(temp.get(6)).build();
            excelVos.add(excelVo);
        }
        try {
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/";
            String newName = UUID.randomUUID()+".csv";
            File file = new File(basePath, newName);
            ExcelWriter excelWriter = EasyExcel.write(file, ExcelVo.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(excelVos, writeSheet);
            excelWriter.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fofahistory() {
    }

    @Test
    void export() {
    }
}