package ui;

import java.util.List;

import javax.persistence.EntityManager;

import dao.impl.MyEntityManager;
import entity.News;

public class MainTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManager em = MyEntityManager.getInstance().getEntityManager();

		//		db.news.createIndex({tags:"text", newsCategories:"text"})
		String keyword = "Database";
		List<News> listNews = em.createNativeQuery("db.news.find({'$text':{'$search':'" + keyword + "'}})", News.class)
		.getResultList();
		
		listNews.forEach(nw -> System.out.println(nw));
	}
}
