package com.time.timemanager.security.payload.reponsePayload;

import lombok.Data;

@Data
public class ChoiceResponse {
    private long id;
    private String text;
    private long voteCount;

}
