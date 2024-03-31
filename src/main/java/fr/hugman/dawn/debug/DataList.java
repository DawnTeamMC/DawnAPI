package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;

import java.util.List;

public class DataList<T> {
    @Expose
    protected List<T> entries;

    public DataList(List<T> entries) {
        this.entries = entries;
    }
}
