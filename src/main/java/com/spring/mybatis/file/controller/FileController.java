package com.spring.mybatis.file.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.mybatis.board.service.BoardFileService;


@Controller
public class FileController {
	@Autowired
	BoardFileService boardfile;
	
	@RequestMapping("/fileDownload/{file}")
    public void fileDownload(@PathVariable String file, HttpServletResponse response) throws IOException {
		boardfile.service(file, response);
    }
	
}
