package com.example.springfling.Validator;

import com.example.springfling.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service("userValidator")
public class UserValidator {


    final static private String nameRegex = "^[\\p{L} \\-']+([\\p{L} \\-']+)*$";
    final static private String emailRegex = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    final static private int MAX_NAME_LEN = 45;
    final static private int MAX_EMAIL_LEN = 255;
    final static public String NAME_ERROR = "Name must contain at least one letter and no non-letter characters";

    /**
     * Check if the user's attributes are all valid.
     * ResponseStatusException is thrown and stops execution if
     * invalid attributes are found.
     *
     * @param user the user to be validated
     * @return true if valid user, otherwise throw ResponseStatusException
     */

    public boolean validate(User user) throws ResponseStatusException {
        validateName(user.getFirstName(), "first");
        validateName(user.getLastName(), "last");
        validateEmail(user.getEmail());

        return true;
    }

    /**
     * Checks name to ensure:
     *  name is not null (unless middle name)
     *  name conforms to regex for given part
     *  name does not exceed the character limit
     * @param name name to be checked
     * @param part the part of the full name that the given name represents [first, middle, last]
     * @throws ResponseStatusException thrown if the given name is invalid
     */
    public boolean validateName(String name, String part) {
        if (name.length() > MAX_NAME_LEN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid " + part + " name. Name is too long");
        }
        if (!name.matches(nameRegex) && !name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid " + part + " name. " + NAME_ERROR);
        }

        return true;
    }

    /**
     * Checks if the given users email is valid
     *  Checks if the email string length exceeds the MAX_EMAIL_LEN
     *  Checks if the email string matches the email validation regex
     * @param email is the string of an email for the given user
     * @throws ResponseStatusException if the given email string is invalid
     */
    public boolean validateEmail(String email) {
        if (email.length() > MAX_EMAIL_LEN) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Email exceeds maximum length: '%s'", email));
        }
        if (!email.matches(emailRegex)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid email: '%s'", email));
        }
        return true;
    }
}
