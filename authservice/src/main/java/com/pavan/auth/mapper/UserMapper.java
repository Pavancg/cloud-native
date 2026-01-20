package com.pavan.auth.mapper;


import com.pavan.auth.dto.request.RegisterRequest;
import com.pavan.auth.dto.response.UserResponse;
import com.pavan.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(RegisterRequest request){
        if(request == null){
            return  null;
        }

        return User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public UserResponse toResponse(User user){
        if(user == null){
            return null;
        }

        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getCreatedAt());
    }
}
