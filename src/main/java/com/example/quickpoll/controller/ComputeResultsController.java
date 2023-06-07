package com.example.quickpoll.controller;

import com.example.quickpoll.repository.VoteRepository;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeResultsController {
    @Inject
    VoteRepository voteRepository;
}
