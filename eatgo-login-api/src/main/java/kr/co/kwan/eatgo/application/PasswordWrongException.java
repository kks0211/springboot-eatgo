package kr.co.kwan.eatgo.application;

public class PasswordWrongException extends RuntimeException{
    PasswordWrongException(){
        super("Password is Wrong");
    }
}
