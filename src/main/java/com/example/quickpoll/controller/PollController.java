package com.example.quickpoll.controller;

import com.example.quickpoll.domain.Poll;
import com.example.quickpoll.repository.PollRepository;
import jakarta.inject.Inject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
public class PollController {
    @Inject
    private PollRepository pollRepository;

    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {

        poll = pollRepository.save(poll);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) throws Exception {

        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty())
            throw new Exception("Poll not found!");

        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
    }

    @PutMapping("/polls/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody Poll newPoll) throws Exception {

        Optional<Poll> poll = pollRepository.findById(id);

        if (poll.isEmpty())
            throw new Exception("No poll presents at {" + id + "}");

        return new ResponseEntity<>(newPoll, HttpStatus.OK);
    }

    @DeleteMapping("/polls/{id}")
    public ResponseEntity<Poll> deletePoll(@PathVariable Long id) {

        pollRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}