package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void update(Genre genre) {
        em.merge(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }
}
