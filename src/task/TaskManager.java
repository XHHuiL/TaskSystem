package task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private ArrayList<UserTask> taskPool;

    private volatile static TaskManager taskManager;

    private TaskManager() {
        taskPool = new ArrayList<>();
    }

    public static TaskManager getInstance() {
        if (taskManager == null) {
            synchronized (TaskManager.class) {
                if (taskManager == null) {
                    taskManager = new TaskManager();
                }
            }
        }
        return taskManager;
    }

    public int getTaskPoolSize() {
        return taskPool.size();
    }

    public void releaseTask(UserTask task) {
        taskPool.add(task);
    }

    public void removeTask(UserTask task){
        taskPool.remove(task);
    }

    public boolean performTask(UserTask task) {
        return task.perform();
    }

    public Map getTaskInfo() {
        Map<String, UserTaskStatus> taskInfo = new HashMap<>();
        for (UserTask task : taskPool
        ) {
            taskInfo.put(task.getName(), task.getStatus());
        }
        return taskInfo;
    }



}