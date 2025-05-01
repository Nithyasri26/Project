package com.example.microservicecalls;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.QuestionDTO;

import com.example.dto.QuizDTO;


@FeignClient(name="QUIZ-SERVICE")
public interface QuizClient {
	
	@GetMapping("/quizzes/viewAllQuizzes")
	 public ResponseEntity<List<QuizDTO>> getAllQuizzes() ;
	
	@GetMapping("questions/viewAllQuestions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions();
	
	@GetMapping("questions/viewQuestionById/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable("questionId") String questionId);
	
   @GetMapping("questions/searchQuestion/{keyword}")
    public ResponseEntity<List<QuestionDTO>> searchQuestionsByKeyword(@PathVariable("keyword") String keyword);
	
   @GetMapping("questions/searchByQuizId/{quizId}")
   public ResponseEntity<List<QuestionDTO>> searchQuestionsByQuizId(@PathVariable("quizId") String quizId);

	


}
