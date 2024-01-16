package com.spring.mybatis.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mybatis.board.Board;
import com.spring.mybatis.main.dto.BoardSearchDTO;
import com.spring.mybatis.main.dto.PagingDTO;

@Repository
public class BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	public List<Board> list(Map<String, Object> map){
		return sqlSession.selectList("board.list", map);
	}
	public List<Board> searchlist(Map<String, Object> map){
		return sqlSession.selectList("board.searchlist", map);
	}
	public void insert(Board board) {
		sqlSession.insert("board.write", board);				
	}
	public List<Board> search(PagingDTO pagingDTO) {		
		return sqlSession.selectList("board.search", pagingDTO);
	}
	public Board boardDetail(Board board) {
		return sqlSession.selectOne("board.boardDetail", board);
	}
	public void update(Board bd) {
		sqlSession.selectOne("board.update", bd);
	}
	public void delete(int id) {
		sqlSession.insert("board.delete", id);
	}

	public int totalPageCount() {
		return sqlSession.selectOne("board.count");
	}

	public int totalSearchPageCount(BoardSearchDTO searchDTO) {
		return sqlSession.selectOne("board.searchCount", searchDTO);
	}
	public void updateReid(int id) {
		sqlSession.selectOne("board.updateReid", id);
		
	}
	public Board findByReid(int reid) {
		return sqlSession.selectOne("board.viewReid");
	}
	public void increaseReord(Board body) {
		sqlSession.update("board.autopdate", body);
	}
}
