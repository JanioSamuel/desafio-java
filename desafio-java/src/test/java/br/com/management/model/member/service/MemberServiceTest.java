package br.com.management.model.member.service;

import java.util.ArrayList;
import java.util.List;

import br.com.management.model.member.dto.MembershipDTO;
import br.com.management.model.member.entity.Membership;
import br.com.management.model.member.repository.MemberRepository;
import br.com.management.model.member.service.MemberService;
import br.com.management.model.member.service.MemberServiceImpl;
import br.com.management.model.person.entity.Person;
import br.com.management.model.project.dto.ProjectDTO;
import br.com.management.model.project.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

	private MemberService memberService;
	private MemberRepository memberRepository;

	@BeforeEach
	void prepareTests() {
		this.memberRepository = Mockito.mock(MemberRepository.class);
		this.memberService = new MemberServiceImpl(memberRepository);
	}

	@Test
	void verifyFindMemberByProjectId() {
		final long id = 1;
		final Membership membership = new Membership();
		final Membership membership2 = new Membership();
		final Person person = new Person();
		final Project project = new Project();
		final List<Membership> membershipList = new ArrayList<>();
		membership.setId(1);
		person.setId(2);
		person.setName("Employee");
		person.setEmployee(true);
		project.setId(4);
		project.setManager(person);
		membership.setProject(project);
		membership.setPerson(person);
		membership2.setProject(project);
		membership2.setPerson(person);
		membershipList.add(membership);
		membershipList.add(membership2);
		Mockito.when(this.memberRepository.findByProjectId(id)).thenReturn(membershipList);
		final List<MembershipDTO> response = this.memberService.findMemberByProjectId(id);
		Mockito.verify(this.memberRepository).findByProjectId(id);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), membershipList.size());
	}

	@Test
	void verifyFindNoMemberByProjectId() {
		final long id = 1;
		Mockito.when(this.memberRepository.findByProjectId(id)).thenReturn(new ArrayList<>());
		final List<MembershipDTO> response = this.memberService.findMemberByProjectId(id);
		Mockito.verify(this.memberRepository).findByProjectId(id);
		assertTrue(response.isEmpty());
	}

	@Test
	void verifyFindMemberWithoutProjectId() {
		final long id = 1;
		final Membership membership = new Membership();
		final Membership membership2 = new Membership();
		final Person person = new Person();
		final Project project = new Project();
		final List<Membership> membershipList = new ArrayList<>();
		membership.setId(1);
		person.setId(2);
		person.setName("Employee");
		person.setEmployee(true);
		project.setId(4);
		project.setManager(person);
		membership.setProject(project);
		membership.setPerson(person);
		membership2.setProject(project);
		membership2.setPerson(person);
		membershipList.add(membership);
		membershipList.add(membership2);
		Mockito.when(this.memberRepository.findWithoutProjectId(id)).thenReturn(membershipList);
		final List<MembershipDTO> response = this.memberService.findMemberWithoutProjectId(id);
		Mockito.verify(this.memberRepository).findWithoutProjectId(id);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), membershipList.size());
	}

	@Test
	void verifyInsertMember() {
		final ProjectDTO projectDTO = new ProjectDTO();
		final Person person = new Person();
		final Membership membership = new Membership();
		person.setName("Test");
		projectDTO.setMember(person);
		Mockito.when(this.memberRepository.save(Mockito.any(Membership.class))).thenReturn(membership);
		this.memberService.insertMember(projectDTO);
		Mockito.verify(this.memberRepository).save(Mockito.any(Membership.class));
	}


}
