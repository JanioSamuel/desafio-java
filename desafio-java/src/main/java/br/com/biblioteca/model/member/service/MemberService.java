package br.com.biblioteca.model.member.service;

import java.util.List;

import br.com.biblioteca.model.member.dto.MembershipDTO;
import br.com.biblioteca.model.project.dto.ProjectDTO;

public interface MemberService {

	/**
	 * Find members by project id
	 * @param id project's id
	 * @return {@link MembershipDTO}
	 */
	List<MembershipDTO> findMemberByProjectId(final long id);

	/**
	 * Find employees who is not member of specific project
	 * @param id project's id
	 * @return {@link MembershipDTO}
	 */
	List<MembershipDTO> findMemberWithoutProjectId(final long id);

	/**
	 * Insert a new member into specific project
	 * @param membershipDTO member dto
	 */
	void insertMember(final ProjectDTO membershipDTO);
}
