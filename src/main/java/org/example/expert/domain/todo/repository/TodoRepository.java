package org.example.expert.domain.todo.repository;

import jakarta.persistence.Entity;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    //Lv2.Fetch Join 문제

    //Code Before
    //@Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    @EntityGraph(attributePaths = {"managers"})
    @Query("SELECT t FROM Todo t ORDER BY t.modifiedAt")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    //Code Before
//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN FETCH t.user " +
//            "WHERE t.id = :todoId")
    @EntityGraph(attributePaths = {"managers"})
    @Query("SELECT t FROM Todo t where t.user = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
