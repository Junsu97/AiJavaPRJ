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

    public void deleteStudent(StudentDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + "deleteStudent Start!");

        studentMapper.deleteStudent(pDTO);
        log.info(this.getClass().getName() + "deleteStudent End!");
    }

}