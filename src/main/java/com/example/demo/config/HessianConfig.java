package com.example.demo.config;

import com.example.demo.service.CommentService;
import com.example.demo.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfig {

    @Autowired
    private CommentService commentService;

    @Bean(name = "/interconnect/commentService")
    public RemoteExporter documentService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(commentService);
        exporter.setServiceInterface(CommentService.class);
        return exporter;
    }

}
