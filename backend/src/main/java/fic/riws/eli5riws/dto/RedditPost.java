package fic.riws.eli5riws.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RedditPost {
    private String category;
    private String question;
    private List<String> responses;
}