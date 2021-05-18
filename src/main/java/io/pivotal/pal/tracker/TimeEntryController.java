package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController
{
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository)
    {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate)
    {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId)
    {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);

        if (timeEntry == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(timeEntry);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate)
    {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);

        if (timeEntry == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(timeEntry);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId)
    {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list()
    {
        return ResponseEntity.ok(timeEntryRepository.list());
    }
}
