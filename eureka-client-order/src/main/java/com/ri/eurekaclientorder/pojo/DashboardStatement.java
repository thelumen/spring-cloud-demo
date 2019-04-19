package com.ri.eurekaclientorder.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "dashboard_statement")
public class DashboardStatement {

    //唯一id
    private String _id;

    private String userId;

    private List<Statement> statements;

    public String get_id() {
        return _id;
    }

    public DashboardStatement set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DashboardStatement setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public DashboardStatement setStatements(List<Statement> statements) {
        this.statements = statements;
        return this;
    }

    @Override
    public String toString() {
        return "DashboardStatement{" +
                "_id='" + _id + '\'' +
                ", userId='" + userId + '\'' +
                ", statements=" + Arrays.toString(statements.toArray()) +
                '}';
    }
}
