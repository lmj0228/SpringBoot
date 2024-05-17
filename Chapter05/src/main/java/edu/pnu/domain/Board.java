package edu.pnu.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Board 엔티티 클래스입니다.
@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq; // 게시물 번호
	private String title; 
	private String writer;
	private String content;
	@Builder.Default
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate = new Date(); // 생성일시 (기본값: 현재 날짜 및 시간)
	@Builder.Default
	private Long cnt = 0L; // 조회수 (기본값: 0)
	
	public void update(Board board) {
		if (board.getTitle() != null) title = board.getTitle();
		if (board.getWriter() != null) writer = board.getWriter();
		if (board.getContent() != null) content = board.getContent();
	}
}
