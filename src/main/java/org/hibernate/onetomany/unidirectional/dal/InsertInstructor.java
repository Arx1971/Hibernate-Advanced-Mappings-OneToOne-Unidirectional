package org.hibernate.onetomany.unidirectional.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.onetomany.unidirectional.model.Course;
import org.hibernate.onetomany.unidirectional.model.Instructor;
import org.hibernate.onetomany.unidirectional.model.InstructorDetail;

public class InsertInstructor {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();
            Instructor instructor[] = {
                    new Instructor("Optimus", "Prime", "OptimusPrime@email.com"),
                    new Instructor("Megatron", "101", "Megatron101@email.com")
            };

            InstructorDetail instructorDetail[] = {
                    new InstructorDetail("youtube.com/Optimus", "Driving"),
                    new InstructorDetail("youtube.com/Megatron", "Flying")
            };

            for (int i = 0; i < 2; i++) {

                instructor[i].setInstructorDetail(instructorDetail[i]);

                session.save(instructor[i]);

            }
            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
