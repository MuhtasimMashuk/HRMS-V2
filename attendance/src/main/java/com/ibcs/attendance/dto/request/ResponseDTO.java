package com.ibcs.attendance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    public enum ResponseStatus {
        OK, ERROR, CREATED, UPDATED, DELETED, SUCCESS
    }

    private ResponseStatus status;

    private String message;

    private T payload;
}
