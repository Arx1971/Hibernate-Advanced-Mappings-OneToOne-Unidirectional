package org.hibernate.onetomany.unidirectional.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.onetomany.unidirectional.model.Course;
import org.hibernate.onetomany.unidirectional.model.Instructor;
import org.hibernate.onetomany.unidirectional.model.InstructorDetail;


import java.util.ArrayList;
import java.util.List;

public class InsertCourseToInstructor {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();


        try {

            session.beginTransaction();

            List<Course> courses = new ArrayList<>();
            Course course1 = new Course("Machine Learning and Neural Network");
            Course course2 = new Course("Data Science and Machine Learning");
            courses.add(course1);
            courses.add(course2);

            Instructor instructor = session.get(Instructor.class, 1);

            course1.setInstructor(instructor); // setting up course instructor
            course2.setInstructor(instructor); // setting up course instructor

            instructor.setCourses(courses);

            session.save(course2);
            session.save(course1);

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}