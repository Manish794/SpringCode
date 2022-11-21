package com.manish.spring.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.manish.spring.model.Login;
import com.manish.spring.model.User;
import com.manish.spring.model.UserResponse;
import com.manish.spring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserResponse<String> addUser(User user){
        UserResponse<String> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(user.getEmail());
        if(result == null) {
            result =  userRepo.getUserByPhone(user.getPhone());
            if(result == null) {
                result =  userRepo.getUserByLoginId(user.getLoginId());
                if(result == null) {
                    String userId = userRepo.addUser(user);
                    response.setData(userId);
                } else{
                    response.setErrorCode("LOGIN_ID_ALREADY_USED");
                    response.setErrorMessage("LoginId "+user.getLoginId()+" is not available");
                }
            } else {
                response.setErrorCode("PHONE_ALREADY_USED");
                response.setErrorMessage("Phone "+user.getPhone()+" is not available");
            }
        } else {
            response.setErrorCode("EMAIL_ALREADY_USED");
            response.setErrorMessage("Email "+user.getEmail()+" is not available");
        }
        return response;
    }

    public UserResponse<User> updateUser(User user){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(user.getEmail());
        if(result == null) {
            result =  userRepo.getUserByPhone(user.getPhone());
            if(result == null) {
                result =  userRepo.getUserByLoginId(user.getLoginId());
                if(result == null) {
                    result = userRepo.updateUser(user);
                    if(result == null) {
                        response.setErrorCode("USER_NOT_FOUND");
                        response.setErrorMessage("User with id "+user.getUserId()+" is not available");
                    } else {
                        response.setData(result);
                    }
                } else{
                    response.setErrorCode("LOGIN_ID_ALREADY_USED");
                    response.setErrorMessage("LoginId "+user.getLoginId()+" is not available");
                }
            } else {
                response.setErrorCode("PHONE_ALREADY_USED");
                response.setErrorMessage("Phone "+user.getPhone()+" is not available");
            }
        } else {
            response.setErrorCode("EMAIL_ALREADY_USED");
            response.setErrorMessage("Email "+user.getEmail()+" is not available");
        }
        return response;
    }

    public UserResponse<User> getUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUser(userId);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> getUserByPhone(long phone){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByPhone(phone);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with phone "+phone+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> getUserByEmail(String email){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.getUserByEmail(email);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with email "+email+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<List<User>> getAllUsers(){
        UserResponse<List<User>> response = new UserResponse<>();
        List<User> result = userRepo.getAllUsers();
        if(result == null || result.isEmpty()) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("No user available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> deleteUser(String userId){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.deleteUser(userId);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("User with id "+userId+" is not available");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<User> loginUser(Login login){
        UserResponse<User> response = new UserResponse<>();
        User result = userRepo.loginUser(login);
        if(result == null) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("Invalid username or password");
        } else {
            response.setData(result);
        }
        return response;
    }

    public UserResponse<Boolean> deleteAll(){
        UserResponse<Boolean> response = new UserResponse<>();
        boolean result = userRepo.deleteAll();
        if(!result) {
            response.setErrorCode("USER_NOT_FOUND");
            response.setErrorMessage("No Record to Delete");
        } else {
            response.setData(result);
        }
        return response;
    }


    public UserResponse<String> generatePDFReport(){
        UserResponse<String> response = new UserResponse<>();
        try{
            File file = new File("UserDetails.pdf");
            UserResponse<List<User>> getAllUserResponse = getAllUsers();
            if(!ObjectUtils.isEmpty(getAllUserResponse)){
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();
                PdfPTable table = new PdfPTable(4);
                addTableHeader(table);
                addRows(table, getAllUserResponse.getData());
                document.add(table);
                document.close();
            }
            response.setData(file.getAbsolutePath());
        }catch(Exception e){
            response.setErrorCode("ERROR_GENERATING_PDF");
            e.printStackTrace();
        }
        return response;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("UserId", "Full Name", "Email", "Phone")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<User> users) {
        for(User user: users) {
            table.addCell(user.getUserId());
            table.addCell(user.getFullName());
            table.addCell(user.getEmail());
            table.addCell(String.valueOf(user.getPhone()));
        }
    }

}
