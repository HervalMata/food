package com.herval.food.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedContentTypes;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedContentTypes = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile == null
                || this.allowedContentTypes.contains(multipartFile.getContentType());
    }
}
