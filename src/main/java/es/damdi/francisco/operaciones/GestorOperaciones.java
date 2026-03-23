package es.damdi.francisco.operaciones;

import es.damdi.francisco.dominio.Asignatura;
import es.damdi.francisco.dominio.Centro;
import es.damdi.francisco.dominio.Especialidad;
import es.damdi.francisco.dominio.Profesor;
import es.damdi.francisco.utilidades.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class GestorOperaciones {

    // OPERACIONES DE PROFESORES

    public void altaProfesor(int codProf, String nombre, String sexo, String codEspecialidad, int codCentro) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Profesor p = new Profesor();
            p.setCodProf(codProf);
            p.setNombreApe(nombre);
            p.setSexo(sexo);

            Especialidad esp = sesion.get(Especialidad.class, codEspecialidad);
            Centro centro = sesion.get(Centro.class, codCentro);

            if (esp != null) p.setEspecialidad(esp);
            if (centro != null) p.setCentro(centro);

            sesion.persist(p);
            tx.commit();
            System.out.println("Profesor guardado correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al guardar profesor: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void bajaProfesor(int id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Profesor p = sesion.get(Profesor.class, id);
            if (p != null) {
                sesion.remove(p);
                tx.commit();
                System.out.println("Profesor eliminado.");
            } else {
                System.out.println("Profesor no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al dar de baja: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void infoProfesor(int id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Profesor p = sesion.get(Profesor.class, id);
            if (p != null) {
                System.out.println("\n--- INFO DEL PROFESOR ---");
                System.out.println("ID: " + p.getCodProf());
                System.out.println("Nombre: " + p.getNombreApe());
                System.out.println("Sexo: " + p.getSexo());
                System.out.println("Especialidad: " + (p.getEspecialidad() != null ? p.getEspecialidad().getNombre() : "N/A"));
                System.out.println("Centro: " + (p.getCentro() != null ? p.getCentro().getNomCentro() : "N/A"));
                System.out.println("Jefe: " + (p.getJefe() != null ? p.getJefe().getNombreApe() : "N/A"));
            } else {
                System.out.println("Profesor no encontrado.");
            }
        } finally {
            sesion.close();
        }
    }

    public void listarProfesores() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            var lista = sesion.createQuery("from Profesor", Profesor.class).getResultList();

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

    // OPERACIONES DE CENTROS

    public void altaCentro(int id, String nombre, String direccion, String localidad, String provincia) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Centro nuevoCentro = new Centro();
            nuevoCentro.setCodCentro(id);
            nuevoCentro.setNomCentro(nombre);
            nuevoCentro.setDireccion(direccion);
            nuevoCentro.setLocalidad(localidad);
            nuevoCentro.setProvincia(provincia);

            sesion.persist(nuevoCentro);
            tx.commit();
            System.out.println("Centro '" + nombre + "' guardado correctamente.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al guardar el centro: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void bajaCentro(int id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Centro centro = sesion.get(Centro.class, id);

            if (centro != null) {
                sesion.remove(centro);
                tx.commit();
                System.out.println("Centro con código '" + id + "' eliminado.");
            } else {
                System.out.println("No se encontró ningún centro con el código '" + id + "'.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al dar de baja el centro: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void modificarDirector(int idCentro, int idDirector) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Centro centro = sesion.get(Centro.class, idCentro);
            Profesor nuevoDirector = sesion.get(Profesor.class, idDirector);

            if (centro == null) {
                System.out.println("No se encontró el centro.");
            } else if (nuevoDirector == null) {
                System.out.println("No se encontró al profesor con ese ID.");
            } else {
                centro.setDirector(nuevoDirector);
                sesion.merge(centro);
                tx.commit();
                System.out.println("Director del centro actualizado a: " + nuevoDirector.getNombreApe());
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al modificar el director: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void listarCentros() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            var lista = sesion.createQuery("from Centro", Centro.class).getResultList();

            System.out.println("\n--- LISTADO DE CENTROS ---");
            for (Centro c : lista) {
                String nombreDirector = (c.getDirector() != null) ? c.getDirector().getNombreApe() : "Sin director";

                int numProfes = (c.getProfesores() != null) ? c.getProfesores().size() : 0;
                Set<Asignatura> asignaturasDistintas = new HashSet<>();
                if (c.getProfesores() != null) {
                    for (Profesor p : c.getProfesores()) {
                        if (p.getAsignaturas() != null) {
                            asignaturasDistintas.addAll(p.getAsignaturas());
                        }
                    }
                }

                System.out.println("\nCENTRO: " + c.getNomCentro() + " (Código: " + c.getCodCentro() + ")");
                System.out.println(" - Director: " + nombreDirector);
                System.out.println(" - Nº Profesores: " + numProfes);
                System.out.println(" - Nº Asignaturas distintas: " + asignaturasDistintas.size());
            }
            System.out.println("--------------------------");
        } catch (Exception e) {
            System.out.println("Error al listar centros: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    // OPERACIONES DE ESPECIALIDADES

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
            if (tx != null) tx.rollback();
            System.out.println("Error al guardar la especialidad: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void bajaEspecialidad(String id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Especialidad esp = sesion.get(Especialidad.class, id);

            if (esp != null) {
                sesion.remove(esp);
                tx.commit();
                System.out.println("Especialidad con código '" + id + "' eliminada correctamente.");
            } else {
                System.out.println("No se ha encontrado ninguna especialidad con el código '" + id + "'.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al dar de baja: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void modificarEspecialidad(String id, String nuevoNombre) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Especialidad esp = sesion.get(Especialidad.class, id);

            if (esp != null) {
                esp.setNombre(nuevoNombre);
                sesion.merge(esp);
                tx.commit();
                System.out.println("Nombre de la especialidad actualizado a '" + nuevoNombre + "'.");
            } else {
                System.out.println("No se ha encontrado ninguna especialidad con el código '" + id + "'.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al modificar: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void listarEspecialidades() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            var lista = sesion.createQuery("from Especialidad", Especialidad.class).getResultList();

            System.out.println("\n--- LISTADO DE ESPECIALIDADES ---");
            if (lista.isEmpty()) {
                System.out.println("No hay especialidades registradas.");
            } else {
                for (Especialidad e : lista) {
                    System.out.println("CÓDIGO: " + e.getIdEspecialidad() + " | NOMBRE: " + e.getNombre());
                }
            }
            System.out.println("---------------------------------");
        } catch (Exception e) {
            System.out.println("Error al listar especialidades: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    // OPERACIONES DE ASIGNATURAS

    public void altaAsignatura(String id, String nombre) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Asignatura a = new Asignatura();
            a.setCodAsig(id);
            a.setNombreAsi(nombre);
            sesion.persist(a);
            tx.commit();
            System.out.println("Asignatura guardada.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error al guardar: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void bajaAsignatura(String id) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Asignatura a = sesion.get(Asignatura.class, id);
            if (a != null) {
                sesion.remove(a);
                tx.commit();
                System.out.println("Asignatura eliminada.");
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            sesion.close();
        }
    }

    public void listarAsignaturasYProfesores() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            var asignaturas = sesion.createQuery("from Asignatura", Asignatura.class).getResultList();
            var todosProfesores = sesion.createQuery("from Profesor", Profesor.class).getResultList();

            System.out.println("\n--- LISTADO DE ASIGNATURAS ---");
            for (Asignatura a : asignaturas) {
                int contadorProfes = 0;
                System.out.println("\nASIGNATURA: " + a.getNombreAsi());

                for (Profesor p : todosProfesores) {
                    if (p.getAsignaturas() != null && p.getAsignaturas().contains(a)) {
                        contadorProfes++;
                        System.out.println("  - Profesor: " + p.getNombreApe() +
                                " | Esp: " + (p.getEspecialidad() != null ? p.getEspecialidad().getNombre() : "N/A") +
                                " | Centro: " + (p.getCentro() != null ? p.getCentro().getNomCentro() : "N/A") +
                                " | Jefe: " + (p.getJefe() != null ? p.getJefe().getNombreApe() : "Ninguno"));
                    }
                }
                System.out.println("  * TOTAL PROFESORES: " + contadorProfes);
            }
        } finally {
            sesion.close();
        }
    }
}
