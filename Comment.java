package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7499175392797159236L;
	private String commentContent;
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="newsID")
	private News nw;

	public Comment() {
	}
	
	
	public Comment(String commentContent, LocalDateTime date, News news) {
		super();
		this.commentContent = commentContent;
		this.date = date;
		this.nw = news;
	}


	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public News getNews() {
		return nw;
	}

	public void setNews(News nw) {
		this.nw = nw;
	}

	@Override
	public String toString() {
		return "Comment [commentContent=" + commentContent + ", date=" + date + ", nw=" + nw + "]";
	}
	
}
