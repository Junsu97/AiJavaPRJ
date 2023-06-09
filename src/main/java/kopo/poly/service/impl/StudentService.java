package kopo.poly.service.impl;

import kopo.poly.dto.StudentDTO;
import kopo.poly.persistance.mapper.IStudentMapper;
import kopo.poly.service.IStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final IStudentMapper studentMapper;
    @Override
    public List<StudentDTO> insertStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertStudent Start!");

        // Student 테이블에 등록된 학생 아이디가 존재하는지 체크하기 위해 DB 조회하기
        // select 쿼리 한명 조회 
        Optional<StudentDTO> res = Optional.ofNullable(
                studentMapper.getStudent(pDTO)
        );
        
        // insert 쿼리
        if(!res.isPresent()){//DB 조회 결과로 회원아이디가 존재 하지 않는다면
            studentMapper.insertStudent(pDTO); //학생 등록 SQL 실행하기
        }


        // 학생 테이블 전체 조회하기
        // select 쿼리 전체 조회
        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        // orElseGet() = null 값을 Empty로 처리
        // ArrayList 형태로 null이 아닌 Empty 로 메모리에 올려만 둠

        log.info(this.getClass().getName() + ".insertStudent End!");

        return rList;
    }


    // 여러명 동시 입력
    @Override
    public List<StudentDTO> insertStudentList(List<StudentDTO> pList) throws Exception {
        for(int i = 0; i < pList.size(); i++){
            Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudentList().get(i));
            if(res.isPresent()){
                studentMapper.insertStudent(pList.get(i));
                log.info(pList.get(i).getUserId());
            }
        }

        List<StudentDTO> rList = Optional.ofNullable(
                studentMapper.getStudentList()
        ).orElseGet(ArrayList::new);
        // orElseGet() = null 값을 Empty로 처리
        // ArrayList 형태로 null이 아닌 Empty 로 메모리에 올려만 둠

        log.info(this.getClass().getName() + ".insertStudentList End!");

        return rList;
    }


    @Override
    public void deleteStudent(StudentDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + "deleteStudent Start!");
        
        //Student 테이블에 등록된 학생 아이디가 존재하는지 체크하기 위해 DB 조회
        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));
        
        if(res.isPresent()){ //DB 조회 결과로 회원아이디가 존재한다면
            studentMapper.deleteStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 삭제되었습니다.");
        }else{
            log.info("회원이 존재하지 않아 삭제되지 못했습니다.");
        }


        log.info(this.getClass().getName() + "deleteStudent End!");
    }

    @Override
    public List<StudentDTO> updateStudent(StudentDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + "updateStudent Start!");

        // Student 테이블에 등록된 학생 아이디가 존재하는지 체크하기 위해 DB 조회
        Optional<StudentDTO> res = Optional.ofNullable(studentMapper.getStudent(pDTO));

        if(res.isPresent()){
            studentMapper.updateStudent(pDTO);
            log.info(pDTO.getUserId() + "님이 수정되었습니다.");
        }else{
            log.info("존재하지 않는 회원 ID입니다.");
        }

        List<StudentDTO> rList = Optional.ofNullable(studentMapper.getStudentList()).orElseGet(ArrayList::new);

        log.info(this.getClass().getName());

        return rList;
    }

}
