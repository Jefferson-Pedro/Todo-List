package br.com.portifolio.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_tasks")
public class Task {
	
	@Id
	@GeneratedValue( generator = "UUID")
	private UUID id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "title", length = 50)
	private String title;
	
	@Column(name = "start_at", nullable = false)
	private LocalDateTime startAt;
	
	@Column(name = "end_at", nullable = false)
	private LocalDateTime endAt;
	
	@Column(name = "priority")
	private String priority;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	private UUID idUser;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws Exception{
		System.err.println("Titulo: " + this.title);
		if(title.length() > 50){
			throw new Exception("O campo title deve conter no máximo 50 caracteres");
		}
		this.title = title;
	}

	public LocalDateTime getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}

	public LocalDateTime getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}
}
