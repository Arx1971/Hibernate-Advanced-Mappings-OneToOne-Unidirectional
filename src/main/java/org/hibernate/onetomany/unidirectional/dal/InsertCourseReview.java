package org.hibernate.onetomany.unidirectional.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.onetomany.unidirectional.model.Course;
import org.hibernate.onetomany.unidirectional.model.Instructor;
import org.hibernate.onetomany.unidirectional.model.InstructorDetail;
import org.hibernate.onetomany.unidirectional.model.Review;

import java.util.ArrayList;
import java.util.List;

public class InsertCourseReview {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class).addAnnotatedClass(Review.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Course course = session.get(Course.class, 10);

            List<Review> reviews = new ArrayList<>();

            Review review1 = new Review("This is the best Class.");
            Review review2 = new Review("This class is awesome.");
            reviews.add(review1);
            reviews.add(review2);

            course.setReviews(reviews);

            session.save(course);

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
