package com.spring.mybatis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.service.BoardDeleteService;
import com.spring.mybatis.board.service.BoardInsertService;
import com.spring.mybatis.board.service.BoardService;
import com.spring.mybatis.board.service.BoardUpdateService;
import com.spring.mybatis.main.dto.BoardListDTO;
import com.spring.mybatis.main.dto.BoardSearchDTO;
import com.spring.mybatis.main.dto.PagingDTO;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BoardService boardService;

	@Autowired
	BoardInsertService boardinsert;

	@Autowired
	BoardUpdateService boardupdate;

	@Autowired
	BoardDeleteService boarddelete;

	@RequestMapping("/main")
	public String main(BoardSearchDTO searchDTO, PagingDTO pagingDTO, Model model) {
		System.out.println("\n----------------");
		logger.info("BoardController >> main >> start!");

		BoardListDTO listDTO = boardService.boardList(searchDTO, pagingDTO);

		model.addAttribute("listDTO", listDTO);

		return "main";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request) {
		// request.getSession().getAttribute("userId");

		return "write";
	}

	@ResponseBody
	@RequestMapping(value = "/writeAct", produces = "text/html;charset=UTF-8")
	public String writeAct(Board board, @RequestParam(name = "files", required = false) List<MultipartFile> files) {
		return boardinsert.service(board, files);
	}

	/*
	 * @RequestMapping("/writeTest") public String writeTest() { return "writeTest";
	 * }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/writeActTest2", produces =
	 * "text/html;charset=UTF-8") public String writeActTest2(Board board,
	 * 
	 * @RequestParam(name = "files", required = false) List<MultipartFile> files) {
	 * System.out.println(board); System.out.println(files);
	 * boardinsert.service(board, files);
	 * 
	 * return "SUCCESS"; }
	 */

	@RequestMapping("/write_reply")
	public String write_reply(Model model, Board board) {
		model.addAttribute("list", boardService.writeReply(board));
		return "write_reply";
	}
	
	@ResponseBody
	@RequestMapping(value = "/write_replyAct", produces = "text/html;charset=UTF-8")
	public String write_replyAct(Board board,
			@RequestParam(name = "files", required = false) List<MultipartFile> files) {
		
		return boardinsert.service2(board, files);
	}

	@RequestMapping("/selectone")
	public String selectone(Model model, Board board) {
		model.addAttribute("listDTO", boardService.boardDetail(board));
		return "selectone";
	}

	@GetMapping("/update")
	public String update(Model model, Board board) {
		model.addAttribute("listDTO", boardService.boardDetail(board));
		return "update";
	}

	@ResponseBody
	@RequestMapping(value = "/updateAct", produces = "text/html;charset=UTF-8")
	public String Act(Board board, @RequestParam(name = "files", required = false) List<MultipartFile> files,
			@RequestParam(name = "deleteFile", required = false) int[] deleteFile) {

		return boardupdate.service(board, files, deleteFile);
	}

	@GetMapping("/delete")
	public RedirectView delete(int id) {
		boarddelete.service(id);
		return new RedirectView("/main");
	}
	
	
	//--------TEST------------------------------------
	
	@RequestMapping("/xJoin")		//selectOne example 
	public String xJoin(Model model, Board board) {
		model.addAttribute("viewDTO", boardService.boardDetail(board));
		return "xJoin";
	}
	@ResponseBody
	@RequestMapping(value = "/ACT", produces = "text/html;charset=UTF-8")
	public String test2(Board board) {
		return "YOLO";
	}
	
	
}
