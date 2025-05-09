package pl.games.guessnumber.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

class UserNumberRepositoryTestImlp implements UserNumberRepository {

    Map<String, UserNumber> userNumbersDb = new HashMap<>();

    @Override
    public UserNumber save(final UserNumber userNumber) {
        userNumber.setId("12345");
        userNumbersDb.put("12345", userNumber);
        return userNumber;
    }

    @Override
    public <S extends UserNumber> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends UserNumber> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends UserNumber> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserNumber> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends UserNumber> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends UserNumber> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserNumber> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserNumber> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserNumber, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserNumber> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<UserNumber> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<UserNumber> findAll() {
        return List.of();
    }

    @Override
    public List<UserNumber> findAllById(final Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final UserNumber entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends UserNumber> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserNumber> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<UserNumber> findAll(final Pageable pageable) {
        return null;
    }
}
