package com.spring.mybatis.board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.spring.mybatis.file.dao.BoardFileDAO;
import com.spring.mybatis.file.dto.BoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.board.dao.BoardDAO;
import com.spring.mybatis.main.dto.BoardListDTO;
import com.spring.mybatis.main.dto.BoardSearchDTO;
import com.spring.mybatis.main.dto.PagingDTO;
import org.springframework.web.multipart.MultipartFile;

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


	public String boardInsert(Board board, List<MultipartFile> files) {
		BoardFileDTO fileDTO = new BoardFileDTO();
		boardDao.insert(board);

		if(files!=null){
			for(int i =0;i<files.size(); i++ ) {
				fileDTO = fileSave(files.get(i), fileDTO);
				fileDTO.setB_id(board.getSeq());
				filedao.insert(fileDTO);
			}
		}
		updateRe_id();

		return "SUCCESS";
	}
	public String boardInsert_service2(Board board, List<MultipartFile> files) {
		BoardFileDTO fileDTO = new BoardFileDTO();
		boardDao.increaseReord(board);
		board.setRelev(board.getRelev()+1);
		board.setReord(board.getReord()+1);
		boardDao.insert(board);

		if(files!=null){
			for(int i =0;i<files.size(); i++ ) {
				fileDTO = fileSave(files.get(i), fileDTO);
				fileDTO.setB_id(board.getSeq());
				filedao.insert(fileDTO);
			}
		}
		return "SUCCESS";
	}

	public void updateRe_id() {
		Board board = boardDao.findByReid(999);
		if(board!=null)	boardDao.updateReid(board.getSeq());
	}

	public String update(Board board, List<MultipartFile> files, int[] deleteFile) {
		BoardFileDTO fileDTO = new BoardFileDTO();
		boardDao.update(board);

		if(deleteFile!=null) {
			for(int i=0;i<deleteFile.length;i++) {
				System.out.println(deleteFile[i]);
				filedao.deleteOne(deleteFile[i]);
			}
		}
		if(files!=null) {
			for(int i =0;i<files.size(); i++ ) {
				fileDTO = fileSave(files.get(i), fileDTO);
				fileDTO.setB_id(board.getSeq());
				filedao.insert(fileDTO);
			}
		}
		return "SUCCESS";
	}

	public BoardFileDTO fileSave(MultipartFile files, BoardFileDTO fileDTO) {
			String originalFile = files.getOriginalFilename();
			String fileExtension =	originalFile.substring(originalFile.lastIndexOf("."));
			//업무에서 사용하는 리눅스, UNIX는 한글지원이 안 되는 운영체제
			//파일업로드시 파일명은 ASCII코드로 저장되므로, 한글명으로 저장 필요
			//UUID클래스 - (특수문자를 포함한)문자를 랜덤으로 생성                    "-"라면 생략으로 대체
			String storedFileName = UUID.randomUUID().toString().replaceAll("-",  "") + fileExtension;
			String filePath = "C:\\Users\\TIGEN_221123\\eclipse-workspace\\TestMybatis\\webContent\\WEB-INF\\resources\\";

			File fileObj = new File(filePath + storedFileName);
			System.out.println(fileObj);
			try {
				files.transferTo(fileObj);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileDTO.setBf_fileName(originalFile);
			fileDTO.setBf_uploadFileName(storedFileName);
			fileDTO.setBf_filePath(filePath);
			return fileDTO;
		}

	public void delete(int id) {
		File file;

		boardDao.delete(id);
		List<BoardFileDTO> fileDTO = filedao.fileList(id);

		filedao.deleteAll(id);
		String filePath = "C:\\Users\\TIGEN_221123\\eclipse-workspace\\TestMybatis\\webContent\\WEB-INF\\resources\\";
		for(int i=0;i<fileDTO.size();i++) {
			file = new File(filePath + fileDTO.get(i).getBf_uploadFileName());
			file.delete();
		}
	}

}