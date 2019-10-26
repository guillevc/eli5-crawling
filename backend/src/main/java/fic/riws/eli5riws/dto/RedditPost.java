package fic.riws.eli5riws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.model.Question;

@Data 
@AllArgsConstructor
public class RedditPost {
    private Question question;
    private List<Answer> responses;
}