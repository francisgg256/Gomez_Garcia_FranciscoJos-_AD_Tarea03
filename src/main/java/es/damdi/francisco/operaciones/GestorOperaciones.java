package es.damdi.francisco.operaciones;

import es.damdi.francisco.dominio.Especialidad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import es.damdi.francisco.utilidades.HibernateUtil;

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
}
