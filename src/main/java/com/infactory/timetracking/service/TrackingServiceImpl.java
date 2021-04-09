package com.infactory.timetracking.service;

import com.infactory.timetracking.model.Entry;
import com.infactory.timetracking.model.Summary;
import com.infactory.timetracking.repo.EntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    EntryRepo entryRepo;

    @Override
    public void checkIn() throws AlreadyCheckInException {
        System.out.println("Checking in...");
        if (isCheckingIn()) {
            throw new AlreadyCheckInException();
        }
        var entry = new Entry();
        entry.setCheckIn(LocalDateTime.now());
        entryRepo.save(entry);
    }

    @Override
    public void checkOut() throws NoCheckInFoundException {
        System.out.println("Checking out...");
        if (!isCheckingIn()) {
            throw new NoCheckInFoundException();
        }
        var latestEntry = getLatestEntry().get();
        latestEntry.setCheckOut(LocalDateTime.now());
        entryRepo.save(latestEntry);
    }

    @Override
    public Summary getSummary() {
        var entries = entryRepo.findAll();
        return new Summary(entries);
    }

    private boolean isCheckingIn() {
        return getLatestEntry().filter(entry -> entry.getCheckOut() == null).isPresent();
    }

    private Optional<Entry> getLatestEntry() {
        var entries = entryRepo.findAll(Sort.by("checkIn").descending());
        return entries.stream().findFirst();
    }
}
