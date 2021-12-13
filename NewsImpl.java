package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;

import dao.NewsDao;
import entity.News;

public class NewsImpl extends UnicastRemoteObject implements NewsDao{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8926807582129947222L;
	private EntityManager em;
	public NewsImpl() throws RemoteException{
		em = MyEntityManager.getInstance().getEntityManager();
	}
	
//	Bước 1- Tạo text index: 
//	db.news.createIndex({tags:"text", newsCategories:"text"})
//	Bước 2: Text search
//	db.news.find({'$text':{'$search':"analysis"}})
	@SuppressWarnings("unchecked")
	@Override
	public List<News> getNewsByTagsOrCategoriesName(String value) throws RemoteException {
		return em.createNativeQuery("db.news.find({'$text':{'$search':'" + value + "'}})", News.class)
				.setMaxResults(5)
				.getResultList();
	}

	@Override
	public News getNews(Long id) throws RemoteException {
		return em.find(News.class, id);
	}

//	db.users.aggregate([{'$match':{'userEmail':'johnnguyen@gmail.com'}}, {'$lookup':{'from':'news', 'localField':'listOfComments.newsID', 'foreignField':'_id', 'as':'rs'}},{'$project':{'_id':0, 'rs':1}},{'$unwind':'$rs'}, {'$replaceRoot':{'newRoot':'$rs'}}])
	@SuppressWarnings("unchecked")
	@Override
	public List<News> listNewsByUserEmail(String email) throws RemoteException {
		String sqlString = "db.users.aggregate([{'$match':{'userEmail':'"+email+"'}}, {'$lookup':{'from':'news', 'localField':'listOfComments.newsID', 'foreignField':'_id', 'as':'rs'}},{'$project':{'_id':0, 'rs':1}},{'$unwind':'$rs'}, {'$replaceRoot':{'newRoot':'$rs'}}])";
		return em.createNativeQuery(sqlString , News.class)
				.getResultList();
	}
}
