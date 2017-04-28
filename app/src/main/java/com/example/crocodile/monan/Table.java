package com.example.crocodile.monan;

/**
 * Created by ThienKhiem on 4/27/2017.
 */

public class Table {
    Integer TableID;
    String TableName;
    Integer ChairNumber;
    Integer isUsed;

    public Table(Integer tableID, String tableName, Integer chairNumber, Integer isUsed) {
        TableID = tableID;
        TableName = tableName;
        ChairNumber = chairNumber;
        this.isUsed = isUsed;
    }

    public Integer getTableID() {
        return TableID;
    }

    public void setTableID(Integer tableID) {
        TableID = tableID;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public Integer getChairNumber() {
        return ChairNumber;
    }

    public void setChairNumber(Integer chairNumber) {
        ChairNumber = chairNumber;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}