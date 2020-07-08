package com.yingjianren.yingjianren.model;

public class DeleteObject {
    private Long id;
    private int deletedLine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeletedLine() {
        return deletedLine;
    }

    public void setDeletedLine(int deletedLine) {
        this.deletedLine = deletedLine;
    }

    public DeleteObject(Long id, int deletedLine) {
        this.id = id;
        this.deletedLine = deletedLine;
    }
}