package com.itheima.common.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FdfsClientConfig.class)
//@ConditionalOnProperty(name = "fastdfs.enable",havingValue = "on")
public class FdfsConfiguration {


    @Bean
    public FastDFSClientUtil getFastDFSClientUtil(){
        return new FastDFSClientUtil();
    }
}
