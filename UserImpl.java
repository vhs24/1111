package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.UserDao;
import entity.User;

public class UserImpl extends UnicastRemoteObject implements UserDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2368001032605529265L;
	private EntityManager em;
	
	public UserImpl()  throws RemoteException{
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean addUser(User u) throws RemoteException {
		boolean result = false;
		
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(u);
			tr.commit();
			result = true;
		}catch (Exception e) {
			tr.rollback();
			throw e;
		}
		
		return result;
	}

//	 db.users.aggregate([{'$project':{'num':{'$size':'$listOfComments'}}}, {'$match':{'num':{'$gte':3}}}])
	@Override
	public Map<User, Integer> getStatistics() throws RemoteException {
		Map<User, Integer> map = new HashMap<User, Integer>();
		
		String sqlString = "db.users.aggregate([{'$project':{'num':{'$size':'$listOfComments'}}}, {'$match':{'num':{'$gte':3}}}])";
		List<?> temp = em.createNativeQuery(sqlString)
		.getResultList();
		for(Object o : temp) {
			Object[] x = (Object[]) o;
			map.put(em.find(User.class, (Long)x[0]), (Integer) x[1]);
		}
		
		return map;
	}
	
//	db.users.find({'$or':[{'userName':'John Smith'},{'userEmail':'xyz@gmail.com'}]})
	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers(String keyword) throws RemoteException {
		String sqlString = "db.users.find({'$or':[{'userName':'"+keyword+"'},{'userEmail':'"+keyword+"'}]})";
		return em.createNativeQuery(sqlString, User.class).getResultList();
	}
	
}
