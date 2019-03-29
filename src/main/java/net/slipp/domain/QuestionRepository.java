package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	//인터페이스라서 구현체가 없음, 구현은 spring프레임워크해서 해서 인스턴스까지 만들어서 관리하고있음

}
