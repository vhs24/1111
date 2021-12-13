package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "users", 
	indexes = {
			@Index(columnList = "userName, userEmail asc")
	}
)
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5811125794644714698L;
	@Id
	private Long id;
	private String userName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Embedded
	private Address userAddress;
	private String userEmail;
	private String userPassword;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> researchAreas;
	
	@ElementCollection
	private List<Comment> listOfComments;
	
	public User() {
	}

	public User(Long id, String userName, Gender gender, Address userAddress, String userEmail, String userPassword,
			Set<String> researchAreas) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.researchAreas = researchAreas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<String> getResearchAreas() {
		return researchAreas;
	}

	public void setResearchAreas(Set<String> researchAreas) {
		this.researchAreas = researchAreas;
	}
	
	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", gender=" + gender + ", userAddress=" + userAddress
				+ ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", researchAreas=" + researchAreas
				+ "]";
	}
	
	
}
