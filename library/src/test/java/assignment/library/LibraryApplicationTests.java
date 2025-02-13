package assignment.library;

import jakarta.persistence.PreUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LibraryApplicationTests.class)
class LibraryApplicationTests {

	public int testValue;

	@BeforeEach
	void init() {
		testValue = 1;
	}

	@Test
	void 테스트_확인() {
		assertThat(testValue).isEqualTo(1);
	}

}
