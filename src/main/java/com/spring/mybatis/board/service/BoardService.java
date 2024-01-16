package com.spring.mybatis.board.service;

import java.util.HashMap;
import java.util.Map;

import com.spring.mybatis.file.dao.BoardFileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.dao.BoardDAO;
import com.spring.mybatis.main.dto.BoardListDTO;
import com.spring.mybatis.main.dto.BoardSearchDTO;
import com.spring.mybatis.main.dto.PagingDTO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDao;

	@Autowired
	private BoardFileDAO filedao;

	public BoardListDTO boardList(BoardSearchDTO searchDTO, PagingDTO pagingDTO) {
		BoardListDTO listDTO = new BoardListDTO(); 	// 반환

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchDTO", searchDTO);
		map.put("pagingDTO", pagingDTO);
		
		if(searchDTO.getSearchOption() != null && !searchDTO.getSearchOption().equals("")
				&& searchDTO.getSearchText() != null && !searchDTO.getSearchText().equals("")) {
			listDTO.setBoardList(boardDao.searchlist(map));
			pagingDTO.setTotalCount(boardDao.totalSearchPageCount(searchDTO));
		}
		else{
			listDTO.setBoardList(boardDao.list(map));
			pagingDTO.setTotalCount(boardDao.totalPageCount());
		}
		
		pagingDTO.setPagingHtml(setHtml(pagingDTO));		// jwh 수정 가능
		
		listDTO.setPagingDTO(pagingDTO);
		listDTO.setSearchDTO(searchDTO);
		
		return listDTO;
	}

	public Board boardDetail(Board board) {
		return boardDao.boardDetail(board);
	}
	public PagingDTO pageNumSet(PagingDTO pagingDTO){
		pagingDTO.setCurrentPage((pagingDTO.getCurrentPage()-1)*pagingDTO.getBlockCount());
		return pagingDTO;
	}
	public void fillSearch(BoardSearchDTO searchDTO) {
		searchDTO.setSearchOption(searchDTO.getSearchOption() == null ? "" : searchDTO.getSearchOption());
		searchDTO.setSearchText(searchDTO.getSearchText() == null ? "" : searchDTO.getSearchText());		
	}
	public String setHtml(PagingDTO pagingDTO) {
		StringBuffer html;
		int currentPage = pagingDTO.getCurrentPage();
		int totalCount = pagingDTO.getTotalCount();
		int blockCount = pagingDTO.getBlockCount();
	
		int pageCount = 10;
		
		int totalPage = (int) Math.ceil((double) totalCount / blockCount);
		
		if(totalPage == 0)	totalPage = 1;
		if(currentPage > totalPage) currentPage = totalPage;
		
		int startPage = (int) ((currentPage - 1) / pageCount) * pageCount  + 1;
		int endPage = startPage + pageCount - 1;
	
		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		html = new StringBuffer();
		html.append("<div class =\"cent\">");
		
		if (currentPage > pageCount) {
			html.append("<a href=\"javascript:goList(1)\"><<</a> ");
			html.append("<a href=\"javascript:goList(" + (startPage - 1) + ")\"><</a> ");
		}
	
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage)	break;
			
			if (i == currentPage) {
				html.append("<a href=\"javascript:goList(" + i + ")\">" + i + "</a> ");
	
			} else {
				html.append("<a href=\"javascript:goList(" + i + ")\">" + i + "</a> ");
			}
		}
	
		// 다음 block 페이지
		if (totalPage - startPage >= pageCount) {
			html.append("<a href=javascript:goList(" + (endPage + 1) + ")>></a> ");
			html.append("<a href=javascript:goList(" + totalPage + ")>>></a> ");
		}
		
		html.append("</div>");
	
		return html.toString();
	}

	/*
	 * public List<Board> searchService(PagingDTO pagingDTO){ return
	 * boardDao.search(pagingDTO); }
	 */
	/*
	 * public long totalPageCnt(){ return boardDao.totalPageCount()/10 +1; }
	 */

	public Board writeReply(Board board) {
		return boardDao.boardDetail(board);
	}
	
}