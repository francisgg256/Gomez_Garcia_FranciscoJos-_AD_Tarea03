package es.damdi.francisco.operaciones;

import es.damdi.francisco.dominio.Especialidad;
import es.damdi.francisco.dominio.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import es.damdi.francisco.utilidades.HibernateUtil;
import java.util.List;

public class GestorOperaciones {
    public void altaEspecialidad(String id, String nombre) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = sesion.beginTransaction();
            Especialidad nuevaEspecialidad = new Especialidad(id, nombre);
            sesion.persist(nuevaEspecialidad);
            tx.commit();
            System.out.println("Especialidad '" + nombre + "' guardada correctamente.");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al guardar la especialidad: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void listarProfesores() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Profesor> lista = sesion.createQuery("from Profesor", Profesor.class).getResultList();

            System.out.println("\n--- LISTADO DE PROFESORES ---");
            for (Profesor p : lista) {
                System.out.println("ID: " + p.getCodProf() + " | Nombre: " + p.getNombreApe() +
                        " | Especialidad: " + (p.getEspecialidad() != null ? p.getEspecialidad().getNombre() : "N/A"));
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }
}
