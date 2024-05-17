package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class TestController {

	@Autowired
	private BoardRepository boardRepo;
	
	// 모든 게시물 목록을 반환하는 메서드입니다.
	@GetMapping("/boards")
	public List<Board> getAllBoards() {
		return boardRepo.findAll();
	}
	
	// 특정 번호의 게시물을 반환하는 메서드입니다.
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	// 게시물 등록
	@PostMapping("/board")
	public Board postBoard(Board board) {
		return boardRepo.save(board);
	}
	
	// 게시물 수정
	@PutMapping("/board")
	public Board putBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.update(board);
		return boardRepo.save(b);
	}
	
	// 특정 번호의 게시물을 삭제하는 메서드입니다.
	@DeleteMapping("/board/{seq}")	
	public Board deleteBoard(@PathVariable Long seq) {
		Board b = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return b;
	}
	
}
