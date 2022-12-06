package com.example.tourismagency.Helpers;


import java.util.Map;

public class Query {

    private String sql;

    public Query() {
        this.sql = "";
    }

    public Query select(String table, String column){
        this.sql = "SELECT "+ column +" FROM "+ table;
        return this;
    }

    public Query update(String table, Map<String,String> args){
        StringBuilder set = new StringBuilder();
        String end = ", ";
        int i = 1;
        for (Map.Entry<String, String> arg : args.entrySet()) {
            if(i == args.size()){ end=""; }
            set.append(arg.getKey()).append(" = ").append("'").append(arg.getValue()).append("'").append(end);
            i++;
        }
        this.sql = "UPDATE "+ table +" SET "+ set.toString();
        return this;
    }

    public Query insert(String table, Map<String,String> args){
        StringBuilder ketString = new StringBuilder();
        StringBuilder valueString = new StringBuilder();
        String end = ", ";
        int i = 1;
        for (Map.Entry<String, String> arg : args.entrySet()) {
            if(i == args.size()){ end=""; }
            ketString.append(arg.getKey()).append(end);
            valueString.append(arg.getKey()).append(end);
            i++;
        }
        this.sql = "INSERT INTO "+ table +" ("+ ketString.toString() +") VALUES ("+ valueString.toString() +")";
        return this;
    }

    public Query insert(String table, String[] args){
        String keys = "";
        String values = "";
        String end = ", ";
        for (int i = 0; i < args.length; i++) {
            if(i+1 == args.length){ end=""; }
            keys += args[i]+end;
            values += "?"+end;
        }
        this.sql = "INSERT INTO "+ table +" ("+ keys +") VALUES ("+ values +")";
        return this;
    }

    public Query delete(String table){
        this.sql = "DELETE FROM "+ table;
        return this;
    }

    public Query where(String key, String operator, String value){
        if(this.sql.contains("WHERE")){
            this.sql += " AND "+key+" "+operator+" "+value;
        }else{
            this.sql += " WHERE "+key+" "+operator+" "+value;
        }
        return this;
    }

    public Query orWhere(String key, String operator, String value){
        if(this.sql.contains("WHERE")){
            this.sql += " OR "+key+" "+operator+" "+value;
        }else{
            this.sql += " WHERE "+key+" "+operator+" "+value;
        }
        return this;
    }

    public Query in(String column, String[] args){
        StringBuilder strIn = new StringBuilder();
        String end = ", ";
        for (int i = 0; i < args.length; i++) {
            if(i+1 == args.length){ end=""; }
            strIn.append(args[i]).append(end);
        }
        if(this.sql.contains("WHERE")){
            this.sql += " AND "+column+" IN "+strIn.toString();
        }else{
            this.sql += " WHERE "+column+" IN "+strIn.toString();
        }
        return this;
    }

    public Query orIn(String column, String[] args){
        StringBuilder strOrin = new StringBuilder();
        String end = ", ";
        for (int i = 0; i < args.length; i++) {
            if(i+1 == args.length){ end=""; }
            strOrin.append(args[i]).append(end);
        }
        if(this.sql.contains("WHERE")){
            this.sql += " OR "+column+" IN "+strOrin.toString();
        }else{
            this.sql += " WHERE "+column+" IN "+strOrin.toString();
        }
        return this;
    }

    public Query between(String column, String value1, String value2){
        if(this.sql.contains("WHERE")){
            this.sql += " AND "+column+" BETWEEN "+value1+" AND "+value2;
        }else{
            this.sql += " WHERE "+column+" BETWEEN "+value1+" AND "+value2;
        }
        return this;
    }

    public Query orBetween(String column, String value1, String value2){
        if(this.sql.contains("WHERE")){
            this.sql += " OR "+column+" BETWEEN "+value1+" AND "+value2;
        }else{
            this.sql += " WHERE "+column+" BETWEEN "+value1+" AND "+value2;
        }
        return this;
    }

    public Query having(String key, String operator, String value){
        if(this.sql.contains("HAVING")){
            this.sql += " AND "+key+" "+operator+" "+value;
        }else{
            this.sql += " HAVING "+key+" "+operator+" "+value;
        }
        return this;
    }

    public Query orHaving(String key, String operator, String value){
        if(this.sql.contains("HAVING")){
            this.sql += " OR "+key+" "+operator+" "+value;
        }else{
            this.sql += " HAVING "+key+" "+operator+" "+value;
        }
        return this;
    }

    public Query join(String type, String table, String equal){
        this.sql += " "+type+" JOIN "+table+" ON "+equal;
        return this;
    }

    public Query limit(int start, int limit){
        this.sql += " LIMIT "+start+", "+limit;
        return this;
    }

    public Query groupBy(String column){
        this.sql += " GROUP BY "+column;
        return this;
    }

    public Query orderBy(String column, String sort){
        sort = sort.isEmpty() ? "ASC" : sort;
        this.sql += " ORDER BY "+column+" "+sort.toUpperCase();
        return this;
    }

    public Query union(String sql){
        this.sql = "("+this.sql+") UNION ("+sql+")";
        return this;
    }

    public String get(){
        return this.sql;
    }

}
