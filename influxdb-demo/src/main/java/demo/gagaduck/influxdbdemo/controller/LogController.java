package demo.gagaduck.influxdbdemo.controller;

import demo.gagaduck.influxdbdemo.entity.LogEntry;
import demo.gagaduck.influxdbdemo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService logService;

    // 查询所有日志
    @GetMapping("/get")
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        List<LogEntry> logs = logService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    // 根据时间戳删除日志
    @DeleteMapping("/timestamp")
    public ResponseEntity<String> deleteLogByTimestamp(@RequestParam("timestamp") String timestamp) {
        try {
            Instant instant = Instant.parse(timestamp);
            logService.deleteLogByTimestamp(instant);
            return ResponseEntity.ok("Log with timestamp " + timestamp + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid timestamp format or error occurred: " + e.getMessage());
        }
    }

    // 修改日志
    @PutMapping("/timestamp")
    public ResponseEntity<String> updateLog(@RequestParam("timestamp") String timestamp,
                                            @RequestParam("newMessage") String newMessage,
                                            @RequestParam("newLevel") String newLevel) {
        try {
            Instant instant = Instant.parse(timestamp);
            logService.updateLog(instant, newMessage, newLevel);
            return ResponseEntity.ok("Log with timestamp " + timestamp + " updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid timestamp format or error occurred: " + e.getMessage());
        }
    }

    // 创建新的日志
    @PostMapping
    public ResponseEntity<String> createLog(@RequestParam("message") String message,
                                            @RequestParam("level") String level) {
        logService.addLog(message, level);
        return ResponseEntity.ok("Log created with message: " + message);
    }
}

