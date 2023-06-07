package kopo.poly.persistance.mapper;

import kopo.poly.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStudentMapper {
    /*
    학생 등록 쿼리
    <p>
    함수명 : insertStudent -> Mapper XML 파일의 ID와 동일하며, 여러개 작성 가능한 SQL 쿼리를 구분하기 위한 값
    Parameter : StudentDTO pDTO -> SQL 실행에 필요한 파라미터가 존재하는 객체로 Mapper XML 파일의 ParameterType과 동일
    리턴 타입 void -> inser 함수 실행한 뒤, 조회된 결과가 없음을 의미
    
    @param pDTO 등록할 학생정보
    * */
    void insertStudent(StudentDTO pDTO) throws Exception;

    /*
    학생 전체 조회 쿼리
    <p>
    함수명 : getStudentList -> Mapper XML 파일의 ID와 동일하며, 여러 개 작성가능한 SQL 쿼리를 구분하기 위한 값

    * */
}
