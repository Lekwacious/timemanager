package com.time.timemanager.payload.responsePayLoad;

import com.time.timemanager.model.Activity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ActivityListResponse extends BaseResponsePayload {
    private List<Activity> response;
    public ActivityListResponse (Boolean isSuccessful, String message, Integer responseCode, LocalDate timeStamp,
                                 List<Activity> response){
        super(isSuccessful, message, responseCode, timeStamp);
        this.response = response;

    }
}


