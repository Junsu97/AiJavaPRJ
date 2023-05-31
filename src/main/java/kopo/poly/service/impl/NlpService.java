package kopo.poly.service.impl;

import kopo.poly.dto.NlpDTO;
import kopo.poly.service.INlpService;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NlpService implements INlpService {
    /*
    FULL : 학습량 많음
    LIGHT : 학습량 적음
    * */
    Komoran komoran = new Komoran(DEFAULT_MODEL.FULL); //학습용 데이터는 가장 큰 파일 사용
    
    

    @Override
    public NlpDTO getPlainText(String text) {
        log.info(this.getClass().getName() + ".getPlaneText Start!");

        KomoranResult komoranResult = komoran.analyze(text); // 인식된 문자열 분석 결과

        String result = komoranResult.getPlainText(); // 모든 단어마다 품사 태깅

        NlpDTO rDTO = new NlpDTO();
        rDTO.setResult(result);

        log.info(this.getClass().getName() + ".getPlainText End!");

        return rDTO;
    }

    @Override
    public NlpDTO getNouns(String text) {
        log.info(this.getClass().getName() + ".getNouns Start!");

        KomoranResult komoranResult = komoran.analyze(text); // 인식된 문자열 분석 결과.
        List<String> nouns = komoranResult.getNouns(); // NNG(일반 명사), NNP(고유 명사) 품사만 추출

        NlpDTO rDTO = new NlpDTO();
        rDTO.setNouns(nouns);

        log.info(this.getClass().getName() + ".getNouns End!");

        return rDTO;
    }
}
