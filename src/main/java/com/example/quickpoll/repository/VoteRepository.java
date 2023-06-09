package com.example.quickpoll.repository;

import com.example.quickpoll.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote,Long> {

    @Query(value = "select v.* from Option o, Vote v where o.POLL_ID and v.OPTION_ID = o.OPTION_ID",nativeQuery = true)
    Iterable<Vote> findByPollId(Long pollId);
}
