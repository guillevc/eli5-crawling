package fic.riws.eli5riws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class RedditPost {
    private String category;
    private String question;
    private List<RedditResponse> responses;
    private Integer karma;
}