package fic.riws.eli5riws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RedditResponse {
    private String text;
    private Integer karma;
}
