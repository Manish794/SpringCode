package com.manish.spring.generator;

import com.manish.spring.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class UserIdGenerator {

    public String generateUserId(List<User> users){
        String newId = "U-001";
        if(!CollectionUtils.isEmpty(users)){
            Optional<String> userId = users
                    .stream()
                    .map(user -> user.getUserId())
                    .sorted(Comparator.reverseOrder()).findFirst();
            if(userId.isPresent()) {
                String lastUserId = userId.get();
                int idIntPart = Integer.parseInt(lastUserId.substring(2));
                idIntPart++;
                if(idIntPart < 10)
                    newId = "U-00"+idIntPart;
                else if (idIntPart < 100)
                    newId = "U-0"+idIntPart;
                else
                    newId = "U-"+idIntPart;
            }
        }
        return newId;
    }
}
