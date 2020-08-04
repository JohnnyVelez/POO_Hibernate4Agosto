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
		
		
	   eliminarEstudiante(4);
	   eliminarCurso(1);
		
		
		
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
	
	static void eliminarEstudiante(int id) 
	{
		Estudiante est= getEstudiantePorId(id);
		if(est!=null) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(est);
			session.getTransaction().commit();
			session.close();}
			
			else {
				
				System.out.println("No existe estudiante con ID= " +id);
				}
		}
		
	
		
		
		static Estudiante getEstudiantePorId(int id)
		{
			
			Session session = sessionFactory.openSession();
			Query query=session
					.createQuery("from Estudiante where id=:id");
		    query.setParameter("id", id);
		    @SuppressWarnings("unchecked")
			List<Estudiante> estudiante=(List<Estudiante>) query.getResultList();
			if(estudiante.size()!=0){
				return estudiante.get(0);}
			
			return null; 
		}
		
	
		
	
	static void eliminarCurso(int id) 
	{
		Curso cur= getCursoPorId(id);
		if(cur!=null) {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(cur);
			session.getTransaction().commit();
			session.close();}
			
			else {
				
				System.out.println("No existe curso con ID= " +id);
				}
		}
		
	static Curso getCursoPorId(int id)
	{
		
		Session session = sessionFactory.openSession();
		Query query=session
				.createQuery("from Curso where id=:id");
	    query.setParameter("id", id);
	    @SuppressWarnings("unchecked")
		List<Curso> cursos=(List<Curso>) query.getResultList();
		if(cursos.size()!=0)
		{
			
			return cursos.get(0);}
		
		return null; 
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
