package br.com.management.model.member.service;

import java.util.Arrays;
import java.util.List;

import br.com.management.model.member.dto.MembershipDTO;
import br.com.management.model.member.entity.Membership;
import br.com.management.model.member.repository.MemberRepository;
import br.com.management.model.project.dto.ProjectDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(final MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * Find members by project id
	 *
	 * @param id project's id
	 * @return {@link MembershipDTO}
	 */
	@Override
	public List<MembershipDTO> findMemberByProjectId(long id) {
		final ModelMapper modelMapper = new ModelMapper();
		final List<Membership> membership = this.memberRepository.findByProjectId(id);
		return Arrays.asList(modelMapper.map(membership, MembershipDTO[].class));
	}

	/**
	 * Find employees who is not member of specific project
	 *
	 * @param id project's id
	 * @return {@link MembershipDTO}
	 */
	@Override
	public List<MembershipDTO> findMemberWithoutProjectId(long id) {
		final ModelMapper modelMapper = new ModelMapper();
		final List<Membership> membership = this.memberRepository.findWithoutProjectId(id);
		return Arrays.asList(modelMapper.map(membership, MembershipDTO[].class));
	}

	/**
	 * Insert a new member into specific project
	 *
	 * @param projectDTO member dto
	 */
	@Override
	public void insertMember(ProjectDTO projectDTO) {
		if (projectDTO.getMember() != null) {
			final ModelMapper modelMapper = new ModelMapper();
			final MembershipDTO membershipDTO = new MembershipDTO();
			membershipDTO.setProject(projectDTO);
			membershipDTO.setPerson(projectDTO.getMember());
			final Membership entity = modelMapper.map(membershipDTO, Membership.class);
			this.memberRepository.save(entity);
		}
	}
}
