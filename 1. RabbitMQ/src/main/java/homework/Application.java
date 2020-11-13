package homework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    private final String id;
    private final String title;
    private final String date;
    private SimpleDateFormat simpleDateFormat;

    public Application(String id, String title){
        this.id = id;
        this.title = title;
        this.date = new SimpleDateFormat("dd MMMM").format(new Date());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
