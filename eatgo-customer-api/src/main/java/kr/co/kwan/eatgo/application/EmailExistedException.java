package kr.co.kwan.eatgo.application;

public class EmailExistedException extends RuntimeException {

    EmailExistedException(String email){
        super("email is already registered : " + email);
    }
}
