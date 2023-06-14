package kopo.poly;

import kopo.poly.dto.NlpDTO;
import kopo.poly.dto.OcrDTO;
import kopo.poly.dto.StudentDTO;
import kopo.poly.service.INlpService;
import kopo.poly.service.IOcrService;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@Slf4j
//RequiredArgsConstructor : 자동으로 생성자를 만들어줌 - Spring 메모리에 올라온 객체(Service 등)을 변수에 넣는다.
//Spring은 무조건 인터페이스를 통해서 객체를 확인함
//메모리에 올라간 인터페이스중 변수로 선언된 타입과 같은 것을 찾아서
//컴파일 과정에 해당 인터페이스를 매개변수로 한 생성자를 만든다.
@RequiredArgsConstructor
@SpringBootApplication
//web이 아닌 콘솔창에서 실행할 때는 항상 CommandLineRunner 인터페이스를 상속받아 구현해야 함.
public class AiJavaPrjApplication implements CommandLineRunner {

    // @Services 정의된 자바파일
    // Spring Frameworks 실행될 때, @Service 정의한 자바는 자동으로 메모리에 올림
    // 메모리에 올라간 OcrService 객체를 ocrService 변수에 객체를 넣어주기
    
    //Spring에 올라갈 변수는 모두 상수
    private final IOcrService ocrService; // 이미지 인식

    private final INlpService nlpService; // 자연어 처리

    private final IStudentService studentService;

    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("자바 프로그래밍 시작!");
        Scanner in = new Scanner(System.in);

        
//        String filePath = "img"; // 문자열을 인식할 이미지 파일 경로
//        //한글 파일String fileName = "news01.jpg"; // 문자열을 인식할 이미지 파일 이름
//        String fileName = "news01.jpg";
//        //String fileName = "engsample.jpg";
//
//        // 전달할 값(Parameter) 약자로 보통 변수명 앞에 p를 붙임 => pDTO
//        OcrDTO pDTO = new OcrDTO(); //OcrService의 함수에 정보를 전달할 DTO를 메모리에 올리기
//
//        pDTO.setFilePath(filePath);
//        pDTO.setFileName(fileName);
//
//        // 실형 결과(Result) 약자로 보통 변수명 앞에 r을 붙임 => rDTO
//        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
//
//        String result = rDTO.getResult();
//
//        log.info("인식된 문자열");
//        log.info("\n"+result);
//
//        log.info("--------------------------------------------------------");
//        NlpDTO nlpDTO = nlpService.getPlainText(result);
//        log.info("형태소별 품사 분석 결과 : " + nlpDTO.getResult());
//
//        // 명사 추출 결과
//        nlpDTO = nlpService.getNouns(result);
//
//        List<String> nouns = nlpDTO.getNouns(); // 명사 추출 결과를 nouns 변수에 저장
//
//        // 중복을 포함하는 List 구조의 nouns 객체의 값들을 중복제거
//        // Set 구조는 중복을 허용하지 않기 때문에 List -> Set 구조로 변환하면 자동으로 중복된 값은 제거됨
//        Set<String> distinct = new HashSet<>(nouns);
//
//        log.info("중복 제거 수행 전 단어 수 : " + nouns.size());
//        log.info("중복 제거 수행 후 단어 수 : " + distinct.size());
//
//        // 단어, 빈도수를 Map 구조로 저장하기 위해 객체 새엇ㅇ
//        // Map 구조의 키는 중복 불가능 (값은 중복 가능)
//        Map<String,Integer> rMap = new HashMap<>();
//
//        // 중복 제거된 전체 단어마다 반복하기
//        for(String s : distinct){
//            //frequency (컬렉션, 빈도수를 찾을 문자열 혹은 정수 및 실수) -> 빈도수를 정수형으로 반환
//            int count = Collections.frequency(nouns, s);// 단어 빈도수
//            rMap.put(s, count);
//
//
//            log.info(s + " : " + count);
//        }
//
//        // 빈도수 결과를 정렬
//        // 정렬을 위해 맵에 저장된 레코드 1개(키, 값)을 리스트 구조로 변경
//        List<Map.Entry<String,Integer>> sortResult = new LinkedList<>(rMap.entrySet());
//
//        // 저장된 List 결과를 정렬 (내림차순)
//        // o1 은 sortResult가 가지고 있는 선행 인덱스 o2는 후속 인덱스
//        // 후속 인덱스의 값을 o1의 선행 인덱스 값과 비교 하여 정렬하겠다.
//        Collections.sort(sortResult, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//
//        log.info("가장 많이 사용된 단어는? : " + sortResult);
/**********************************************************************************/

         // 학생 등록, 수정, 삭제에 활용될 DTO
        StudentDTO pDTO;
        List<StudentDTO> rList; //DB 조회 결과를 표현

        // 학생 등록하기
        pDTO = new StudentDTO();

        // Student 테이블에 저장할 값을 DTO에 저장하기
        pDTO.setUserId("junsu123");
        pDTO.setUserName("한준수12");
        pDTO.setEmail("wnstn7759@naver.com");
        pDTO.setAddr("서울");

        // 주요 로직인 서비스 호출
        rList = studentService.insertStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });


/*****************************************************************************/
        // 학생 수정하기
        pDTO = new StudentDTO();
        
        pDTO.setUserId("junsu12"); //PK 칼럼인 회원 아이디를 기준으로 데이터를 수정
        pDTO.setUserName("한준수_수정");
        pDTO.setEmail("wnstn7759@naver.com_수정");
        pDTO.setAddr("서울_수정");

        rList = studentService.updateStudent(pDTO);

        rList.forEach(dto -> {
            log.info("DB에 저장된 아이디 : " + dto.getUserId());
            log.info("DB에 저장된 이름 : " + dto.getUserName());
            log.info("DB에 저장된 이메일 : " + dto.getEmail());
            log.info("DB에 저장된 주소 : " + dto.getAddr());
        });


/***************************************************************************/


        // Delete
        log.info("삭제하고 싶은 ID가 있다면 Y 없다면 아무키");
        String str = in.nextLine();

        if(str.startsWith("y") || str.startsWith("Y")){
            log.info("삭제할 유저 아이디를 입력하세요");
            String id = in.nextLine();
            for(int  i = 0; i < rList.size(); i++){

                pDTO.setUserId(id);

                studentService.deleteStudent(pDTO);

                rList.forEach(dto -> {
                    log.info("DB에 저장된 아이디 : " + dto.getUserId());
                    log.info("DB에 저장된 이름 : " + dto.getUserName());
                    log.info("DB에 저장된 이메일 : " + dto.getEmail());
                    log.info("DB에 저장된 주소 : " + dto.getAddr());
                });

            }
        }else{
            log.info("프로그램을 종료합니다.");
        }
/***************************************************************************************/



        log.info("\n자바 프로그래밍 종료");
    }
}
