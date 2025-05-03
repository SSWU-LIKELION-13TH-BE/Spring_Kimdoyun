package com.likelion.session.service.calculator;
import org.springframework.stereotype.Service;
@Service
public class CalculatorService {

    public int add(int number1, int number2) {
        return number1 + number2;
    }

    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public int subtract(int number1, int number2) {
        return number1 - number2;
    }

    public int divide(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("0으로는 나눌 수 없습니다.");
        }
        return number1 / number2;
    }

}
