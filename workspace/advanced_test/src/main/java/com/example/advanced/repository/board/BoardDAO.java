package com.example.advanced.repository.board;

import com.example.advanced.entity.board.Board;
import com.example.advanced.entity.board.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    게시글 추가
    public Board save(Board board) {
        entityManager.persist(board);
        return board;
    }

    //    댓글 추가
    public Reply save(Reply reply) {
        entityManager.persist(reply);
        return reply;
    }

    //    게시글 조회
    public Optional<Board> findById(Long id) {
//        return Optional.ofNullable(entityManager.find(Board.class, id));

//        1:N 관계에서 한 방 쿼리를 작성하면 JOIN은 되지만,
//        여러 개가 조회되는 N의 특성상 SELECT 쿼리가 한 번 더 실행된다.
//        따라서 1:N 관계일 경우에는 JOIN을 할 필요가 없다.
//        String query = "select b from Board b inner join b.replies where b.id = :id";

//        관계의 종류에 상관없이 참조 객체에 모든 정보를 한 번에 가져올 때에는
//        FETCH JOIN을 사용하면 된다.
        String query = "select b from Board b join fetch b.replies where b.id = :id";
        return Optional.ofNullable(
                entityManager
                        .createQuery(query, Board.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    //    댓글 조회
    public Optional<Reply> findReplyById(Long id) {
        return Optional.ofNullable(entityManager.find(Reply.class, id));
    }

    //    전체 조회
    public List<Board> findAll() {
        String query = "select b from Board b";
        return entityManager.createQuery(query, Board.class).getResultList();
    }

    //    삭제
    public void delete(Board board) {
        entityManager.remove(board);
    }

}
