package com.cursos_online;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;

public class Main {
	
	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	
	static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	


	public static void main(String[] args) {
		
		Curso cur1= new Curso("Fundamentos de Java");
		Curso cur2= new Curso("Hibernate para principiantes");
		ingresarCurso(cur1);
		ingresarCurso(cur2);
		
		Estudiante est1= new Estudiante(0,"Juan","Menoscla");
		Estudiante est2= new Estudiante(0,"Jose","Caicedo");
		Estudiante est3= new Estudiante(0,"Matias","España");
		
		ingresarEstudiante(est1);
		
		ingresarEstudiante(est2);
		
		ingresarEstudiante(est3);
		
		List<Curso> cursos= getCursos();
		for(Curso temp:cursos) {
			
			System.out.println(temp);
		}
		
		List<Estudiante> estudiantes= getEstudiantes();
		for(Estudiante temp:estudiantes) {
			
			System.out.println(temp);
		}
		 
		List<Estudiante> estudiantes1= getEstudiantesPorNombre("Jose");
		for(Estudiante e:estudiantes1) 
		{
			System.out.println(e);
		}
		
		
		
	
	
		
	
		//Modificar Curso
		Curso curso1= new Curso(1,"Mantenimiento"); 
		modificarCursos(curso1);
		
		//Modificar Estudiante
		Estudiante estud1= new Estudiante(4,"Luis","Caicedo");
		modificarEstudiante(estud1);
		
		eliminarEstudiante(est2);
		eliminarCurso(curso1);
		
		
		
	}
	
	static void modificarCursos(Curso curso) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(curso);
		session.getTransaction().commit();
		session.close();
		
	}
	
	static void modificarEstudiante(Estudiante estudiante) 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(estudiante);
		session.getTransaction().commit();
		session.close();
		
		
		
		
	}
	
	static void eliminarEstudiante(Estudiante idEstudiante) 
	{

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(idEstudiante);
		session.getTransaction().commit();
		session.close();

	
		
	}
	
	static void eliminarCurso(Curso idCurso) 
	{
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(idCurso);
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	
	
	
	static List<Estudiante> getEstudiantesPorNombre(String nombre)
	{
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("from Estudiante where nombre=:nombre");
	    query.setParameter("nombre", nombre);
		List<Estudiante> estudiantes=(List<Estudiante>)query.getResultList();
		return estudiantes;
		
	}
	
	
	static List<Curso> getCursos(){
	Session session = sessionFactory.openSession();
	List<Curso> cursos=(List<Curso>)session.createQuery("from Curso",Curso.class ).list();
	return cursos;
}

	static List<Estudiante> getEstudiantes(){
	Session session = sessionFactory.openSession();
	List<Estudiante> estudiantes=(List<Estudiante>)session.createQuery("from Estudiante",Estudiante.class ).list();
	return estudiantes;
	
}
	
	
	static void ingresarEstudiante(Estudiante estudiante) {
		
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(estudiante);
		session.getTransaction().commit();
		session.close();

	}
	
	
	static void ingresarCurso(Curso curso) {
		
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		session.getTransaction().commit();
		session.close();

	}

}
