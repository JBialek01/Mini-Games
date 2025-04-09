package pl.games.lotek.domain.resultchecker;

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

class UserResultsRepositoryTestImpl implements UserResultsRepository {

    Map<String, UserResults> userResultsDb = new HashMap<>();

    @Override
    public boolean existsByUserIdAndUserNumbersId(final String userId, final String userNumbersId) {
        return false;
    }

    @Override
    public List<UserResults> findByUserIdAndDateBetween(final String userId, final Instant fromTime, final Instant toTime) {
        return userResultsDb.values().stream().filter(
                userResults -> userResults.getUserId().equals(userId) && userResults.getTimestamp().isAfter(fromTime) && userResults.getTimestamp().isBefore(toTime)
        ).toList();
    }

    @Override
    public List<UserResults> findByDateBetween(final Instant fromTime, final Instant toTime) {
        return userResultsDb.values().stream().filter(
                userResults -> userResults.getTimestamp().isAfter(fromTime) && userResults.getTimestamp().isBefore(toTime)
        ).toList();
    }

    @Override
    public UserResults save(final UserResults userResults) {
        userResultsDb.put(userResults.getId(), userResults);
        return userResults;
    }

    @Override
    public <S extends UserResults> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends UserResults> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends UserResults> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserResults> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends UserResults> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends UserResults> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserResults> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserResults> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserResults, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserResults> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<UserResults> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<UserResults> findAll() {
        return List.of();
    }

    @Override
    public List<UserResults> findAllById(final Iterable<String> strings) {
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
    public void delete(final UserResults entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends UserResults> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserResults> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<UserResults> findAll(final Pageable pageable) {
        return null;
    }
}
