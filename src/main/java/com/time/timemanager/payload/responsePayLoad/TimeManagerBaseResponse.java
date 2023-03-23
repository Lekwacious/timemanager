package com.time.timemanager.payload.responsePayLoad;

import com.time.timemanager.model.TimeManagerUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeManagerBaseResponse extends BaseResponsePayload{
        private TimeManagerUser response;
    public TimeManagerBaseResponse (Boolean isSuccessful, String message, Integer responseCode, LocalDate timeStamp,
                TimeManagerUser response){
            super(isSuccessful, message, responseCode, timeStamp);
            this.response = response;

        }
}
