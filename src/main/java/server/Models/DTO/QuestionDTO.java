package server.Models.DTO;

import server.Models.Entities.Client;
import server.Models.Entities.Question;

import javax.persistence.*;
import java.util.List;

public class QuestionDTO {

    private int questionid;

    private String text;

    private String answer;

    public QuestionDTO(Question question){
        this.answer = question.getAnswer();
        this.text = question.getText();
        this.questionid = question.getQuestionid();
    }

    public QuestionDTO() {
    }
    public QuestionDTO(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public QuestionDTO(int questionid, String text, String answer) {
        this.questionid = questionid;
        this.text = text;
        this.answer = answer;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
