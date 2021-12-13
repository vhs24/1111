package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6691760162170255839L;
	@Id
	private Long id;
	private String newsTitle;
	private String newsContent;
	private LocalDateTime creationDate;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> newsCategories;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> votes;
	
	public News() {
	}
	
	public News(Long id) {
		this.id = id;
	}

	public News(Long id, String newsTitle, String newsContent, LocalDateTime creationDate, Set<String> newsCategories,
			Set<String> tags, Set<String> votes) {
		super();
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.creationDate = creationDate;
		this.newsCategories = newsCategories;
		this.tags = tags;
		this.votes = votes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public LocalDateTime getCreateDate() {
		return creationDate;
	}

	public void setCreateDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Set<String> getNewsCategories() {
		return newsCategories;
	}

	public void setNewsCategories(Set<String> newsCategories) {
		this.newsCategories = newsCategories;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getVotes() {
		return votes;
	}

	public void setVotes(Set<String> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", newsTitle=" + newsTitle + ", newsContent=" + newsContent + ", creationDate="
				+ creationDate + ", newsCategories=" + newsCategories + ", tags=" + tags + ", votes=" + votes + "]";
	}
}
