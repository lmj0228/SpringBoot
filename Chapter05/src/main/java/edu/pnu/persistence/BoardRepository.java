package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

//JpaRepository를 상속받는 BoardRepository 인터페이스입니다.
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	// 제목에 해당 검색어를 포함하는 게시물 리스트를 반환합니다.
	List<Board> findByTitle(String searchKeyword);
	
	// 내용에 해당 검색어를 포함하는 게시물 리스트를 반환합니다.
	List<Board> findByContentContaining(String searchKeyword);
	
	// 제목 또는 내용에 해당 검색어를 포함하는 게시물 리스트를 반환합니다.
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	// title에 "1"이 포함된 Board 객체를 찾는 쿼리 메소드
    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword%")
    List<Board> findByTitleContaining(@Param("keyword") String keyword);
    
    // title에 "1"이 포함되고 cnt가 50보다 큰 Board 객체를 찾는 쿼리 메소드
    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% AND b.cnt > :cnt")
    List<Board> findByTitleContainingAndCntGreaterThan(@Param("keyword") String keyword, @Param("cnt") Long cnt);

    Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
    
    @Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
    List<Board> queryAnnotationTest1(String searchKeyword);
    
    @Query("SELECT b.seq, b.title, b.writer, b.createDate "
    		+ "FROM Board b "
    		+ "WHERE b.title like %?1% "
    		+ "ORDER BY b.seq DESC")
    List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
    
    @Query(value="select seq, title, writer, createdate "
    		+ "from board where title like '%||?1||%' "
    		+ "order by seq desc", nativeQuery=true)
    List<Object[]> queryAnnotationTest3(String searchKeyword);
    
}
