package cs.miu.edu.mediatationattendance.repository;

import org.springframework.data.repository.CrudRepository;

import cs.miu.edu.mediatationattendance.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
