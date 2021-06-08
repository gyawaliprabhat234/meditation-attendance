/**
 * 
 */
package cs.miu.edu.meditationattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs.miu.edu.meditationattendance.domain.Administrator;

/**
 * @author Anderson
 *
 */
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
