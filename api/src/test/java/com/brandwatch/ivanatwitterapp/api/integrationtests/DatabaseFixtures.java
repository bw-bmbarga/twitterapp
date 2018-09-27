package com.brandwatch.ivanatwitterapp.api.integrationtests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.brandwatch.ivanatwitterapp.common.models.Mention;
import com.brandwatch.ivanatwitterapp.common.models.MentionID;
import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.repositories.MentionRepository;
import com.brandwatch.ivanatwitterapp.common.repositories.QueryRepository;

public class DatabaseFixtures {

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private QueryRepository queryRepository;

    private void populateTestDatabase() throws ParseException {
        TwitterQuery twitterQuery1 = new TwitterQuery(1, "brandwatch");
        TwitterQuery twitterQuery2 = new TwitterQuery(2, "spotify");
        queryRepository.saveQuery(twitterQuery1);
        queryRepository.saveQuery(twitterQuery2);
        DateFormat isoFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Mention mention1 = new Mention(new MentionID(37294979,
                twitterQuery1.getId()),
                "Some random tweeted text",
                "Dummy user",
                "web client",
                isoFormat.parse("2018-09-23"));
        Mention mention2 = new Mention(new MentionID(37294978,
                twitterQuery1.getId()),
                "Some random tweeted text",
                "Dummy user",
                "web client",
                isoFormat.parse("2018-09-22"));
        mentionRepository.saveMention(mention1);
        mentionRepository.saveMention(mention2);
    }
}
