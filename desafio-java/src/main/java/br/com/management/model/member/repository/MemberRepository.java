package br.com.management.model.member.repository;

import java.util.List;

import br.com.management.model.member.entity.Membership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Membership, Long> {

	@Query("select m from Membership m where m.project.id = :id and m.person.isEmployee = true")
	List<Membership> findByProjectId(final long id);

	@Query("select m from Membership m where m.project.id <> :id and m.person.isEmployee = true")
	List<Membership> findWithoutProjectId(final long id);
}
