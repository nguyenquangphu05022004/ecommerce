package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.EvaluationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import org.springframework.stereotype.Service;

@Service
public class EvaluationServiceImpl  implements IEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, UserRepository userRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EvaluationDto saveOrUpdate(EvaluationDto evaluationDto) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        Evaluation evaluation = null;
        if(user.getEvaluation() != null) {
            Evaluation old = user.getEvaluation();
            evaluation = (Evaluation) Convert.EVAL.toEntity(old, evaluationDto);
        } else {
            evaluation = (Evaluation) Convert.EVAL.toEntity(evaluationDto);
        }
        evaluation.setUser(user);
        EvaluationDto evalDto =  (EvaluationDto) Convert.EVAL.toDto(evaluationRepository.save(evaluation));
        return evalDto;
    }

    @Override
    public long count() {
        return evaluationRepository.count();
    }

    @Override
    public void delete(Long id) {
        evaluationRepository.deleteById(id);
    }
}
