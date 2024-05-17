package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {

	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Random rd = new Random();
		// 회원1이 작성한 게시물 초기화
		for (int i = 1; i < 5 ; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					.writer("member1")
					.content("content1" + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
		
		// 회원2가 작성한 게시물 초기화
		for (int i = 1; i < 5 ; i++) {
			boardRepo.save(Board.builder()
					.title("title2" + i)
					.writer("member2")
					.content("content2" + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
		
	}
}
