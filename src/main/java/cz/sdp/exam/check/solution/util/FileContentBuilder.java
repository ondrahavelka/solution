package cz.sdp.exam.check.solution.util;

import cz.sdp.exam.check.solution.exceptions.InvalidInputException;
import cz.sdp.exam.check.solution.model.FileContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Component
public class FileContentBuilder {

    @Value("${date.format}")
    private String DATE_FORMAT;
    @Value("${input.regex}")
    private String REGEX;

    private static Pattern transactionPattern = Pattern.compile("^([a-zA-Z0-9 ,.\"]{1,200})$");

    private static Pattern partnerPattern = Pattern.compile("^([a-zA-Z0-9 ,.\"]{1,30})$");

    public FileContent build(String fileLine) throws InvalidInputException {
        FileContent fileContent = new FileContent();
        String[] split = fileLine.split(REGEX);
        if (!transactionPattern.matcher(split[0]).find()){
            throw new InvalidInputException("Error at Address: 14, Treacle Mine Road, Ankh-Morpork");
        }
        if (split[0].contains("\"")){
            fileContent.setProduct(split[0].replaceAll("\"", ""));
        } else {
            fileContent.setProduct(split[0]);
        }
        if (!partnerPattern.matcher(split[1].substring(0, split[1].indexOf('/')).trim()).find()){
            throw new InvalidInputException("+++Whoops! Here comes the cheese! +++");
        } else {
            fileContent.setCompany(split[1].substring(0, split[1].indexOf('/')).trim());
        }
        fileContent.setLocalDateTime(parseLocalDateTime(split[2]));
        return fileContent;
    }

    private LocalDateTime parseLocalDateTime(String stringDate) {
        return LocalDateTime.parse(stringDate.trim(), DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
