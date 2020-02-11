package org.hibernate.onetomany.unidirectional.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.onetomany.unidirectional.model.Course;
import org.hibernate.onetomany.unidirectional.model.Instructor;
import org.hibernate.onetomany.unidirectional.model.InstructorDetail;
import org.hibernate.onetomany.unidirectional.model.Review;

public class GetCourseReviews {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Review.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int theCourseId = 10;



            session.getTransaction().commit();
        }
        finally {
            session.close();
            sessionFactory.close();
        }

    }

}
