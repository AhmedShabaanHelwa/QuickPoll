package com.example.quickpoll.repository;

import com.example.quickpoll.domain.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote,Long> {
}
