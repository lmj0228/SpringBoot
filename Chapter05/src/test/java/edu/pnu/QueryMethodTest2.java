package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest2 {
	
	@Autowired 
	private BoardRepository boardRepo; // BoardRepository 주입
	
	Random random = new Random(); // Random 인스턴스 생성
	
	@BeforeEach
	public void dataPrepare() {
		for (int i = 1 ; i <= 100; i++) {
			// 새로운 Board 객체 생성
			Board board = new Board();
			board.setTitle("테스트 제목" + i);
			board.setWriter("테스터" + i);
			board.setContent("테스트 내용" + i);
			board.setCreateDate(new Date());  // 생성 날짜를 현재 날짜로 설정
			board.setCnt((long) random.nextInt(101)); // 0부터 100까지의 랜덤 조회수 설정
			boardRepo.save(board); // Board 객체를 리포지토리에 저장
		}
	}
	
	@Test
	public void testFindByTitleContaining1() {
		// title에 "1"이 포함된 Board 객체를 찾음
		List<Board> boards = boardRepo.findByTitleContaining("1");
		
		// 찾은 Board 객체를 출력
		for (Board board : boards) {
			System.out.println(board);
		}
	}
	
	@Test
	public void testFindByTitleContainingAndCntGreaterThan() {
		// title에 "1"이 포함되고 cnt가 50보다 큰 Board 객체를 찾음
		List<Board> boards = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		
		// 찾은 Board 객체를 출력
        for (Board board : boards) {
            System.out.println(board);
        }
	}
	
}
