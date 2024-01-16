package com.spring.mybatis.file.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.service.BoardFileService;
import com.spring.mybatis.board.service.BoardReadService;
import com.spring.mybatis.board.service.BoardService;
import com.spring.mybatis.login.dto.LoginDTO;
import com.spring.mybatis.main.dto.BoardListDTO;
import com.spring.mybatis.main.dto.BoardSearchDTO;
import com.spring.mybatis.main.dto.PagingDTO;


@Controller
public class FileController {
	@Autowired
	BoardFileService boardfile;
	
	@RequestMapping("/fileDownload/{file}")
    public void fileDownload(@PathVariable String file, HttpServletResponse response) throws IOException {
		boardfile.service(file, response);
    }
	
}
