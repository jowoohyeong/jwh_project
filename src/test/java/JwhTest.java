import org.junit.jupiter.api.*;

public class JwhTest {

    @Test
    void create() {
        System.out.println("create1. study _3");
    }

    @Test
    void create2() {
        System.out.println("create2(), study");
    }

    @Disabled
    @Test
    void Disable() {
        System.out.println("Disabled: 해당 테스트 무시");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("--- BeforeAll: 테스트 클래스 초기화시 처음 단 한번만 실행----");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("--------- AfterAll: 테스트 후 마지막 단 한번만 실행 ---------");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach: 테스트 클래스 실행 이전마다 실행");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach: 테스트 클래스 실행 이후마다 실행");
    }

    @Test
    void lastTest() {
        System.out.println("lastTest");
    }
}
