package ui;

import java.rmi.Naming;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.NewsDao;
import dao.UserDao;
import entity.Address;
import entity.Comment;
import entity.Gender;
import entity.News;
import entity.User;

public class Client {
	public static void main(String[] args) {

		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "policy\\policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			//Lookup từ RMIRegistry
			NewsDao newsDao = (NewsDao) Naming.lookup("rmi://DESKTOP-7DCNV58:9999/newsDao");
			UserDao userDao = (UserDao) Naming.lookup("rmi://DESKTOP-7DCNV58:9999/userDao");
			
			System.out.println("===Câu 2a===");
			Address userAddress = new Address("21 2nd Street", "New York", "NY", "10021");
			Set<String> researchAreas = new HashSet<>();
			researchAreas.add("Architectures");
			researchAreas.add("Compiler Optimization");
			researchAreas.add("Embedded Systems");
			
			//user 1
			User user = new User(171180l, "John Nguyen", Gender.MALE, userAddress , "johnnguyen@gmail.com", "123456", researchAreas);
			Comment cmd3 = new Comment("What am I doing here. I'm an embedded developer for 3 years.", LocalDateTime.now().minusMonths(1), newsDao.getNews(20l)); //Tìm trong CSDL
			Comment cmd2 = new Comment("Good Job", LocalDateTime.now().minusDays(10), new News(9l)); //Hoặc news có id = 9 đã tồn tại
			Comment cmd1 = new Comment("Great! As a electronic engineer this is the main thing I am interested when it comes to programming (apart from Labview programming).", LocalDateTime.now().minusMonths(2), newsDao.getNews(20l));
			List<Comment> listOfComments = Arrays.asList(cmd1, cmd2, cmd3);
			user.setListOfComments(listOfComments);
			
			//user 2
			User user2 = new User(71810l, "John Smith", Gender.MALE, userAddress , "johnsmith@gmail.com", "123456", researchAreas);
			Comment cmd = new Comment("Good Job", LocalDateTime.now().minusDays(10), new News(9l)); //Hoặc news có id = 9 đã tồn tại
			List<Comment> listOfComments2 = Arrays.asList(cmd);
			user2.setListOfComments(listOfComments2);
			
			boolean result = userDao.addUser(user);
			if(result)
				System.out.println("Inserted!");
			else
				System.out.println("Fail!");
			
			boolean result2 = userDao.addUser(user2);
			if(result2)
				System.out.println("Inserted!");
			else
				System.out.println("Fail!");
			
			System.out.println("===Câu 2b===");
			newsDao.getNewsByTagsOrCategoriesName("\"dark-knight-rises\"")
			.forEach(nw -> System.out.println(nw));
//			
			System.out.println("==Câu 2c==");
			userDao.getStatistics()
			.forEach((us, num) -> {
				System.out.println(us);
				System.out.println("Number: " + num);
			});
			
			System.out.println("===Câu 2d===");
			newsDao.listNewsByUserEmail("johnnguyen@gmail.com")
			.forEach(nw -> System.out.println(nw));
			
			System.out.println("===Câu 2e===");
			userDao.listUsers("johnnguyen@gmail.com")
			.forEach(us -> System.out.println(us));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
