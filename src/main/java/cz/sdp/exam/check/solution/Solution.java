package cz.sdp.exam.check.solution;

import cz.sdp.exam.check.solution.exceptions.InitialLoadException;
import cz.sdp.exam.check.solution.model.FileContent;
import cz.sdp.exam.check.solution.service.FileService;
import cz.sdp.exam.check.solution.util.CounterMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class Solution implements CommandLineRunner {

    @Autowired
    private FileService fileService;

    private CounterMap<FileContent> counterMap = new CounterMap<>();

    @Override
    public void run(String... args) throws Exception {

        List<FileContent> allContent = fileService.parseArgumentsAndFindFiles(args);
        allContent.forEach(e -> counterMap.put(e));
        log.info(processContent(allContent));

    }

    public String solution(String location) throws InitialLoadException {
        List<FileContent> allContent = fileService.parseArgumentsAndFindFiles(location);
        allContent.forEach(e -> counterMap.put(e));
        return processContent(allContent);
    }

    private String processContent(List<FileContent> content) {
        counterMap.keySet().forEach(companyKey -> counterMap.get(companyKey).sort(Comparator.comparing(FileContent::getLocalDateTime)));
        return print(content);
      }

    private String print(List<FileContent> content){
        StringBuilder sb = new StringBuilder(content.size() * 10);
        content.forEach(fContent -> sb.append(fContent.getCompany() + "|" + (counterMap.getFormattedIndex(fContent)) + "|" + fContent.getProduct()).append(System.lineSeparator()));
        //storeToDb();
        return sb.toString();
    }

    private void storeToDb() {

    }



}
