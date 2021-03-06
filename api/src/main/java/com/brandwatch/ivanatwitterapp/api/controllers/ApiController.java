package com.brandwatch.ivanatwitterapp.api.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.ivanatwitterapp.api.services.MentionService;
import com.brandwatch.ivanatwitterapp.api.services.QueryService;
import com.brandwatch.ivanatwitterapp.api.utils.DateParser;
import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;

@RestController
public class ApiController {

    @Autowired
    private MentionService mentionService;

    @Autowired
    private QueryService queryService;

    @GetMapping("/mentions")
    public List<Mention> getMentions(@RequestParam int limit,
                                     @RequestParam String startDate,
                                     @RequestParam String endDate) {
        LocalDateTime startDateISO = DateParser.parseDateTime(startDate);
        LocalDateTime endDateISO = DateParser.parseDateTime(endDate);
        return mentionService.getMentions(limit, startDateISO, endDateISO);
    }

    @PostMapping("/queries")
    public TwitterQuery saveQuery(@RequestParam String hashtag) {
        return queryService.createQuery(hashtag);
    }

    @GetMapping(value = "/mentions/{id}")
    public List<Mention> getMentionsByQueryId(@PathVariable long id) {
        return mentionService.getMentionsByQueryId(id);
    }

    @DeleteMapping("/queries/{id}")
    public boolean deleteQuery(@PathVariable long id) {
        return queryService.deleteQueryById(id);
    }

    @PatchMapping("/queries/{id}")
    public TwitterQuery updateQuery(@PathVariable long id, @RequestParam String hashtag) {
        return queryService.updateQueryForHashTag(id, hashtag);
    }
}
