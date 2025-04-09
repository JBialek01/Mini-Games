package pl.games.lotek.domain.usershitsranking;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class UsersHitsRankingRepositoryTestImpl implements UsersHitsRankingRepository {

    Map<String, UsersHitsRanking> usersHitsRankingDb = new HashMap<>();

    @Override
    public List<UsersHitsRanking> findByDateBetweenAndUserId(final Instant fromTime, final Instant toTime, final String userId) {
        return usersHitsRankingDb
                .values()
                .stream()
                .filter(
                        usersHitsRanking -> usersHitsRanking.getUserId().equals(userId) && usersHitsRanking.getTimestamp().isAfter(fromTime) && usersHitsRanking.getTimestamp().isBefore(toTime)
                ).collect(Collectors.toList());
    }

    @Override
    public List<UsersHitsRanking> findByDateBetween(final Instant fromTime, final Instant toTime) {
        return usersHitsRankingDb
                .values()
                .stream()
                .filter(
                        usersHitsRanking -> usersHitsRanking.getTimestamp().isAfter(fromTime) && usersHitsRanking.getTimestamp().isBefore(toTime)
                ).collect(Collectors.toList());
    }

    @Override
    public <S extends UsersHitsRanking> List<S> saveAll(final Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S entity : entities) {
            usersHitsRankingDb.put(entity.getId(), entity);
            saved.add(entity);
        }
        return saved;
    }

    @Override
    public <S extends UsersHitsRanking> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends UsersHitsRanking> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends UsersHitsRanking> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UsersHitsRanking> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends UsersHitsRanking> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends UsersHitsRanking> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UsersHitsRanking> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UsersHitsRanking> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends UsersHitsRanking, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UsersHitsRanking> S save(final S entity) {
        return null;
    }


    @Override
    public Optional<UsersHitsRanking> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<UsersHitsRanking> findAll() {
        return List.of();
    }

    @Override
    public List<UsersHitsRanking> findAllById(final Iterable<String> strings) {
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
    public void delete(final UsersHitsRanking entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends UsersHitsRanking> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UsersHitsRanking> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<UsersHitsRanking> findAll(final Pageable pageable) {
        return null;
    }
}
