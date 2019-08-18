package com.wctf.task.go.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskVo {
	private Integer id;
	private Timestamp createTs;
	private User createUser;
	private String title;
	private String description;
	private TaskStatus status;
	private Integer leftDays;
	List<Attachment> attachments = new ArrayList<>();
	List<Comment> comments = new ArrayList<>();

	public void addAttachment(Attachment attachment) {
		attachments.add(attachment);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Integer getLeftDays() {
		return leftDays;
	}

	public void setLeftDays(Integer leftDays) {
		this.leftDays = leftDays;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
