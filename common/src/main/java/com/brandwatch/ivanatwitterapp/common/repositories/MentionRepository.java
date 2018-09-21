package com.brandwatch.ivanatwitterapp.common.repositories;

import com.brandwatch.ivanatwitterapp.common.models.TwitterQuery;
import com.brandwatch.ivanatwitterapp.common.models.Mention;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface MentionRepository {

    void saveMention(Mention mention);

    List<Mention> readMentions(int limit, LocalDateTime startDate, LocalDateTime endDate);

    List<Mention> findMentionsByQueryId(long queryId);
}
