package models;

import java.util.Date;

import service.Priority;
import service.Status;

public class Task {
private String task_title;
private String task_description;
private Priority prioirty;
private Date due_date;
private Status staus;
public Task() {
	super();
	
}

public Task(String task_title, String task_description, Priority prioirty, Date due_date, Status staus) {
	super();
	this.task_title = task_title;
	this.task_description = task_description;
	this.prioirty = prioirty;
	this.due_date = due_date;
	this.staus = staus;
}

public String getTask_title() {
	return task_title;
}
public void setTask_title(String task_title) {
	this.task_title = task_title;
}
public String getTask_description() {
	return task_description;
}
public void setTask_description(String task_description) {
	this.task_description = task_description;
}
public Priority getPrioirty() {
	return prioirty;
}
public void setPrioirty(Priority prioirty) {
	this.prioirty = prioirty;
}
public Date getDue_date() {
	return due_date;
}
public void setDue_date(Date due_date) {
	this.due_date = due_date;
}
public Status getStaus() {
	return staus;
}
public void setStaus(Status staus) {
	this.staus = staus;
}
@Override
public String toString() {
	return "task [task_title=" + task_title + ", task_description=" + task_description + ", prioirty=" + prioirty
			+ ", due_date=" + due_date + ", staus=" + staus + "]";
}

}
