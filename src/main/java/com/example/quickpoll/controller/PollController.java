package com.example.quickpoll.controller;

import com.example.quickpoll.repository.PollRepository;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PollController {
    @Inject
    private PollRepository pollRepository;
}