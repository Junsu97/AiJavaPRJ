package kopo.poly;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {

        SpringApplication.run(AiJavaPrjApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("자바 프로그래밍 시작!");
        
        String filePath = "img"; // 문자열을 인식할 이미지 파일 경로
        //한글 파일String fileName = "news01.jpg"; // 문자열을 인식할 이미지 파일 이름
        String fileName = "news01.jpg";
        //String fileName = "engsample.jpg";

        // 전달할 값(Parameter) 약자로 보통 변수명 앞에 p를 붙임 => pDTO
        OcrDTO pDTO = new OcrDTO(); //OcrService의 함수에 정보를 전달할 DTO를 메모리에 올리기

        pDTO.setFilePath(filePath);
        pDTO.setFileName(fileName);

        // 실형 결과(Result) 약자로 보통 변수명 앞에 r을 붙임 => rDTO
        OcrDTO rDTO = ocrService.getReadforImageText(pDTO);

        String result = rDTO.getResult();
        
        log.info("인식된 문자열");
        log.info("\n"+result);
        
        log.info("\n자바 프로그래밍 종료");
    }
}
