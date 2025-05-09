package pl.games.guessnumber.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class UserGameSessionRepositoryTestImpl implements UserGameSessionRepository {
    @Override
    public Optional<UserGameSessionResult> findByUserIdAndDate(final String userId, final LocalDate date) {
        return Optional.empty();
    }

    @Override
    public List<UserGameSessionResult> findAllByDate(final LocalDate date) {
        return List.of();
    }

    @Override
    public <S extends UserGameSessionResult> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends UserGameSessionResult> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends UserGameSessionResult> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserGameSessionResult> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends UserGameSessionResult> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends UserGameSessionResult> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserGameSessionResult> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserGameSessionResult> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserGameSessionResult, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserGameSessionResult> S save(final S entity) {
        return null;
    }

    @Override
    public <S extends UserGameSessionResult> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<UserGameSessionResult> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<UserGameSessionResult> findAll() {
        return List.of();
    }

    @Override
    public List<UserGameSessionResult> findAllById(final Iterable<String> strings) {
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
    public void delete(final UserGameSessionResult entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends UserGameSessionResult> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserGameSessionResult> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<UserGameSessionResult> findAll(final Pageable pageable) {
        return null;
    }
}
