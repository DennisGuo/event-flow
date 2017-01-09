package cn.geobeans.flow;

import java.util.LinkedList;

/**
 * 具体的流程
 * Created by ghx on 2017/1/9.
 */
public class Flow {

    private int id;
    private String name;
    LinkedList<Progress> progress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Progress> getProgress() {
        return progress;
    }

    public void setProgress(LinkedList<Progress> progress) {
        this.progress = progress;
    }
}
