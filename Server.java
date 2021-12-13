package ui;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

import dao.NewsDao;
import dao.UserDao;
import dao.impl.NewsImpl;
import dao.impl.UserImpl;

public class Server {
	public static void main(String[] args) {

		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "policy\\policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			LocateRegistry.createRegistry(9999);
			NewsDao newsDao = new NewsImpl();
			UserDao userDao = new UserImpl();
			
			//Bind vào RMIRegistry
			Naming.bind("rmi://DESKTOP-7DCNV58:9999/newsDao", newsDao);
			Naming.bind("rmi://DESKTOP-7DCNV58:9999/userDao", userDao);
			System.out.println("Server started at " + LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
