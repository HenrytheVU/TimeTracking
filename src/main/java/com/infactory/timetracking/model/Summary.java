package com.infactory.timetracking.model;

import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
public class Summary {

    final private List<Entry> entries;
    private Duration total;

    public Summary(List<Entry> entries) {
        this.entries = entries;
        this.total = calculateTotal();
    }

    private Duration calculateTotal() {
        return entries.stream().map(Entry::getDuration).reduce(Duration.ZERO, Duration::plus);
    }
}
