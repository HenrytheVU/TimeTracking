package com.infactory.timetracking.repo;

import com.infactory.timetracking.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepo extends JpaRepository<Entry, Long> {

}
