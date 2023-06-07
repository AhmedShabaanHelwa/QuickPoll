package com.example.quickpoll.controller;

import com.example.quickpoll.controller.dto.OptionCount;
import com.example.quickpoll.controller.dto.VoteResult;
import com.example.quickpoll.domain.Vote;
import com.example.quickpoll.repository.VoteRepository;
import jakarta.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultsController {
    @Inject
    VoteRepository voteRepository;

    @GetMapping("/compute-result")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        Iterable<Vote> votesOfPoll = voteRepository.findByPollId(pollId);

        VoteResult voteResult = computeVoteResults(votesOfPoll);
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);

    }

    private VoteResult computeVoteResults(Iterable<Vote> votesOfPoll) {
        long totalVotes = 0;
        Map<Long, OptionCount> optionCountMap = new HashMap<>();

        for (Vote vote : votesOfPoll) {
            totalVotes++;
            OptionCount optionCount = optionCountMap.get(vote.getOption().getId());
            if (optionCount == null)
            {
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                optionCountMap.put(vote.getOption().getId(),optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }

        VoteResult voteResutl = new VoteResult();
        voteResutl.setTotalVotes(totalVotes);
        voteResutl.setResults(optionCountMap.values());
        return voteResutl;
    }
}
