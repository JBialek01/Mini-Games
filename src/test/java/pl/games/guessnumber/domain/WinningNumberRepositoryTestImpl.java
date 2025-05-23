package pl.games.guessnumber.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class WinningNumberRepositoryTestImpl implements WinningNumberRepository {

    Map<String, WinningNumber> winningNumbersDb = new HashMap<>();

    @Override
    public WinningNumber save(final WinningNumber winningNumber) {
        winningNumber.setId("12345");
        winningNumbersDb.put("12345", winningNumber);
        return winningNumber;
    }

    @Override
    public WinningNumber findByDateBetween(final Instant fromTime, final Instant toTime) {
        return null;
    }

    @Override
    public <S extends WinningNumber> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends WinningNumber> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends WinningNumber> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinningNumber> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends WinningNumber> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends WinningNumber> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinningNumber> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinningNumber> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinningNumber, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends WinningNumber> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<WinningNumber> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<WinningNumber> findAll() {
        return List.of();
    }

    @Override
    public List<WinningNumber> findAllById(final Iterable<String> strings) {
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
    public void delete(final WinningNumber entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends WinningNumber> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinningNumber> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<WinningNumber> findAll(final Pageable pageable) {
        return null;
    }
}
