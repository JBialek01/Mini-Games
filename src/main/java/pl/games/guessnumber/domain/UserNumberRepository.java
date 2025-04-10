package pl.games.guessnumber.domain;


import org.springframework.data.mongodb.repository.MongoRepository;

interface UserNumberRepository extends MongoRepository<UserNumber, String> {
}
