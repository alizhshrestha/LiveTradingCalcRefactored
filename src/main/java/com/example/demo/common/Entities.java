/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.common;

import java.util.List;
import java.util.Map;

/**
 *
 * @author alizh
 */
public class Entities {
    String tablename;
    List<Map<String, String>> rows;

    public Entities(String tablename, List<Map<String, String>> rows) {
        this.tablename = tablename;
        this.rows = rows;
    }

    public Entities() {
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }
    
    
    @Override
    public String toString() {
        return "Entities{" + "tablename=" + tablename + ", rows=" + rows + '}';
    }
    
}
