package task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserTaskAction {

    private Date actionTime;

    public UserTaskAction(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getFormatActionTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(actionTime);
    }
}