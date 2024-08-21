import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterExample extends StdScalarSerializer<LocalDateTime>{
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss.SSS",
            new Locale("ru","RU"));
}
