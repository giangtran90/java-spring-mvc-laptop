package vn.hoidanit.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {
	
	private final UserService userService;

    public RegisterValidator(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords không chính xác!")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        
        // check if string contains at least one digit, one lowercase letter, one
        // uppercase letter, one special character and 8 characters long
        if (!user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$")) {
            context.buildConstraintViolationWithTemplate("Passwords phải có kí tự viết hoa, viết thường, kí tự đặc biệt và nhiều hơn 8 kí tự!")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        
        // check if email exist
        User currentUser = userService.fetchUserByEmail(user.getEmail());
        if (currentUser != null) {
        	context.buildConstraintViolationWithTemplate("Email đã được sử dụng!")
		            .addPropertyNode("email")
		            .addConstraintViolation()
		            .disableDefaultConstraintViolation();
        	valid = false;
        }
        
        // check if fistname = null or ""
        if (user.getFirstName() == null || "".equals(user.getFirstName())) {
        	context.buildConstraintViolationWithTemplate("Vui lòng nhập first name!")
		            .addPropertyNode("firstName")
		            .addConstraintViolation()
		            .disableDefaultConstraintViolation();
			valid = false;
        }
        
        // check if lastname = null or ""
        if (user.getLastName() == null || "".equals(user.getLastName())) {
        	context.buildConstraintViolationWithTemplate("Vui lòng nhập last name!")
		            .addPropertyNode("lastName")
		            .addConstraintViolation()
		            .disableDefaultConstraintViolation();
			valid = false;	
        }

        return valid;
    }
}
