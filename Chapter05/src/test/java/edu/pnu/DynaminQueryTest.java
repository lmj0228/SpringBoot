package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynaminQueryTest {
	
	@Autowired 
	private DynamicBoardRepository boardRepo; // BoardRepository 주입
	
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
	public void testDynamicQuery() {
		String searchCondition = "Title";
		String searchKeyword = "테스트 제목10";
		BooleanBuilder builder  = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		if (searchCondition.equals("Title")) {
			builder.and(qboard.title.contains(searchKeyword));
		} else if (searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+ searchKeyword + "%"));
		}
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList = boardRepo.findAll(builder, paging);
		System.out.println("검색 결과");
		for (Board board : boardList)
			System.out.println("--->" + board);
	} 
	
}
