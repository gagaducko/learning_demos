package demo.gagaduck.influxdbdemo.entity;

public class LogEntry {

    private String message;
    private String level;
    private String timestamp;

    // 无参构造函数
    public LogEntry() {}

    // 带参构造函数
    public LogEntry(String message, String level, String timestamp) {
        this.message = message;
        this.level = level;
        this.timestamp = timestamp;
    }

    // Getter 和 Setter 方法
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // toString 方法，用于输出日志内容
    @Override
    public String toString() {
        return "LogEntry{" +
                "message='" + message + '\'' +
                ", level='" + level + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}


