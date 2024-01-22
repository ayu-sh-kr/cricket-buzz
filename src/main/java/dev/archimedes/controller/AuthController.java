package dev.archimedes.controller;

import dev.archimedes.dtos.LoginRequest;
import dev.archimedes.dtos.RegistrationRequest;
import dev.archimedes.dtos.UpdateProfile;
import dev.archimedes.entities.User;
import dev.archimedes.enums.AccountStatus;
import dev.archimedes.enums.UserType;
import dev.archimedes.repositories.UserRepository;
import dev.archimedes.services.security.TokenService;
import dev.archimedes.utils.KeyEncoderUtil;
import dev.archimedes.utils.exceptions.BadRequest;
import dev.archimedes.utils.exceptions.NullObjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        if(null != loginRequest){
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            try {
                Authentication res = authenticationManager.authenticate(authentication);
                if(res.isAuthenticated()){
                    Map<String, String> map = new HashMap<>();
                    String token = tokenService.generateToken(res);
                    map.put("jwt", token);
                    map.put("userId", KeyEncoderUtil.encodeKey(userRepository.getIdByEmail(loginRequest.getEmail())));
                    return new ResponseEntity<>(map, HttpStatus.OK);
                }
                return new ResponseEntity<>("Username password did not match", HttpStatus.UNAUTHORIZED);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Malformed Request", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest){
        if(null != registrationRequest){
            if(userRepository.existsByEmail(registrationRequest.getEmail())){
                throw new RuntimeException("Email Already Exist");
            }
            User user = User.builder()
                    .name(registrationRequest.getName())
                    .email(registrationRequest.getEmail())
                    .phone(Long.parseLong(registrationRequest.getPhone()))
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .active(true)
                    .userType(UserType.USER)
                    .accountStatus(AccountStatus.ENABLED)
                    .build();
            try {
                userRepository.save(user);
                return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
            }catch (Exception e){
                throw new RuntimeException("Error creating user");
            }
        }
        return new ResponseEntity<>("Invalid registration request", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfile updateProfile){
        if(null != updateProfile){
            int id = KeyEncoderUtil.decodeKey(updateProfile.getId());
            if(userRepository.existsById(id)){
                LocalDate dob = LocalDate.parse(updateProfile.getDob(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                int updateStatus = userRepository.updateNameAndPhoneAndDateOfBirthById(updateProfile.getName(),
                        Long.parseLong(updateProfile.getPhone()), dob, id);
                if(updateStatus > 0){
                    return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
                }
                throw new RuntimeException("Something went wrong");
            }
        }
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/deactivate")
    public ResponseEntity<?> deactivateAccount(@RequestParam("userId") String userId) throws BadRequest {
        int id = KeyEncoderUtil.decodeKey(userId);
        if(userRepository.existsById(id)){
            userRepository.updateAccountStatusById(AccountStatus.DISABLED, id);
            return new ResponseEntity<>("Account Deactivated", HttpStatus.OK);
        }
        else {
            throw new BadRequest("Invalid credential");
        }
    }

    @PatchMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestBody LoginRequest loginRequest) throws BadRequest, NullObjectException {
        if(userRepository.existsByEmail(loginRequest.getEmail())){
            boolean password = userRepository.findByEmail(loginRequest.getEmail()).getPassword()
                    .equals(passwordEncoder.encode(loginRequest.getPassword()));

            if(password){
                userRepository.updateAccountStatusById(AccountStatus.ENABLED, userRepository.getIdByEmail(loginRequest.getEmail()));
                return new ResponseEntity<>("Account Activated", HttpStatus.OK);
            }
            throw new BadRequest("Invalid request credentials");
        }
        throw new NullObjectException("Request is null");
    }
}


