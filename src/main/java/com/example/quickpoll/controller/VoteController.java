package com.example.quickpoll.controller;

import com.example.quickpoll.domain.Vote;
import com.example.quickpoll.repository.VoteRepository;
import jakarta.inject.Inject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VoteController {
    @Inject
    VoteRepository voteRepository;

    @GetMapping("/polls/{pollId}/votes")
    public ResponseEntity<Iterable<Vote>> getVotesOfPoll(@PathVariable Long pollId) {

        Iterable<Vote> votes = voteRepository.findByPollId(pollId);

        return new ResponseEntity<>(votes, HttpStatus.OK);
    }

    @PostMapping("/polls/{pollId}/votes")
    public ResponseEntity<Vote> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {

        Vote createdVote = voteRepository.save(vote);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVote.getId())
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(vote, httpHeaders, HttpStatus.CREATED);
    }
}
