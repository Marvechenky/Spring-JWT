package com.example.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            description = "Endpoint for registering a user",
            summary = "This is user registration endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                        description = "Invalid user details",
                            responseCode = "401"
                )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @Operation(
            description = "Endpoint for authenticating users",
            summary = "User authentication endpoint",
            responses = {
                    @ApiResponse(
                            description = "User log in successfully",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Invalid email or password",
                            responseCode = "400"
                    )
            }
    )

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
