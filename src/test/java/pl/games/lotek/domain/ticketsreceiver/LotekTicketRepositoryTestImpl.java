package pl.games.lotek.domain.ticketsreceiver;

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
import java.util.UUID;
import java.util.function.Function;

class LotekTicketRepositoryTestImpl implements LotekTicketRepository {

    private final Map<String, LotekTicket> tickets = new HashMap<>();

    @Override
    public LotekTicket save(final LotekTicket lotekTicket) {
        tickets.put(UUID.randomUUID().toString(), lotekTicket);
        return lotekTicket;
    }

    @Override
    public <S extends LotekTicket> List<S> saveAll(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<LotekTicket> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public List<LotekTicket> findAll() {
        return List.of();
    }

    @Override
    public List<LotekTicket> findAllById(final Iterable<String> strings) {
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
    public void delete(final LotekTicket entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends LotekTicket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<LotekTicket> findByUserIdAndDateBetween(final String userId, final Instant fromTime, final Instant toTime) {
        return tickets.values().stream()
                .filter(lotekTicket -> lotekTicket.getUserId().equals(userId))
                .filter(lotekTicket -> lotekTicket.getTimestamp().isAfter(fromTime) && lotekTicket.getTimestamp().isBefore(toTime))
                .toList();
    }

    @Override
    public List<LotekTicket> findByDateBetween(final Instant fromTime, final Instant toTime) {
        return tickets.values().stream()
                .filter(lotekTicket -> lotekTicket.getTimestamp().isAfter(fromTime) && lotekTicket.getTimestamp().isBefore(toTime))
                .toList();
    }

    @Override
    public <S extends LotekTicket> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends LotekTicket> List<S> insert(final Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends LotekTicket> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends LotekTicket> List<S> findAll(final Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends LotekTicket> List<S> findAll(final Example<S> example, final Sort sort) {
        return List.of();
    }

    @Override
    public <S extends LotekTicket> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends LotekTicket> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends LotekTicket> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends LotekTicket, R> R findBy(final Example<S> example, final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<LotekTicket> findAll(final Sort sort) {
        return List.of();
    }

    @Override
    public Page<LotekTicket> findAll(final Pageable pageable) {
        return null;
    }
}
