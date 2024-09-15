package demo.gagaduck.influxdbdemo.service;

import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;
import com.influxdb.query.FluxRecord;
import demo.gagaduck.influxdbdemo.entity.LogEntry;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@Service
public class LogService {

    private final InfluxDBClient influxDBClient;
    private final String bucket;
    private final String org;

    public LogService() {
        // 创建 InfluxDB 客户端，使用 token 来进行认证
        String token = "g3T2rEUpiXqOQkFyJN37XH4wM_4lKidRkBl1zJjX6EpxmJpeGNP2aJghE3j0KQGCPqGuGtWpNiUTgGY1tF9RzA=="; // 替换为实际的 token
        this.influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
        this.bucket = "gagaduck"; // 替换为实际的 bucket 名称
        this.org = "gagaduck"; // 替换为实际的组织名称
    }

    // 增加日志记录
    public void addLog(String logMessage, String level) {
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        Point point = Point
                .measurement("logs")
                .addTag("level", level)
                .addField("logMessage", logMessage)
                .time(Instant.now(), WritePrecision.NS);
        writeApi.writePoint(bucket, org, point);
    }

    // 查询所有日志
    public List<LogEntry> getAllLogs() {
        List<LogEntry> logEntries = new ArrayList<>();
        // InfluxDB 查询语句，查询1h内的日志数据
        // 构建 Flux 查询，加入对 _measurement 和 _field 的过滤
        String fluxQuery = "from(bucket: \"" + bucket + "\")" +
                " |> range(start: -1h)" +  // 查询过去 1 小时的数据
                " |> filter(fn: (r) => r[\"_measurement\"] == \"logs\")" +  // 过滤 measurement 为 logs
                " |> filter(fn: (r) => r[\"_field\"] == \"logMessage\")";  // 过滤 field 为 logMessage// 查询过去1小时的日志，可根据需求调整
        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(fluxQuery, org);
        // 处理查询返回的数据
        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                LogEntry logEntry = new LogEntry();
                String message = (String) record.getValueByKey("_value");  // 从InfluxDB获取日志消息
                String level = (String) record.getValueByKey("level");         // 获取日志级别
                String timestamp = record.getTime() != null ? record.getTime().toString() : null; // 获取时间戳
                // 如果message和level不为空，则将其添加到logEntry
                if (message != null && level != null) {
                    logEntry.setMessage(message);
                    logEntry.setLevel(level);
                    logEntry.setTimestamp(timestamp);
                    logEntries.add(logEntry);
                }
            }
        }
        return logEntries;
    }


    // 根据时间戳删除日志
    public void deleteLogByTimestamp(Instant timestamp) {
        String deleteQuery = "from(bucket: \"" + bucket + "\") |> range(start: -1h) |> filter(fn: (r) => r._time == " + timestamp.toString();
        QueryApi queryApi = influxDBClient.getQueryApi();
        queryApi.query(deleteQuery, org);
        // InfluxDB 2.0 不支持直接删除功能，通常需要重新构建 bucket 来清理数据，或使用其他方案。
    }

    // 修改日志 (通过时间戳找到对应的日志进行更新)
    public void updateLog(Instant timestamp, String newMessage, String newLevel) {
        // InfluxDB 不支持直接更新，只能写入新的数据，因此你需要找到对应的日志，删除它，并插入新的记录。
        deleteLogByTimestamp(timestamp); // 先删除旧的记录
        addLog(newMessage, newLevel); // 然后添加新的记录
    }
}
