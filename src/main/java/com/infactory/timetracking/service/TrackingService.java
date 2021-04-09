package com.infactory.timetracking.service;

import com.infactory.timetracking.model.Summary;

public interface TrackingService {

    void checkIn() throws AlreadyCheckInException;

    void checkOut() throws NoCheckInFoundException;

    Summary getSummary();

}
