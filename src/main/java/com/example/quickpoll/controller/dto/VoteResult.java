package com.example.quickpoll.controller.dto;

import java.util.Collection;

public class VoteResult {
    private Long totalVotes;
    private Collection<OptionCount> results;

    public Long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Collection<OptionCount> getResults() {
        return results;
    }

    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }
}
